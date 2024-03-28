package com.example.cbsmiddleware.Country.Controller;

import com.example.cbsmiddleware.Country.Dto.CountryDto;
import com.example.cbsmiddleware.Country.Entities.Country;
import com.example.cbsmiddleware.Country.Service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bankerise/api/countries/")
public class CountryController {
@Autowired
CountryService countryService;

    @GetMapping("listCountries")
    public List<Country> countryList(){
        return  countryService.getAllCountry();
    }
    @GetMapping("listEnabledCountries")
    public List<Country> listEnabledCountries(){
        return  countryService.listEnabledCountries();
    }

    @GetMapping("getCountryByName/{countryName}")
    public Optional<Country> countryByName(@PathVariable String countryName){
        return  countryService.getCountryByCountryName(countryName);
    }

    @PostMapping("addCountry")
    public Country addCountry(@RequestBody CountryDto countryDto){
        return countryService.addcountry(countryDto);
    }
    @PutMapping("updateCountry/{isoCode}")
    public Country addCountry(@RequestBody CountryDto  countryDto , @PathVariable String isoCode ){
        return countryService.updatecountry(countryDto , isoCode);
    }

    @DeleteMapping("deleteCountry/{isoCode}")
    public void deleteCountry( @PathVariable String isoCode ){
        countryService.deleteCountry(isoCode);
    }
}
