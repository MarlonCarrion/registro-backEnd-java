package com.xyzch.client.api.campaign;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyzch.client.core.campaign.CampaignUC;
import com.xyzch.client.core.customer.CustomerUC;
import com.xyzch.client.core.model.Campaign;
import com.xyzch.client.core.model.Customer;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/campaign")
public class CampaignController {
    @Autowired
    CampaignUC campaignUC;

    @Autowired
    CustomerUC customerUC;

    private BoundedMapper boundedMapper = Mappers.getMapper(BoundedMapper.class);

    @PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseSaveCampaign> save(@Valid @RequestBody RequestSaveCampaign requestRegisterCampaign) {
        Customer customer = boundedMapper.requestSaveCustomer(requestRegisterCampaign);
        Campaign campaign = boundedMapper.requestCampaign(requestRegisterCampaign);
        String message = campaignUC.saveCampaign(customer, campaign);

        ResponseSaveCampaign response = new ResponseSaveCampaign();
        response.setMessage(message);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<ResponseSaveCampaign>(response, headers, HttpStatus.OK);
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<ResponseCampaigns> getAllCampaign() {
        List<Campaign> compaignList = campaignUC.getAllCampaign();
        List<Customer> customers = customerUC
                .getAllCustomerByNames(compaignList.stream().map(Campaign::getName).collect(Collectors.toList()));

        List<ResponseCampaign> responseList = new ArrayList<>();
        for (Campaign campaign : compaignList) {
            ResponseCampaign responseTemp = new ResponseCampaign();
            Optional<Customer> customer = customers.stream().filter(c -> campaign.getName().equals(c.getName()))
                    .findFirst();

            responseTemp.setName(campaign.getName());
            responseTemp.setGroupBenefit(campaign.getGroupBenefit());
            responseTemp.setBenefit(campaign.getBenefit());

            if (customer.isPresent()) {
                responseTemp.setEmail(customer.get().getEmail());
                responseTemp.setPhone(customer.get().getPhone());
            }
            responseList.add(responseTemp);
        }
        ResponseCampaigns response = new ResponseCampaigns();
        response.setCampaigns(responseList);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom-Header", "CustomHeaderValue");
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<ResponseCampaigns>(response, headers, HttpStatus.OK);
    }
}
