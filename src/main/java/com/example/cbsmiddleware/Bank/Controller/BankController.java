package com.example.cbsmiddleware.Bank.Controller;

import com.example.cbsmiddleware.Bank.Entities.Bank;
import com.example.cbsmiddleware.Bank.Services.BankFineractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankerise/api/banks/")

public class BankController {

    @Autowired
     BankFineractService bankFineractService;
    @PostMapping("addOwnBank")
    public ResponseEntity<Object> addAccountType(@RequestBody Bank bank) {
        Object response = bankFineractService.addBank(bank);
        return ResponseEntity.ok(response);
    }
    @PutMapping("updateOwnBank/{id}")
    public ResponseEntity<Object> addAccountType(@RequestBody Bank bank, @PathVariable Integer id ) {
        Object response = bankFineractService.updateBank(bank, id );
        return ResponseEntity.ok(response);
    }

    @GetMapping("bankList")
    public ResponseEntity<Object> getBankLinst() {
        Object response = bankFineractService.getListBank();
        return ResponseEntity.ok(response);
    }

}
