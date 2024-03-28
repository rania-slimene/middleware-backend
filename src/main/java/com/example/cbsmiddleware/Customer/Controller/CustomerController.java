package com.example.cbsmiddleware.Customer.Controller;
import com.example.cbsmiddleware.Customer.Dto.ActivatedCutomer;
import com.example.cbsmiddleware.Customer.Entities.Customer;
import com.example.cbsmiddleware.Customer.Service.CustomerFineractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bankerise/api/customers")
public class CustomerController {
    @Autowired
    CustomerFineractService customerFineractService ;
    @PostMapping("/addCustomer")
    public ResponseEntity<Object> addCustomer(@RequestBody Customer CBScustomer) {
        Object response = customerFineractService.addCustomer(CBScustomer);
        return ResponseEntity.ok(response);
    }
    @PutMapping("updateCustomer/{customerId}")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer CustomerCBS , @PathVariable Integer customerId ) {
        Object response = customerFineractService.updateCustomer(CustomerCBS, customerId);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("deleteCustomer/{customerNumber}")
    public Object deleteCustomer(@PathVariable Integer customerNumber ){
        return customerFineractService.deleteCustomer(customerNumber);
    }
    @GetMapping("listCustomers")
    public Object getAllClientsFromFineract() {
        return customerFineractService.getAllClient();
    }
    @GetMapping("/searchByLastName")
    public List<Customer> findByLastName(@RequestParam String lastName) {
        return customerFineractService.findByLastName(lastName);
    }


    @PostMapping("activateCustomer/{id}")
    public Object ActivateCustomer( @PathVariable Integer id, @RequestBody ActivatedCutomer activateCustomer) {
        return customerFineractService.activeCustomer(activateCustomer, id);

}
}

