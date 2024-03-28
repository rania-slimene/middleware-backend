package com.example.cbsmiddleware.Country.Service;

import com.example.cbsmiddleware.Country.Dto.CountryDto;
import com.example.cbsmiddleware.Country.Entities.Country;
import com.example.cbsmiddleware.Country.Repositorie.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    @Autowired
    CountryRepository countryRepository;


    public Country addcountry (CountryDto countryDto){
        Country Country = new  Country();

        Country.setCountryName(countryDto.getCountryNameEn());
        Country.setCountryName(countryDto.getCountryNameFr());
        Country.setEnabled(countryDto.getEnabled());
        Country.setIso2Code(countryDto.getIso2Code());
        Country.setIso3Code(countryDto.getIso3Code());
        Country.setIsoCode(countryDto.getIsoCode());
          return  countryRepository.save(Country);

    }
    public Country updatecountry (CountryDto countryDto, String IsoCode){

        Optional<Country> findByIsoCode = countryRepository.findByIsoCode(IsoCode);
        if (findByIsoCode.isPresent()) {
            Country PutCountry = findByIsoCode.get();
            PutCountry.setCountryName(countryDto.getCountryNameEn());
            PutCountry.setCountryName(countryDto.getCountryNameFr());
            PutCountry.setEnabled(countryDto.getEnabled());
            PutCountry.setIso2Code(countryDto.getIso2Code());
            PutCountry.setIso3Code(countryDto.getIso3Code());
            PutCountry.setIsoCode(countryDto.getIsoCode());
            return countryRepository.save(PutCountry);
        }
        throw new IllegalArgumentException("IsoCode Not exist");
    }

   public List<Country> getAllCountry (){
       return     countryRepository.findAll();
    }
    public List<Country> listEnabledCountries() {
        return countryRepository.findByEnabledTrue();
    }
    public Optional<Country> getCountryByCountryName (String countryName) {
        Optional<Country> findByCountryName = countryRepository.findByCountryName(countryName);
        if (findByCountryName.isPresent()) {
            return countryRepository.findByCountryName(countryName);
        }
        else throw new IllegalArgumentException("countryName not Exist");
    }

    public void deleteCountry (String IsoCode ){
        Optional<Country> currenciesRepo = countryRepository.findByIsoCode(IsoCode);
        if (currenciesRepo.isPresent()) {
            Country country = currenciesRepo.get();
            countryRepository.delete(country);

        }
        else throw new IllegalArgumentException("currencie not exist");

    }
}
