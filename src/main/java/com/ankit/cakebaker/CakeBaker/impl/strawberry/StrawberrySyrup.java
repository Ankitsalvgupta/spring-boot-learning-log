package com.ankit.cakebaker.CakeBaker.impl.strawberry;

import com.ankit.cakebaker.CakeBaker.model.Syrup;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("strawSyrup")
public class StrawberrySyrup implements Syrup {

    @Override
    public String getSyrupType() {
        return "Strawberry Syrup";
    }
}
