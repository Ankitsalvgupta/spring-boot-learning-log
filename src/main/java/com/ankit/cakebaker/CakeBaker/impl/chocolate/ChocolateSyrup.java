package com.ankit.cakebaker.CakeBaker.impl.chocolate;

import com.ankit.cakebaker.CakeBaker.model.Syrup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("chocoSyrup")
public class ChocolateSyrup implements Syrup {

    @Override
    public String getSyrupType() {
        return "Chocolate Syrup";
    }
}
