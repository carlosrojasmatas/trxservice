package com.n26.trx;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by carlos on 4/23/17.
 *
 * Just a testing class. It generates a random transaction
 * every second and send it to the app.
 */
public class TrxGenerator {
    private static Random rnd = new Random();

    public static void main(String[] args) throws IOException, InterruptedException {
        while(true){
            long t = System.currentTimeMillis();
            double amt = rnd.nextDouble() + 100;
            TrxRecord r = new TrxRecord(t,amt);
            System.out.println("sending " + r.toString());
            URL url = new URL("http://localhost:8080/transactions");

            HttpURLConnection cnn = (HttpURLConnection) url.openConnection();

            cnn.setDoOutput(true);
            cnn.setRequestMethod("POST");
            cnn.setRequestProperty("Content-Type","application/json");
            OutputStreamWriter wr = new OutputStreamWriter(cnn.getOutputStream());
            wr.write(r.toString());

            wr.close();
            System.out.println(cnn.getResponseCode());
            Thread.sleep(1000);
        }
    }
}
