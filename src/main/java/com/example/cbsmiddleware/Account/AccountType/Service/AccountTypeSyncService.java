//package com.example.cbsmiddleware.Account.AccountType.Service;
//
//import com.example.cbsmiddleware.Account.AccountType.DTO.ProductSavingFineractDto;
//import com.example.cbsmiddleware.Account.AccountType.Entities.Account;
//import com.example.cbsmiddleware.Account.AccountType.Repositories.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//public class AccountTypeSyncService {
//
//  @Autowired
//  AccountRepository accountRepository;
//
//    public void syncAccountTypesWithFineract() {
//        List<ProductSavingFineractDto> fineractAccountTypes = AccountFineractService.getAccountTypes();
//
//        for (ProductSavingFineractDto fineractAccountType : fineractAccountTypes) {
//            Account accountType = new Account();
//            accountType.setMaintenanceFee(fineractAccountType.getMaintenanceFee());
//            accountType.setMaintenancePeriodicity(fineractAccountType.getMaintenancePeriodicity());
//
//            accountRepository.save(accountType);
//        }
//    }
//
//}
