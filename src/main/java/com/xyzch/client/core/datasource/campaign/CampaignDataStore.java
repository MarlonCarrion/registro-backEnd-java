package com.xyzch.client.core.datasource.campaign;

import java.util.List;

import com.xyzch.client.core.model.Campaign;

public interface CampaignDataStore {
    public void saveRegister(Campaign campaign);

    public List<Campaign> getAllCampaign();

    public List<Campaign> getAllCampaignByGroup(String group);
}
