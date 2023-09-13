package com.xyzch.client.core.datasource.campaign;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.xyzch.client.core.entity.CampaignEntity;

public interface CampaignRepository extends JpaRepository<CampaignEntity, Long>{
    
    @Query("select t from CampaignEntity t where t.groupBenefit=?1")
    public List<CampaignEntity> getAllCampaignByGroup(String group);
}
