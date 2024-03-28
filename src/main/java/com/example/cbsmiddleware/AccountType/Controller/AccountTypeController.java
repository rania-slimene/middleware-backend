package com.example.cbsmiddleware.AccountType.Controller;

import com.example.cbsmiddleware.AccountType.Entities.AccountType;
import com.example.cbsmiddleware.AccountType.Service.AccountTypeFineractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankerise/api/accountTypes/")
public class AccountTypeController {
@Autowired
AccountTypeFineractService accountTypeFineractService;
      @GetMapping("listAccountTypes")

      public ResponseEntity<Object> getAllAccountType(){
          Object response = accountTypeFineractService.getAllAccountType();
          return ResponseEntity.ok(response);
      }
    @PostMapping("addAccountType")
    public ResponseEntity<Object> addAccountType(@RequestBody AccountType cbsAccountTypeType) {
        Object response = accountTypeFineractService.addAccountType(cbsAccountTypeType);
        return ResponseEntity.ok(response);
    }
    @PutMapping("updateAccountType/{accountTypeId}")
    public ResponseEntity<Object> updateAccountType(@RequestBody AccountType cbsAccountType, @PathVariable Integer accountTypeId ) {
        Object response = accountTypeFineractService.updateAccountType(cbsAccountType, accountTypeId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("deleteAccountType/{accountTypeId}")
    public Object deleteAccountType(@PathVariable Integer accountTypeId ){
        return  accountTypeFineractService.deleteAccountType(accountTypeId);
    }
}
