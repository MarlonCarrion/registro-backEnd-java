package com.xyzch.client.core.campaign;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xyzch.client.core.customer.CustomerUC;
import com.xyzch.client.core.datasource.campaign.CampaignDataStore;
import com.xyzch.client.core.model.Campaign;
import com.xyzch.client.core.model.Customer;

@Component
public class CampaignUCImpl implements CampaignUC {
    @Autowired
    CampaignDataStore campaignDS;

    @Autowired
    CustomerUC customerUC;

    @Override
    public String saveCampaign(Customer customer, Campaign campaign) {
        String message = "Cliente registrado correctamente!";
        List<String> remainingBenefits = new ArrayList<>();
        List<Campaign> campaignsSaved = campaignDS.getAllCampaignByGroup(campaign.getGroupBenefit());
        // SAVE CUSTOMER IF NO EXIST
        Customer customerExist = customerUC.getCustomerByName(customer.getName());
        if (customerExist == null) {
            customerUC.saveCustomer(customer);
        }
        // CONTROL IF NAME EXIST IN CAMPAIGN YET
        Optional<Campaign> campaignExist = campaignsSaved.stream().filter(c -> c.getName().equals(campaign.getName()))
                .findFirst();
        if (campaignExist.isPresent()) {
            return "Felicidades! El registro ya existe.";
        }

        switch (campaign.getGroupBenefit()) {
            case "TH":
                remainingBenefits = getListDataFromXml("th_formato.xml");
                System.out.println(remainingBenefits);
                break;
            case "SK":
                remainingBenefits = getListDataFromJson("/sk_formato.json");
                System.out.println(remainingBenefits);
                break;
        }
        if (campaignsSaved.size() > 0)
            for (Campaign campaignSaved : campaignsSaved) {
                remainingBenefits.removeIf(r -> r.equals(campaignSaved.getBenefit()));
            }
        if (remainingBenefits.size() > 0) {
            campaign.setBenefit(remainingBenefits.get(0));
            campaignDS.saveRegister(campaign);
        } else {
            return "Mil disculpas! El sistema ya no tiene beneficios disponibles";
        }
        return message;
    }

    @Override
    public List<Campaign> getAllCampaign() {
        return campaignDS.getAllCampaign();
    }

    public List<String> getListDataFromJson(String filePath) {
        List<String> sk = new ArrayList<>();
        JsonNode json = readJson(filePath).get("sk_formato");

        for (JsonNode jsonNode : json) {
            sk.add(jsonNode.get("beneficio").asText());
        }
        return sk;
    }

    private JsonNode readJson(String filePath) {
        try {
            ClassPathResource resource = new ClassPathResource(filePath);

            byte[] fileBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            String jsonContent = new String(fileBytes, StandardCharsets.UTF_8);

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readTree(jsonContent);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<String> getListDataFromXml(String filePath) {
        List<String> th = new ArrayList<>();
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(filePath);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(inputStream);

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("beneficio");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String elementValue = element.getTextContent();
                th.add(elementValue.strip());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return th;
    }
}