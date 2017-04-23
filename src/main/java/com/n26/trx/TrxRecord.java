package com.n26.trx;

/**
 * Created by carlos on 4/21/17.
 *
 * Just a bean to hold the transaction data.
 */
public class TrxRecord {

    private long timestamp;
    private Double amount;

    public TrxRecord() {
    }

    public TrxRecord(long timestamp, Double amount) {
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "{ \"timestamp\": " + timestamp + ",\"amount\":" +amount + "}";
    }
}
