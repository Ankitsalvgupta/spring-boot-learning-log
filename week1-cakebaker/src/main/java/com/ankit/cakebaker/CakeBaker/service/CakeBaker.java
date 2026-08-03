package com.ankit.cakebaker.CakeBaker.service;

import com.ankit.cakebaker.CakeBaker.model.Frosting;
import com.ankit.cakebaker.CakeBaker.model.Syrup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CakeBaker {

    private final Frosting frosting;

    private final Syrup syrup;

    public CakeBaker(@Qualifier("chocoFrost") Frosting frosting,
                     @Qualifier("chocoSyrup") Syrup syrup) {
        this.frosting = frosting;
        this.syrup = syrup;
    }

    public void bakeCake() {
        System.out.println("Baking cake with frosting type: " + frosting.getFrostingType());
        System.out.println("Baking cake with syrup type: " + syrup.getSyrupType());
    }
}
