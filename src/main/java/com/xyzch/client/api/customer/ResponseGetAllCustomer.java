package com.xyzch.client.api.customer;

import java.util.List;

import lombok.Data;

@Data
public class ResponseGetAllCustomer {
    private List<ResponseCustomer> customers;
}
