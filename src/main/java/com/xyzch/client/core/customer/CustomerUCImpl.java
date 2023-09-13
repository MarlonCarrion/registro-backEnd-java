package com.xyzch.client.core.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyzch.client.core.datasource.customer.CustomerDataStore;
import com.xyzch.client.core.model.Customer;

@Component
public class CustomerUCImpl implements CustomerUC {
    @Autowired
    CustomerDataStore customerDS;

    @Override
    public void saveCustomer(Customer customer) {
        Customer customerExist = customerDS.getCustomerByName(customer.getName());
        if (customerExist == null)
            customerDS.saveCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerDS.getAllCustomer();
    }

    @Override
    public Customer getCustomerByName(String name) {
        return customerDS.getCustomerByName(name);
    }

}
