package com.xyzch.client.core.datasource.campaign;

import java.util.List;

import org.mapstruct.Mapper;

import com.xyzch.client.core.entity.CampaignEntity;
import com.xyzch.client.core.model.Campaign;

@Mapper
public interface BoundedMapperCampaign {
    Campaign campaignFromCampaignEntity(CampaignEntity entity);

    CampaignEntity campaignEntityFromCampaign(Campaign model);

    List<Campaign> listCampaignFromCampaignEntity(List<CampaignEntity> entities);

    List<CampaignEntity> listCampaignEntityFromCampaign(List<Campaign> models);
}
