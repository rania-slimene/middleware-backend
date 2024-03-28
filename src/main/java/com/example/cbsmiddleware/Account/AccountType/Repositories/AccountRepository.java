package com.example.cbsmiddleware.Account.AccountType.Repositories;

import com.example.cbsmiddleware.Account.AccountType.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  AccountRepository extends JpaRepository<Account,Integer> {
// Account findAccountTypeId(Integer accountTypeId);

}
