package com.xyzch.client.api.campaign;

import java.util.List;

import org.mapstruct.Mapper;

import com.xyzch.client.core.model.Campaign;
import com.xyzch.client.core.model.Customer;

import jakarta.validation.Valid;

@Mapper
public interface BoundedMapper {

    Customer requestSaveCustomer(@Valid RequestSaveCampaign requestCampaign);

    Campaign requestCampaign(@Valid RequestSaveCampaign requestSaveCampaign);

    ResponseCampaign responseCampaign(Campaign Campaign);

    List<ResponseCampaign> responseCampaingList(List<Campaign> compaignList);
}
