package com.example.cbsmiddleware.Bank.Services;

import com.example.cbsmiddleware.Bank.Dto.OfficeFineractDto;
import com.example.cbsmiddleware.AccountType.Service.AbstractApiService;
import com.example.cbsmiddleware.Bank.Entities.Bank;
import com.example.cbsmiddleware.Bank.Repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankFineractService  extends AbstractApiService {

    @Value("${fineract.api.url}")
    private String fineractApiUrl;

    @Autowired
    BankRepository bankRepository;



    public Object getListBank(){
        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/offices",
                HttpMethod.GET, entity, Object.class);
        response.getBody();
        List<Bank> responseBase = bankRepository.findAll();
        return responseBase;

    }
   public Object addBank (Bank cbsBank){

       OfficeFineractDto fineractoffice = mapToFineract(cbsBank);
       HttpHeaders headers = this.createHeaders();
       HttpEntity<OfficeFineractDto> entity = new HttpEntity<>(fineractoffice, headers);

       ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/offices",
               HttpMethod.POST, entity, Object.class);
       bankRepository.save(cbsBank);
       return response.getBody();
   }

   public  Object updateBank (Bank cbsBank , Integer id){
       OfficeFineractDto fineractoffice = mapToFineract(cbsBank);
       HttpHeaders headers = this.createHeaders();
       HttpEntity<OfficeFineractDto> entity = new HttpEntity<>(fineractoffice, headers);
       ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/offices/" + id ,
               HttpMethod.PUT, entity, Object.class);
       bankRepository.save(cbsBank);
       return response.getBody();

    }



   public  OfficeFineractDto mapToFineract (Bank cbsBank){
       OfficeFineractDto fineractOffice = new OfficeFineractDto();


       fineractOffice.setName(cbsBank.getBankName());
       fineractOffice.setExternalId(cbsBank.getBankBic());
       fineractOffice.setParentId(1);
       fineractOffice.setOpeningDate(cbsBank.getCreatedDate());
       fineractOffice.setDateFormat("dd MMMM yyyy");
       fineractOffice.setLocale("en");
       cbsBank.setBankEmail(cbsBank.getBankEmail());
       cbsBank.setCode(cbsBank.getCode());
       cbsBank.setCityName(cbsBank.getCityName());
       cbsBank.setBankLogo(cbsBank.getBankLogo());
       cbsBank.setBankPhone(cbsBank.getBankPhone());
       cbsBank.setLastUpdated(cbsBank.getLastUpdated());
       cbsBank.setLatitude(cbsBank.getLatitude());
       cbsBank.setLongitude(cbsBank.getLongitude());
       cbsBank.setCountryName(cbsBank.getCountryName());
       return fineractOffice;

   }

}
