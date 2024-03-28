package com.example.cbsmiddleware.Country.Repositorie;

import com.example.cbsmiddleware.Country.Entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country , Integer> {

    Optional<Country> findByIsoCode(String isoCode);
    Optional<Country> findByCountryName(String countryName);
    List<Country> findByEnabledTrue();
}
