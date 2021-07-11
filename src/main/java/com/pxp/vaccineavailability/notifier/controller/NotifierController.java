package com.pxp.vaccineavailability.notifier.controller;

import com.pxp.vaccineavailability.notifier.model.Centers;
import com.pxp.vaccineavailability.notifier.model.Districts;
import com.pxp.vaccineavailability.notifier.model.StateModel;
import com.pxp.vaccineavailability.notifier.model.States;
import com.pxp.vaccineavailability.notifier.services.NotifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NotifierController {

    @Autowired
    private NotifierService notifierService;

    @RequestMapping(value = "hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping(value = "getstates", method = RequestMethod.GET)
    public States getStates(){
        return notifierService.getStates();
    }

    @RequestMapping(value = "getdistricts", method = RequestMethod.GET)
    public Districts getDistricts(){
        return notifierService.getDistricts();
    }

    @RequestMapping(value = "getcenters", method = RequestMethod.GET)
    public Centers getCenters(){
        Centers centers = notifierService.getCenters();
        centers.getCenters().stream().filter(center -> center.getSessions().stream().filter(session -> session.getAvailable_capacity() > 0).count() > 0).forEach(center -> {
            System.out.println("***********************************Start***********************************************");
            System.out.println("Name of the center: " + center.getName());
            System.out.println("Address: " + center.getAddress());
            System.out.println("State Name: " + center.getState_name());
            System.out.println("District Name: " + center.getDistrict_name());
            System.out.println("Block Name: " + center.getBlock_name());
            System.out.println("Pincode: " + center.getPincode());
            System.out.println("Paid or Free: " + center.getFee_type());
            System.out.println("***********************************Session Details start*****************************");
            center.getSessions().stream().filter(session -> session.getAvailable_capacity() > 0).forEach(session -> {
                System.out.println("Session date: " + session.getDate());
                System.out.println("Vaccine Name: " + session.getVaccine());
                System.out.println("Capacity: " + session.getAvailable_capacity());
                System.out.println("Available slots: " + session.getSlots().stream().collect(Collectors.joining(", ")));
                System.out.println("Dose 1 capacity: " + session.getAvailable_capacity_dose1());
                System.out.println("Dose 2 capacity: " + session.getAvailable_capacity_dose2());
            });
            System.out.println("***********************************Session Details end*******************************");
            System.out.println("***********************************End*************************************************");
        });
        return centers;
    }
}
