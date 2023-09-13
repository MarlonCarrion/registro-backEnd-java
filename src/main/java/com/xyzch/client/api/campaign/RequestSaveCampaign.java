package com.xyzch.client.api.campaign;

import lombok.Data;

@Data
public class RequestSaveCampaign {
    private String name;
    private String email;
    private String phone;
    private String groupBenefit;
}
