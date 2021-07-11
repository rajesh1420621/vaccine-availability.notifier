package com.pxp.vaccineavailability.notifier.services;

import com.pxp.vaccineavailability.notifier.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Comparator;

@Service
public class NotifierService {

    @Autowired
    private RestTemplate restTemplate;

    public States getStates(){
        States statesJson = new States();
        try {
            HttpHeaders headers=new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
            HttpEntity<String> entity=new HttpEntity<String>(headers);
            statesJson = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/admin/location/states", HttpMethod.GET,entity,States.class).getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        statesJson.getStates().sort(Comparator.comparing(StateModel::getState_id));
        statesJson.setTtl(statesJson.getStates().size());
        return statesJson;
    }

    public Districts getDistricts(){
        Districts districtsJson = new Districts();
        try {
            HttpHeaders headers=new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
            HttpEntity<String> entity=new HttpEntity<String>(headers);
            districtsJson = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/admin/location/districts/26", HttpMethod.GET,entity,Districts.class).getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        districtsJson.getDistricts().sort(Comparator.comparing(DistrictModel::getDistrict_id));
        districtsJson.setTtl(districtsJson.getDistricts().size());
        return districtsJson;
    }

    public Centers getCenters(){
        Centers centersJson = new Centers();
        try {
            HttpHeaders headers=new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36");
            HttpEntity<String> entity=new HttpEntity<String>(headers);
            centersJson = restTemplate.exchange("https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=446&date=12-07-2021", HttpMethod.GET,entity,Centers.class).getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return centersJson;
    }
}
