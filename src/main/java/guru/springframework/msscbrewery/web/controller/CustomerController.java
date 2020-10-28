package guru.springframework.msscbrewery.web.controller;

import guru.springframework.msscbrewery.services.CustomerService;
import guru.springframework.msscbrewery.web.model.CustomerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping({"/{customerId}"})
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId")UUID customerId) {
        return new ResponseEntity<CustomerDto>(customerService.getCustomerById(customerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto dto = customerService.saveCustomer(customerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", customerDto.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @PutMapping({"/{customerId}"})
    public ResponseEntity updateCustomer(@PathVariable("customerId") UUID customerId) {
        customerService.updateCustomer(customerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
