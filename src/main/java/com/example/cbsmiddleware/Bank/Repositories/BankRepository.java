package com.example.cbsmiddleware.Bank.Repositories;

import com.example.cbsmiddleware.Bank.Entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Integer> {
}
