package com.xyzch.client.core.datasource.customer;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyzch.client.core.entity.CustomerEntity;
import com.xyzch.client.core.model.Customer;

@Component
public class CustomerDataStoreImpl implements CustomerDataStore {
    @Autowired
    CustomerRepository customerRepository;

    BoundedMapperCustomer boundedMapperCustomer = Mappers.getMapper(BoundedMapperCustomer.class);

    @Override
    public void saveCustomer(Customer customer) {
        CustomerEntity customerEntity = boundedMapperCustomer.customerEntityFromCustomer(customer);
        customerRepository.save(customerEntity);
    }

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customers = boundedMapperCustomer.listCustomerFromCustomerEntity(customerRepository.findAll());
        return customers;
    }

    @Override
    public Customer getCustomerByName(String name) {
        return boundedMapperCustomer.customerFromCustomerEntity(customerRepository.getCustomerByName(name));
    }

    @Override
    public List<Customer> getAllCustomerByNames(List<String> names) {
        return boundedMapperCustomer.listCustomerFromCustomerEntity(customerRepository.getAllCustomerByNames(names));
    }
}
