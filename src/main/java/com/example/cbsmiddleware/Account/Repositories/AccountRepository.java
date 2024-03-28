package com.example.cbsmiddleware.Account.Repositories;

import com.example.cbsmiddleware.Account.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
 Optional<Account> findById(Integer accountId);
 Optional<Account> deleteByAccountNumber(String accountNumber);
 Optional<Account>  findByAccountNumber(String accountNumber);
 public Account findByAccountCode(String accountCode);
 public Account findByIban(String Iban);
 public Account findByRib(String rib);
}
