package com.example.cbsmiddleware.Account.AccountType.Controller;

import com.example.cbsmiddleware.Account.AccountType.Entities.Account;
import com.example.cbsmiddleware.Account.AccountType.Service.AccountFineractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cbs/")
public class AccountController {
@Autowired
    AccountFineractService accountFineractService;
    @PostMapping("AccountType")
    public ResponseEntity<Object> addAccountType(@RequestBody Account cbsAccountType) {
        Object response = accountFineractService.addAccountType(cbsAccountType);
        return ResponseEntity.ok(response);
    }
    @PutMapping("AccountType/{id}")
    public ResponseEntity<Object> updateAccountType(@RequestBody Account cbsAccountType , @PathVariable Integer id ) {
        Object response = accountFineractService.updateAccountType(cbsAccountType, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("AccountType/{id}")
    public Object deleteAccountType(@PathVariable Integer id ){
        return  accountFineractService.deleteAccountType(id);
    }
}
