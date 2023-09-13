package com.xyzch.client.core.campaign;

import java.util.List;

import com.xyzch.client.core.model.Campaign;
import com.xyzch.client.core.model.Customer;

public interface CampaignUC {
    public String saveCampaign(Customer customer, Campaign campaign);

    public List<Campaign> getAllCampaign();
}
