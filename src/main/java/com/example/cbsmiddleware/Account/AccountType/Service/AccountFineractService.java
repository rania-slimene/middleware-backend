package com.example.cbsmiddleware.Account.AccountType.Service;

import com.example.cbsmiddleware.Account.AccountType.DTO.ProductSavingFineractDto;
import com.example.cbsmiddleware.Account.AccountType.Entities.Account;
import com.example.cbsmiddleware.Account.AccountType.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public class AccountFineractService extends AbstractApiService{

    @Value("${fineract.api.url}")
    private String fineractApiUrl;
    @Autowired
    AccountRepository accountRepository;

    public Object addAccountType(Account cbsAccountType) {
        ProductSavingFineractDto fineractAccountType = mapToFineract(cbsAccountType);
        HttpHeaders headers = this.createHeaders();
        HttpEntity<ProductSavingFineractDto> entity = new HttpEntity<>(fineractAccountType, headers);

        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/savingsproducts",
                HttpMethod.POST, entity, Object.class);
        accountRepository.save(cbsAccountType);
        return response.getBody();
    }
    public Object updateAccountType(Account cbsAccountType, Integer accountTypeId) {
        ProductSavingFineractDto fineractAccountType = mapToFineract(cbsAccountType);
        HttpHeaders headers = this.createHeaders();
        HttpEntity<ProductSavingFineractDto> entity = new HttpEntity<>(fineractAccountType, headers);

        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/savingsproducts/" + accountTypeId ,
                HttpMethod.PUT, entity, Object.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            cbsAccountType.setAccountTypeId(accountTypeId); // Assurez-vous que l'ID est correctement défini
            accountRepository.save(cbsAccountType);
            accountRepository.save(cbsAccountType);

        } else {
            throw new RuntimeException("Failed to update account type from Fineract");
        }
        return response.getBody();
    }

    public Object deleteAccountType(Integer accountTypeId) {

        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/savingsproducts/" + accountTypeId,
                HttpMethod.DELETE, entity, Object.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            accountRepository.deleteById(accountTypeId);
        } else {
            throw new RuntimeException("Failed to update account type from Fineract");
        }
        return response.getBody();
    }
    private ProductSavingFineractDto mapToFineract(Account cbsAccountType) {
        ProductSavingFineractDto fineractAccountType = new ProductSavingFineractDto();

        // Mapping des champs communs
        fineractAccountType.setName(cbsAccountType.getAccountTypeName());
        fineractAccountType.setShortName(ShortNameGenerator.generateShortName());
        fineractAccountType.setDescription(cbsAccountType.getDescription());
        fineractAccountType.setCurrencyCode("USD");
        fineractAccountType.setInMultiplesOf(1);
        fineractAccountType.setDigitsAfterDecimal(2);
        fineractAccountType.setLocale("en");
        fineractAccountType.setNominalAnnualInterestRate(cbsAccountType.getInterestRate());
        fineractAccountType.setInterestCompoundingPeriodType(7);
        fineractAccountType.setInterestPostingPeriodType(7);
        fineractAccountType.setInterestCalculationType(1);
        fineractAccountType.setInterestCalculationDaysInYearType(365);
        fineractAccountType.setAccountingRule(1);
        cbsAccountType.setMaintenanceFee(cbsAccountType.getMaintenanceFee());
        cbsAccountType.setMaintenancePeriodicity(cbsAccountType.getMaintenancePeriodicity());
        return fineractAccountType;
    }



}

