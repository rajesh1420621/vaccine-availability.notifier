package com.pxp.vaccineavailability.notifier.model;

import java.util.List;

public class Districts {

    private List<DistrictModel> districts;

    private int ttl;

    public Districts() {
    }

    public List<DistrictModel> getDistricts() {
        return districts;
    }

    public void setDistricts(List<DistrictModel> districts) {
        this.districts = districts;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
