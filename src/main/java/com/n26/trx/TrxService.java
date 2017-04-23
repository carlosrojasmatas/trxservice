package com.n26.trx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.DoubleStream;

/**
 * Created by carlos on 4/21/17.
 *
 * This is the main class. It has 3 main responsibilities:
 * -- Records all the transaction into an in-memory data structure (aka storage).
 * -- Computes the stats of the last 60 seconds.
 * -- Dumps the state of the storage.
 *
 * Another approach would be handling invariants over single variables, updating them every time
 * a trx is recorded, but that would needed a more complex synchronization policy.
 */

@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TrxService {

    private Logger logger = LoggerFactory.getLogger(TrxService.class);
    /*
     * I could've use a synchronized list, but wasn't enough to ensure thread safety over
     * the storage and stats computation.
     */
    private List<TrxRecord> storage = new ArrayList<>();
    private TrxStats stats = new TrxStats();


    public Integer save(TrxRecord record) {
        Integer count = storage.size();
        synchronized (storage) { // to avoid cleaning while computing stats.
            storage.add(record);
            return count;
        }

    }

    public List<TrxRecord> dump() {
        List<TrxRecord> safeCopy = storage;
        return safeCopy;
    }

    public TrxStats getStats() {
        synchronized (stats) { //to avoid race conditions during updates.
            return stats;
        }
    }


    @Scheduled(initialDelay = 60000, fixedRate = 60000)
    public void refreshStats() {

        logger.info("Computing stats for {} records", storage.size());

        long count;
        Double avg;
        Double max;
        Double min;
        Double sum;

        synchronized (storage) { //sync to avoid adding new trxs while computing stats.
            long initTime = System.currentTimeMillis() - 60000;
            logger.info("Filtering events older than {}", new Date(initTime));

            /*
             According to the specification, old transactions could came at any time, so I need to filter up only
             trxs from the last minute.
             */
            DoubleStream amounts = storage.stream().filter(r -> r.getTimestamp() >= initTime)
                    .mapToDouble(TrxRecord::getAmount);
            //get the stats (thanks java ;))
            DoubleSummaryStatistics summary = amounts.summaryStatistics();
            if (summary.getCount() > 0) {
                count = summary.getCount();
                avg = summary.getAverage();
                max = summary.getMax();
                min = summary.getMin();
                sum = summary.getSum();
            } else {
                count = 0;
                avg = 0d;
                max = 0d;
                min = 0d;
                sum = 0d;
            }

            storage.clear();
        }

        synchronized (stats) { //synchronizing over stats (different mutex) to ensure atomicity during update.
            stats = new TrxStats();
            stats.setSum(sum);
            stats.setAvg(avg);
            stats.setCount(count);
            stats.setMax(max);
            stats.setMin(min);
        }

        logger.info("Stats computation ended");
    }

}
