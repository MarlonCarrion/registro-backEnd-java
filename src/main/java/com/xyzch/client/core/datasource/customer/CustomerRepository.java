package com.xyzch.client.core.datasource.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xyzch.client.core.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    @Query("select t from CustomerEntity t where t.name=?1")
    public CustomerEntity getCustomerByName(String name);

    @Query("select t from CustomerEntity t where t.name in ?1")
    public List<CustomerEntity> getAllCustomerByNames(List<String> names);

}
