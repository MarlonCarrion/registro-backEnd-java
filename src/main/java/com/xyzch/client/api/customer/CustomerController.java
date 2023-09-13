package com.xyzch.client.api.customer;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyzch.client.core.customer.CustomerUC;
import com.xyzch.client.core.model.Customer;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerUC customerUC;

    private BoundedMapper boundedMapper = Mappers.getMapper(BoundedMapper.class);

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseGeneric> saveCustomer(@Valid @RequestBody RequestSaveCustomer requestCustomer) {
        Customer customer = boundedMapper.requestSaveCustomer(requestCustomer);
        customerUC.saveCustomer(customer);

        ResponseGeneric response = new ResponseGeneric();
        response.setMessage("Cliente guardado exitosamente!");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<ResponseGeneric>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseGetAllCustomer> getMethodName() {
        List<Customer> customers = customerUC.getAllCustomer();

        ResponseGetAllCustomer response = new ResponseGetAllCustomer();
        response.setCustomers(boundedMapper.responseCustomerList(customers));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<ResponseGetAllCustomer>(response, headers, HttpStatus.OK);
    }

}
