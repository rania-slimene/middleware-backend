package com.example.cbsmiddleware.Account.Controller;

import com.example.cbsmiddleware.Account.Dto.ActivateAccountDtoFineract;
import com.example.cbsmiddleware.Account.Dto.ApproveAccountDtoFineract;
import com.example.cbsmiddleware.Account.Dto.CloseAccountFineractDto;
import com.example.cbsmiddleware.Account.Entities.Account;
import com.example.cbsmiddleware.Account.Services.AccountFineractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankerise/api/accounts/")
public class AccountController {
    @Autowired
    AccountFineractService accountFineractService;

    @PostMapping("addAccount")
    public ResponseEntity<Object> addAccount(@RequestBody Account cbsAccount) {
        Object response = accountFineractService.addAccount(cbsAccount);
        return ResponseEntity.ok(response);
    }

    @PutMapping("updateAccount/{accountId}")
    public ResponseEntity<Object> updateAccount(@RequestBody Account cbsAccount, @PathVariable Integer accountId) {
        Object response = accountFineractService.updateAccount(cbsAccount,accountId );
        return ResponseEntity.ok(response);
    }
   @GetMapping("listAccounts")
    public ResponseEntity<Object>getAllListAccount(){
        Object response = accountFineractService.getAllAccount();
        return  ResponseEntity.ok(response);
}

    @GetMapping("accountByAccountNumber/{accountNumber}")
    public ResponseEntity<Object>getAccount(@PathVariable String accountNumber){
        Object response = accountFineractService.getAccountByAccountNumber(accountNumber);
        return  ResponseEntity.ok(response);
    }
    @PostMapping("approveAccount/{accountNumber}")
    public Object approveAccount( @PathVariable String accountNumber ,@RequestBody ApproveAccountDtoFineract approveAccount) {
        return accountFineractService.approveAccount(approveAccount , accountNumber );
    }
    @PostMapping("activateAccount/{accountNumber}")
    public Object activateAccount( @PathVariable String accountNumber ,@RequestBody ActivateAccountDtoFineract activateAccount) {
        return accountFineractService.activeAcount(activateAccount , accountNumber );
    }
    @PostMapping("inactivateAccount/{accountNumber}")
    public Object inactivateAccount( @PathVariable String accountNumber ,@RequestBody CloseAccountFineractDto inactivateAccount) {
        return accountFineractService.inactiveAcount(inactivateAccount , accountNumber );
    }
    @DeleteMapping("deleteAccount/{accountNumber}")
    public ResponseEntity<Object>getAllListAccount(@PathVariable String accountNumber){
        Object response = accountFineractService.deleteAccount(accountNumber);
        return  ResponseEntity.ok(response);
    }
//    @GetMapping("listAccountsByCustomer/{accountNumber}")
//    public ResponseEntity<Object>getAccountByAccontNumber(@PathVariable String accountNumber){
//        Object response = accountFineractService.getAccountByAccountNumber(accountNumber);
//        return  ResponseEntity.ok(response);
//    }


}
