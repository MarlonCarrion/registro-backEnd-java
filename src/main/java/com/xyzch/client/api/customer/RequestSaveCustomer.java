package com.xyzch.client.api.customer;

import lombok.Data;

@Data
public class RequestSaveCustomer {
    private String name;
    private String email;
    private String phone;
}
