package com.xyzch.client.core.datasource.customer;

import java.util.List;

import org.mapstruct.Mapper;

import com.xyzch.client.core.entity.CustomerEntity;
import com.xyzch.client.core.model.Customer;

@Mapper
public interface BoundedMapperCustomer {

    Customer customerFromCustomerEntity(CustomerEntity entity);

    CustomerEntity customerEntityFromCustomer(Customer model);

    List<Customer> listCustomerFromCustomerEntity(List<CustomerEntity> entities);

    List<CustomerEntity> listCustomerEntityFromCustomer(List<Customer> models);
    
}
