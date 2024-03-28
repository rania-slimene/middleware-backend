package com.example.cbsmiddleware.PermanentTransferManagement.Service;
import com.example.cbsmiddleware.AccountType.Service.AbstractApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class standingInstructionFineractService extends AbstractApiService {
    @Value("${fineract.api.url}")
    private String fineractApiUrl;

 //   @Autowired
 //   PermenantTransactionRepository PermenantTransactionRepository;

//    public Object addCustomer(PermanentTransfer CustomerCBS) {
//        standingInstructionFineractDto fineractCustomer = mapToFineract(CustomerCBS);
//        Customer findByemail = CustomerRepository.findCustomerByEmail(CustomerCBS.getEmail());
//        Customer findBycustomernumber = CustomerRepository.findCustomersByMobile(CustomerCBS.getMobile());
//        if (findByemail != null) {
//            throw new RuntimeException("email already exist");
//        }
//        if (findBycustomernumber != null) {
//            throw new RuntimeException("mobile already exist");
//        }
//        HttpHeaders headers = this.createHeaders();
//        HttpEntity<CustomerFineractDto> entity = new HttpEntity<>(fineractCustomer, headers);
//
//        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients",
//                HttpMethod.POST, entity, Object.class);
//        CustomerRepository.save(CustomerCBS);
//        return response.getBody();
//    }
//
//
//    private standingInstructionFineractDto mapToFineract(PermanentTransfer cbsPermanentTransfer) {
//        standingInstructionFineractDto fineractstandingInstruction = new standingInstructionFineractDto();
//        fineractstandingInstruction.setInstructionType(Integer.valueOf(cbsPermanentTransfer.getTransferType()));
//        fineractstandingInstruction.setToClientId(Integer.valueOf(cbsPermanentTransfer.getRibReceiver()));
//        fineractstandingInstruction.setToAccountId(Integer.valueOf(cbsPermanentTransfer.getRibReceiver()));
//        fineractstandingInstruction.setAmount(String.valueOf(cbsPermanentTransfer.getAmount()));
//        ;
//        fineractstandingInstruction.setFromAccountType(1);
//        fineractstandingInstruction.setClientId(1);
//        fineractstandingInstruction.setDateFormat("dd MMMM yyyy");
//        fineractstandingInstruction.setLocale("en");
//        fineractstandingInstruction.setInterestCalculationDaysInYearType(365);
//        fineractstandingInstruction.setNominalAnnualInterestRate(10.0);
//        fineractstandingInstruction.setInterestCalculationType(1);
//        fineractstandingInstruction.setInterestPostingPeriodType(7);
//        fineractstandingInstruction.setInterestCompoundingPeriodType(7);
//        fineractstandingInstruction.setFromOfficeId(cbsPermanentTransfer.getAccountCode());
//
//        cbsAccount.setAvailableBlance(cbsAccount.getAvailableBlance());
//        cbsAccount.setAccountCodeSecurity(cbsAccount.getAccountCodeSecurity());
//        cbsAccount.setEligibleCheckBook(cbsAccount.getEligibleCheckBook());
//        cbsAccount.setAccountSubType(cbsAccount.getAccountSubType());
//        cbsAccount.setFavorite(cbsAccount.getFavorite());
//        cbsAccount.setCurrentBlance(cbsAccount.getCurrentBlance());
//        cbsAccount.setLastClosingDate(cbsAccount.getLastClosingDate());
//        cbsAccount.setIban(cbsAccount.getIban());
//        cbsAccount.setBank(cbsAccount.getBank());
//        cbsAccount.setMaxAmount(cbsAccount.getMaxAmount());
//        cbsAccount.setMinAmount(cbsAccount.getMinAmount());
//        cbsAccount.setOnHoldAmount(cbsAccount.getOnHoldAmount());
//        cbsAccount.setOwnerName(cbsAccount.getOwnerName());
//        cbsAccount.setSwift(cbsAccount.getSwift());
//  return  null ;
//    }
}