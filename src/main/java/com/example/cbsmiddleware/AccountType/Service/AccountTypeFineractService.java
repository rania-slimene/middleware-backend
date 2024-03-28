package com.example.cbsmiddleware.AccountType.Service;

import com.example.cbsmiddleware.AccountType.Dto.*;
import com.example.cbsmiddleware.AccountType.Entities.AccountType;
import com.example.cbsmiddleware.AccountType.Repositories.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AccountTypeFineractService extends AbstractApiService {

    @Value("${fineract.api.url}")
    private String fineractApiUrl;
    @Autowired
    AccountTypeRepository accountTypeRepository;
//    @Autowired
//    AccountTypeMapper accountTypeMapper;

    public Object getAllAccountType() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/savingsproducts",
                HttpMethod.GET, entity, Object.class);
        response.getBody();
        List<AccountType> responseBase = accountTypeRepository.findAll();
        return responseBase;

    }

    public Object addAccountType(AccountType cbsAccountType) {
        ProductSavingFineractDto fineractAccountType = mapToFineract(cbsAccountType);
        // ProductSavingFineractDto fineractAccountType = accountTypeMapper.mapToFineract(cbsAccountType);
//        cbsAccountType.setAccountTypeName(cbsAccountType.getAccountTypeName());
//        cbsAccountType.setDescription(cbsAccountType.getDescription());
//        cbsAccountType.setMaintenanceFee(cbsAccountType.getMaintenanceFee());
//        cbsAccountType.setInterestRate(cbsAccountType.getInterestRate());

        HttpHeaders headers = this.createHeaders();
        HttpEntity<ProductSavingFineractDto> entity = new HttpEntity<>(fineractAccountType, headers);

        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/savingsproducts",
                HttpMethod.POST, entity, Object.class);
        accountTypeRepository.save(cbsAccountType);
        return response.getBody();
    }

    public Object updateAccountType(AccountType cbsAccountType, Integer accountTypeId) {
        Optional<AccountType> optionalAccount = accountTypeRepository.findById(accountTypeId);
        if (optionalAccount.isPresent()) {
        ProductSavingFineractDto fineractAccountType = mapToFineract(cbsAccountType);
        //ProductSavingFineractDto fineractAccountType = accountTypeMapper.mapToFineract(cbsAccountType);
        HttpHeaders headers = this.createHeaders();
        HttpEntity<ProductSavingFineractDto> entity = new HttpEntity<>(fineractAccountType, headers);

        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/savingsproducts/" + accountTypeId,
                HttpMethod.PUT, entity, Object.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                cbsAccountType.setAccountTypeId(accountTypeId);
                accountTypeRepository.save(cbsAccountType);
            } else {

                throw new RuntimeException("Failed to update account from Fineract");
            }
            return response.getBody();
        } else {
            throw new RuntimeException("Account with ID " + accountTypeId + " not found");
        }
    }


    @Transactional
    public Object deleteAccountType(Integer accountTypeId) {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                fineractApiUrl + "/savingsaccounts/external-id/" + accountTypeId,
                HttpMethod.DELETE,
                entity,
                Object.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            accountTypeRepository.deleteById(accountTypeId);
        } else {
            throw new RuntimeException("Failed to delete account from Fineract");
        }

        return response.getBody();
    }


    private ProductSavingFineractDto mapToFineract(AccountType cbsAccountTypeType) {
        ProductSavingFineractDto fineractAccountType = new ProductSavingFineractDto();


        fineractAccountType.setName(cbsAccountTypeType.getAccountTypeName());
        fineractAccountType.setShortName(ShortNameGenerator.generateShortName());
        fineractAccountType.setDescription(cbsAccountTypeType.getDescription());
        fineractAccountType.setCurrencyCode("USD");
        fineractAccountType.setInMultiplesOf(1);
        fineractAccountType.setDigitsAfterDecimal(2);
        fineractAccountType.setLocale("en");
        fineractAccountType.setNominalAnnualInterestRate(cbsAccountTypeType.getInterestRate());
        fineractAccountType.setInterestCompoundingPeriodType(7);
        fineractAccountType.setInterestPostingPeriodType(7);
        fineractAccountType.setInterestCalculationType(1);
        fineractAccountType.setInterestCalculationDaysInYearType(365);
        fineractAccountType.setAccountingRule(1);
        cbsAccountTypeType.setMaintenanceFee(cbsAccountTypeType.getMaintenanceFee());
        cbsAccountTypeType.setMaintenancePeriodicity(cbsAccountTypeType.getMaintenancePeriodicity());
        return fineractAccountType;
    }







}