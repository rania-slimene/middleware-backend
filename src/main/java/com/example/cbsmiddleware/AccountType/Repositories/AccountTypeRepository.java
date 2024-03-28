package com.example.cbsmiddleware.AccountType.Repositories;

import com.example.cbsmiddleware.AccountType.Entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  AccountTypeRepository extends JpaRepository<AccountType,Integer> {

    Optional<AccountType> findById(Integer accountId);
}
