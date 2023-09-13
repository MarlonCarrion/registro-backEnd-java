package com.xyzch.client.core.datasource.customer;

import java.util.List;

import com.xyzch.client.core.model.Customer;

public interface CustomerDataStore {

    public void saveCustomer(Customer customer);

    public List<Customer> getAllCustomer();

    public Customer getCustomerByName(String name);

}
