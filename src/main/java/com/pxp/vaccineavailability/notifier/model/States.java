package com.pxp.vaccineavailability.notifier.model;

import java.util.List;

public class States {

    private List<StateModel> states;

    private int ttl;

    public States() {
    }

    public List<StateModel> getStates() {
        return states;
    }

    public void setStates(List<StateModel> states) {
        this.states = states;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }
}
