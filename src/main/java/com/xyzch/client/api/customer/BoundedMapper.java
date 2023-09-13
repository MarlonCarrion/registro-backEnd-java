package com.xyzch.client.api.customer;

import java.util.List;

import org.mapstruct.Mapper;

import com.xyzch.client.core.model.Customer;

import jakarta.validation.Valid;

@Mapper
public interface BoundedMapper {

    Customer requestSaveCustomer(@Valid RequestSaveCustomer requestCustomer);

    List<ResponseCustomer> responseCustomerList(List<Customer> customerList);

    ResponseCustomer responseCustomer(Customer customer);

}
