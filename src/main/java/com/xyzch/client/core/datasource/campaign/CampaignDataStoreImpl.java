package com.xyzch.client.core.datasource.campaign;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xyzch.client.core.model.Campaign;

@Component
public class CampaignDataStoreImpl implements CampaignDataStore {
    @Autowired
    CampaignRepository campaignRepository;

    BoundedMapperCampaign boundedMapperCampaign = Mappers.getMapper(BoundedMapperCampaign.class);

    @Override
    public void saveRegister(Campaign campaign) {
        campaignRepository.save(boundedMapperCampaign.campaignEntityFromCampaign(campaign));
    }

    @Override
    public List<Campaign> getAllCampaign() {
        return boundedMapperCampaign.listCampaignFromCampaignEntity(campaignRepository.findAll());
    }

    @Override
    public List<Campaign> getAllCampaignByGroup(String group) {
        return boundedMapperCampaign.listCampaignFromCampaignEntity(campaignRepository.getAllCampaignByGroup(group));
    }

}
