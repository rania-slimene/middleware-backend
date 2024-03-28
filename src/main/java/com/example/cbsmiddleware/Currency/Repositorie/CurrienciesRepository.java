package com.example.cbsmiddleware.Currency.Repositorie;

import com.example.cbsmiddleware.Currency.Entities.Currencies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrienciesRepository extends JpaRepository<Currencies, Integer> {

   Optional<Currencies> findByIsoCode (String IsoCode);
}
