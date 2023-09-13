package com.xyzch.client.api.customer;

import lombok.Data;

@Data
public class ResponseCustomer {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
