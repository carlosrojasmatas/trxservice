package com.n26.trx;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * Created by carlos on 4/22/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,classes = TestConfig.class)
public class TrxServiceTest {

    @Autowired
    private TrxService service;

    @Test
    public void testLastMinute() {
        long t1 = System.currentTimeMillis() + 1000;
        long t2 = System.currentTimeMillis() + 1000;
        long t3 = System.currentTimeMillis() + 1000;
        long t4 = System.currentTimeMillis() + 1000;
        long t5 = System.currentTimeMillis() + 1000;
        long t6 = System.currentTimeMillis() + 1000;
        long t7 = System.currentTimeMillis() + 1000;
        long t8 = System.currentTimeMillis() + 1000;
        long t9 = System.currentTimeMillis() + 1000;
        long t10 = System.currentTimeMillis() + 1000;

        double a1 = 12.5;
        double a2 = 13.5;
        double a3 = 14.5;
        double a4 = 15.5;
        double a5 = 16.5;
        double a6 = 17.5;
        double a7 = 18.5;
        double a8 = 19.5;
        double a9 = 120.5;
        double a10 = 121.5;

        service.save(new TrxRecord(t1,a1));
        service.save(new TrxRecord(t2,a2));
        service.save(new TrxRecord(t3,a3));
        service.save(new TrxRecord(t4,a4));
        service.save(new TrxRecord(t5,a5));
        service.save(new TrxRecord(t6,a6));
        service.save(new TrxRecord(t7,a7));
        service.save(new TrxRecord(t8,a8));
        service.save(new TrxRecord(t9,a9));
        service.save(new TrxRecord(t10,a10));


        service.refreshStats();

        TrxStats stats = service.getStats();

        Assert.assertEquals(stats.getMax(),121.5,0);
        Assert.assertEquals(stats.getMin(),12.5,0);
        Assert.assertEquals(stats.getAvg(),37,0);
        Assert.assertEquals(stats.getSum(),370,0);
        Assert.assertEquals(stats.getCount(),10,0);


    }

    @Test
    public void testLastMinuteWithDuplicates() {
        long t1 = System.currentTimeMillis() + 1000;
        long t2 = t1;
        long t3 = System.currentTimeMillis() + 1000;
        long t4 = System.currentTimeMillis() + 1000;
        long t5 = System.currentTimeMillis() + 1000;
        long t6 = t5;
        long t7 = t5;
        long t8 = System.currentTimeMillis() + 1000;
        long t9 = System.currentTimeMillis() + 1000;
        long t10 = System.currentTimeMillis() + 1000;

        double a1 = 12.5;
        double a2 = 13.5;
        double a3 = 14.5;
        double a4 = 15.5;
        double a5 = 16.5;
        double a6 = 17.5;
        double a7 = 18.5;
        double a8 = 19.5;
        double a9 = 120.5;
        double a10 = 121.5;

        service.save(new TrxRecord(t1,a1));
        service.save(new TrxRecord(t2,a2));
        service.save(new TrxRecord(t3,a3));
        service.save(new TrxRecord(t4,a4));
        service.save(new TrxRecord(t5,a5));
        service.save(new TrxRecord(t6,a6));
        service.save(new TrxRecord(t7,a7));
        service.save(new TrxRecord(t8,a8));
        service.save(new TrxRecord(t9,a9));
        service.save(new TrxRecord(t10,a10));


        service.refreshStats();

        TrxStats stats = service.getStats();

        Assert.assertEquals(stats.getMax(),121.5,0);
        Assert.assertEquals(stats.getMin(),12.5,0);
        Assert.assertEquals(stats.getAvg(),37,0);
        Assert.assertEquals(stats.getSum(),370,0);
        Assert.assertEquals(stats.getCount(),10,0);

    }

    @Test
    public void testLastMinuteWithOldTrxs() {
        long t1 = System.currentTimeMillis() + 1000;
        long t2 = t1;
        long t3 = System.currentTimeMillis() + 1000;
        long t4 = System.currentTimeMillis() + 1000;
        long t5 = System.currentTimeMillis() + 1000;
        long t6 = t5;
        long t7 = t5;
        long t8 = System.currentTimeMillis() + 1000;
        long t9 = System.currentTimeMillis() + 1000;
        long t10 = System.currentTimeMillis() + 1000;
        long t11 = System.currentTimeMillis() - 61000;
        long t12 = System.currentTimeMillis() - 62000;

        double a1 = 12.5;
        double a2 = 13.5;
        double a3 = 14.5;
        double a4 = 15.5;
        double a5 = 16.5;
        double a6 = 17.5;
        double a7 = 18.5;
        double a8 = 19.5;
        double a9 = 120.5;
        double a10 = 121.5;
        double a11 = 121.5;
        double a12 = 121.5;

        service.save(new TrxRecord(t1,a1));
        service.save(new TrxRecord(t2,a2));
        service.save(new TrxRecord(t3,a3));
        service.save(new TrxRecord(t4,a4));
        service.save(new TrxRecord(t5,a5));
        service.save(new TrxRecord(t6,a6));
        service.save(new TrxRecord(t7,a7));
        service.save(new TrxRecord(t8,a8));
        service.save(new TrxRecord(t9,a9));
        service.save(new TrxRecord(t10,a10));
        service.save(new TrxRecord(t11,a11));
        service.save(new TrxRecord(t12,a12));


        service.refreshStats();

        TrxStats stats = service.getStats();

        Assert.assertEquals(stats.getMax(),121.5,0);
        Assert.assertEquals(stats.getMin(),12.5,0);
        Assert.assertEquals(stats.getAvg(),37,0);
        Assert.assertEquals(stats.getSum(),370,0);
        Assert.assertEquals(stats.getCount(),10,0);


    }

}
