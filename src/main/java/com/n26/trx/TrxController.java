package com.n26.trx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * Created by carlos on 4/21/17.
 */
@RestController
public class TrxController {


    @Autowired
    private TrxService trxService;



    @RequestMapping(path = "/transactions", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity recordTrx(@RequestBody TrxRecord record) {

        if (trxService.save(record) == 0) // if is the first record, then send 201
            return ResponseEntity.created(URI.create("/statistics")).body("trx record created");
        else //send 202
            return ResponseEntity.accepted().body("trx accepted into the record");

    }

    @RequestMapping(path = "/statistics", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<TrxStats> statistics() {
        return ResponseEntity.ok(trxService.getStats());
    }

    // just for testing. Returns all trx recorded.
    @RequestMapping(path = "/dump", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<TrxRecord>> dump() {
        return ResponseEntity.ok(trxService.dump());
    }


}
