package com.example.cbsmiddleware.Customer.Service;
import com.example.cbsmiddleware.AccountType.Service.AbstractApiService;
import com.example.cbsmiddleware.Customer.Dto.ActivatedCutomer;
import com.example.cbsmiddleware.Customer.Dto.CustomerFineractDto;
import com.example.cbsmiddleware.Customer.Dto.CustomerStatusDTO;
import com.example.cbsmiddleware.Customer.Entities.Customer;
import com.example.cbsmiddleware.Customer.Enum.ClientStatus;
import com.example.cbsmiddleware.Customer.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class CustomerFineractService extends AbstractApiService {
    @Value("${fineract.api.url}")
    private String fineractApiUrl;


    @Autowired
    CustomerRepository customerRepository;
    public Object addCustomer(Customer CustomerCBS) {
        CustomerFineractDto fineractCustomer = mapToFineract(CustomerCBS);
        Customer findByemail= customerRepository.findCustomerByEmail(CustomerCBS.getEmail());
        Customer findBycustomernumber=customerRepository.findCustomersByMobile(CustomerCBS.getMobile());
        Customer findBycustomerCin=customerRepository.findCustomersByCin(CustomerCBS.getCin());
        Customer findBycustomerFax=customerRepository.findCustomersByFax(CustomerCBS.getFax());
        if(findByemail != null){
            throw  new RuntimeException("email already exist");
        }
        if(findBycustomernumber!= null){
            throw  new RuntimeException("mobile already exist");
        }

        if(findBycustomerCin!= null){
            throw  new RuntimeException("Cin already exist");
        }
        if(findBycustomerFax!= null){
            throw  new RuntimeException("fax already exist");
        }
        HttpHeaders headers = this.createHeaders();
        HttpEntity<CustomerFineractDto> entity = new HttpEntity<>(fineractCustomer, headers);

        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients",
                HttpMethod.POST, entity, Object.class);
        customerRepository.save(CustomerCBS);
        return response.getBody();
    }
    public Object getAllClient() {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Customer> response = restTemplate.exchange(fineractApiUrl + "/clients", HttpMethod.GET, entity, Customer.class);
        response.getBody();
        return customerRepository.findAll();
    }
    public Object getCustomerFromFineract(Integer customerId) {
        HttpHeaders headers = this.createHeaders();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<Customer> response = restTemplate.exchange(fineractApiUrl + "/clients/"+ customerId, HttpMethod.GET, entity, Customer.class);
        response.getBody();
        return customerRepository.findAll();
    }
    public List<Customer> findByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }
    public Object updateCustomer(Customer customerCBS, Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            CustomerFineractDto fineractCustomer = mapToFineract(customerCBS);
            HttpHeaders headers = this.createHeaders();
            HttpEntity<CustomerFineractDto> entity = new HttpEntity<>(fineractCustomer, headers);

            ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients/" + customerId,
                    HttpMethod.PUT, entity, Object.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                customerCBS.setId(customerId); // Assurez-vous que l'ID est correctement défini
                customerRepository.save(customerCBS);
                syncCustomerWithFineract(customerId); // Synchroniser avec Fineract après la mise à jour
            } else {
                throw new RuntimeException("Failed to update account type from Fineract");
            }
            return response.getBody();
        } else {
            throw new RuntimeException("Customer with ID " + customerId + " not found");
        }
    }

    private void syncCustomerWithFineract(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            CustomerFineractDto fineractCustomer = (CustomerFineractDto) getCustomerFromFineract(customerId);
            Customer updatedCustomer = mapToYourLocalModel(fineractCustomer);
            customerRepository.save(updatedCustomer);
        } else {
            throw new IllegalArgumentException("Customer with ID " + customerId + " does not exist.");
        }
    }
    public Object deleteCustomer(Integer CustomerId) {

        HttpHeaders headers = this.createHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(fineractApiUrl + "/clients/" +CustomerId,
                HttpMethod.DELETE, entity, Object.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            customerRepository.deleteById(CustomerId);
        } else {
            throw new RuntimeException("Failed to update Customer from Fineract");
        }
        return response.getBody();
    }
    private void updateCustomerStatusInCBS(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
           customer.setCustomerStatus(ClientStatus.ACTIVE);

            customerRepository.save(customer);
        } else {
            throw new IllegalArgumentException("Account with id " + customerId + " does not exist.");
        }
    }
    public Object activeCustomer(ActivatedCutomer ActivatedCustomer, Integer id){
        ActivatedCutomer ActivatedCutomer1 = new ActivatedCutomer();
        ActivatedCutomer1.setActivationDate(ActivatedCustomer.getActivationDate());
        ActivatedCutomer1.setLocale(ActivatedCustomer.getLocale());
        ActivatedCutomer1.setDateFormat(ActivatedCustomer.getDateFormat());

        // Définir la valeur du paramètre "command"
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        queryParams.add("command", "activate");

        HttpHeaders headers = this.createHeaders();
        // Ajouter les paramètres de requête à l'URL
       UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fineractApiUrl + "/clients/" + id)
                .queryParams(queryParams);

        HttpEntity<ActivatedCutomer> entity = new HttpEntity<>(ActivatedCustomer, headers);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, Object.class);
        if (response.getStatusCode() == HttpStatus.OK) {
           updateCustomerStatusInCBS(id);
        }
        return response.getBody();


    }
    private CustomerFineractDto mapToFineract(Customer CustomerCBS) {
        CustomerFineractDto fineractCustomer= new CustomerFineractDto();
        CustomerCBS.setCustomerStatus(mapToCBSStatus(CustomerCBS.getCustomerStatus()));
        fineractCustomer.setFirstname(CustomerCBS.getFirstName());
        fineractCustomer.setLastname(CustomerCBS.getLastName());
        fineractCustomer.setMiddlename(CustomerCBS.getNickName());
        fineractCustomer.setDateOfBirth(CustomerCBS.getBirthDate());
        fineractCustomer.setExternalId(CustomerCBS.getCustomerNumber());
        fineractCustomer.setOfficeId(1);
        fineractCustomer.setLocale("en");
         fineractCustomer.setLegalFormId(1);
        fineractCustomer.setMobileNo(CustomerCBS.getMobile());
        fineractCustomer.setActive(false);
        fineractCustomer.setSubmittedOnDate(CustomerCBS.getJoinDate());
        fineractCustomer.setDateFormat("dd MMMM yyyy");
        CustomerCBS.setCity(CustomerCBS.getCity());
        CustomerCBS.setFullAddress(CustomerCBS.getFullAddress());;
        CustomerCBS.setLanguage(CustomerCBS.getLanguage());
        CustomerCBS.setProfileImage(CustomerCBS.getProfileImage());
        CustomerCBS.setIsVIP(CustomerCBS.getIsVIP());
        CustomerCBS.setPassword(CustomerCBS.getPassword());
        CustomerCBS.setFax(CustomerCBS.getFax());
        CustomerCBS.setNationality(CustomerCBS.getNationality());
        CustomerCBS.setCin(CustomerCBS.getCin());
        CustomerCBS.setPostCode(CustomerCBS.getPostCode());

        return fineractCustomer;
    }


    private Customer mapToYourLocalModel(CustomerFineractDto fineractCustomer) {
        Customer customer = new Customer();
        customer.setFirstName(fineractCustomer.getFirstname());
        customer.setLastName(fineractCustomer.getLastname());
        customer.setNickName(fineractCustomer.getMiddlename());
        customer.setBirthDate(fineractCustomer.getDateOfBirth());
        customer.setCustomerNumber(fineractCustomer.getExternalId());
        customer.setMobile(fineractCustomer.getMobileNo());
        customer.setJoinDate(fineractCustomer.getSubmittedOnDate());
        return customer;
    }

        public static ClientStatus mapToCBSStatus(ClientStatus CustomerStatus) {
        CustomerStatusDTO fineractStatus = new CustomerStatusDTO();

        switch (CustomerStatus) {
            case ACTIVE -> fineractStatus.setACTIVE(true);
            case PENDING -> fineractStatus.setPENDING(true);
            case INACTIVE -> fineractStatus.setCLOSED(true);
            default -> throw new IllegalArgumentException("Unknown CBS Customer status: " + CustomerStatus);
        }

        return CustomerStatus;
    }



}
