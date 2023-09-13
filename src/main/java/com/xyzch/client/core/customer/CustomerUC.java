package com.xyzch.client.core.customer;

import java.util.List;

import com.xyzch.client.core.model.Customer;

public interface CustomerUC {

    public void saveCustomer(Customer customer);

    public List<Customer> getAllCustomer();

    public Customer getCustomerByName(String name);

}
