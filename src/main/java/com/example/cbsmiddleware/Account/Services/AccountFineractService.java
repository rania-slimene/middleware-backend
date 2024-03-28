package com.example.cbsmiddleware.Account.Services;

import com.example.cbsmiddleware.Account.Dto.*;
import com.example.cbsmiddleware.Account.Entities.Account;
import com.example.cbsmiddleware.Account.Repositories.AccountRepository;
import com.example.cbsmiddleware.Account.Enum.AccountStatus;
import com.example.cbsmiddleware.AccountType.Service.AbstractApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class AccountFineractService extends AbstractApiService {
    @Value("${fineract.api.url}")
    private String fineractApiUrl;
    @Autowired
    AccountRepository accountRepository;



    public Account getAccountByAccountNumber(String accountNumber) {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<Account> response = restTemplate.exchange(
                fineractApiUrl + "/savingsaccounts/external-id/" + accountNumber,
                HttpMethod.GET,
                entity,
                Account.class
        );
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        if (response.getStatusCode().is2xxSuccessful()) {


            if (optionalAccount.isPresent()) {
                return optionalAccount.get();
            } else {
                throw new RuntimeException("Account not found in local database");
            }
        }
        return optionalAccount.get();
    }
    public Account deleteAccount(String  accountNumber) {

        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<Account> response = restTemplate.exchange(fineractApiUrl + "/savingsproducts/" + accountNumber,
                HttpMethod.DELETE, entity, Account.class);

        Optional<Account> optionalAccount = accountRepository.deleteByAccountNumber(accountNumber);

        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        } else {
            throw new RuntimeException("Account not found in local database");
        }
    }
    public Object addAccount(Account cbsAccount) {
        SavingsaccountsDtoFineract fineractAccount = mapToFineract(cbsAccount);
        Account findByAccountCode = accountRepository.findByAccountCode(cbsAccount.getAccountCode());
        Account findByIban = accountRepository.findByIban(cbsAccount.getIban());
        Account findByRib = accountRepository.findByRib(cbsAccount.getRib());

        if(findByAccountCode != null){
            throw  new RuntimeException("AccountCode already exist");
        }
        if(findByIban != null){
            throw  new RuntimeException("Iban already exist");
        }
        if(findByRib != null){
            throw  new RuntimeException("Rib already exist");
        }
        HttpHeaders headers = this.createHeaders();
        HttpEntity<SavingsaccountsDtoFineract> entity = new HttpEntity<>(fineractAccount, headers);

        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/savingsaccounts",
                HttpMethod.POST, entity, Object.class);
        accountRepository.save(cbsAccount);
        return response.getBody();
    }

    public Object updateAccount(Account cbsAccount, Integer accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if (optionalAccount.isPresent()) {
            SavingsaccountsDtoFineract fineractAccountType = mapToFineract(cbsAccount);
            HttpHeaders headers = this.createHeaders();
            HttpEntity<SavingsaccountsDtoFineract> entity = new HttpEntity<>(fineractAccountType, headers);
            ResponseEntity<Object> response = restTemplate.exchange(
                    fineractApiUrl + "/savingsaccounts/" + accountId,
                    HttpMethod.PUT,
                    entity,
                    Object.class
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                cbsAccount.setAccountId(accountId);
                accountRepository.save(cbsAccount);
            } else {

                throw new RuntimeException("Failed to update account from Fineract");
            }
            return response.getBody();
        } else {
            throw new RuntimeException("Account with ID " + accountId + " not found");
        }
    }

    public Object approveAccount(ApproveAccountDtoFineract ApproveAccount, String accountNumber){
        ApproveAccountDtoFineract approveAccount = new ApproveAccountDtoFineract();
        approveAccount.setApprovedOnDate(ApproveAccount.getApprovedOnDate());
        approveAccount.setDateFormat(ApproveAccount.getDateFormat());
        approveAccount.setLocale(ApproveAccount.getLocale());
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("command", "approve");

        HttpHeaders headers = this.createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fineractApiUrl + "/savingsaccounts/external-id/" + accountNumber)
                .queryParams(queryParams);
        HttpEntity<ApproveAccountDtoFineract> entity = new HttpEntity<>(approveAccount, headers);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Object.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            updateAccountStatusInCBS(accountNumber, AccountStatus.APPROVED);
        }
        return response.getBody();
    }

    private void updateAccountStatusInCBS(String accountNumber, AccountStatus accountStatus) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);

        if (optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            account.setAccountStatus(accountStatus);

            accountRepository.save(account);
        } else {
            throw new IllegalArgumentException("Account with id " + accountNumber + " does not exist.");
        }
    }


    public Object activeAcount(ActivateAccountDtoFineract ActivateAccount, String accountNumber){
        ActivateAccountDtoFineract ActivateAccount1 = new ActivateAccountDtoFineract();
        ActivateAccount1.setActivatedOnDate(ActivateAccount.getActivatedOnDate());
        ActivateAccount1.setLocale(ActivateAccount.getLocale());
        ActivateAccount1.setDateFormat(ActivateAccount.getDateFormat());
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("command", "activate");
        HttpHeaders headers = this.createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fineractApiUrl + "/savingsaccounts/external-id/" + accountNumber)
                .queryParams(queryParams);

        HttpEntity<ActivateAccountDtoFineract> entity = new HttpEntity<>(ActivateAccount1, headers);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Object.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            updateAccountStatusInCBS(accountNumber, AccountStatus.ACTIVE);
        }
        return response.getBody();
    }
    public Object inactiveAcount(CloseAccountFineractDto inactivateAccount, String accountNumber){
        CloseAccountFineractDto InactivateAccount = new CloseAccountFineractDto();
        InactivateAccount.setClosedOnDate(inactivateAccount.getClosedOnDate());
        InactivateAccount.setLocale("en");
        InactivateAccount.setDateFormat("dd MMMM yyyy");
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("command", "close");
        HttpHeaders headers = this.createHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fineractApiUrl + "/savingsaccounts/external-id/" + accountNumber)
                .queryParams(queryParams);

        HttpEntity<CloseAccountFineractDto> entity = new HttpEntity<>(InactivateAccount, headers);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Object.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            updateAccountStatusInCBS(accountNumber, AccountStatus.INACTIVE);
        }
        return response.getBody();
    }
    public Object getAllAccount() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/savingsaccounts",
                HttpMethod.GET, entity, Object.class);
        response.getBody();
        List<Account> responseBase = accountRepository.findAll();
        return responseBase;


    }



    private SavingsaccountsDtoFineract mapToFineract(Account cbsAccount) {
        SavingsaccountsDtoFineract fineractAccount = new SavingsaccountsDtoFineract();
        fineractAccount.setSubmittedOnDate(cbsAccount.getCreatioDate());
        fineractAccount.setExternalId(cbsAccount.getAccountNumber());
        cbsAccount.setAccountStatus(mapToCBSStatus(cbsAccount.getAccountStatus()));
        fineractAccount.setProductId(cbsAccount.getAccountType().getAccountTypeId()) ;
        fineractAccount.setProductId(cbsAccount.getAccountType().getAccountTypeId());
        fineractAccount.setClientId(1);
        fineractAccount.setDateFormat("dd MMMM yyyy");
        fineractAccount.setLocale("en");
        fineractAccount.setInterestCalculationDaysInYearType(365);
        fineractAccount.setNominalAnnualInterestRate(10.0);
        fineractAccount.setInterestCalculationType(1);
        fineractAccount.setInterestPostingPeriodType(7);
        fineractAccount.setInterestCompoundingPeriodType(7);
        cbsAccount.setAccountCode(cbsAccount.getAccountCode());
        cbsAccount.setAvailableBlance(cbsAccount.getAvailableBlance());
        cbsAccount.setAccountCodeSecurity(cbsAccount.getAccountCodeSecurity());
        cbsAccount.setEligibleCheckBook(cbsAccount.getEligibleCheckBook());
        cbsAccount.setAccountSubType(cbsAccount.getAccountSubType());
        cbsAccount.setFavorite(cbsAccount.getFavorite());
        cbsAccount.setCurrentBlance(cbsAccount.getCurrentBlance());
        cbsAccount.setLastClosingDate(cbsAccount.getLastClosingDate());
        cbsAccount.setIban(cbsAccount.getIban());
        cbsAccount.setBank(cbsAccount.getBank());
        cbsAccount.setMaxAmount(cbsAccount.getMaxAmount());
        cbsAccount.setMinAmount(cbsAccount.getMinAmount());
        cbsAccount.setOnHoldAmount(cbsAccount.getOnHoldAmount());
        cbsAccount.setOwnerName(cbsAccount.getOwnerName());
        cbsAccount.setSwift(cbsAccount.getSwift());

        return fineractAccount;
    }
    public static AccountStatus mapToCBSStatus(AccountStatus accountStatus) {
        SavingsAccountStatusDTO fineractStatus = new SavingsAccountStatusDTO();

        switch (accountStatus) {
            case PENDING:
                break;
            case ACTIVE:
                fineractStatus.setActive(true);
                break;
            case APPROVED:
                fineractStatus.setSubmittedAndPendingApproval(true);
                break;
            case INACTIVE:
                fineractStatus.setClosed(true);
                fineractStatus.setPrematureClosed(true);
                break;
            default:
                throw new IllegalArgumentException("Unknown CBS account status: " + accountStatus);
        }

        return accountStatus;
    }

}
