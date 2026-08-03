package com.ankit.cakebaker.CakeBaker.impl.chocolate;

import com.ankit.cakebaker.CakeBaker.model.Frosting;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("chocoFrost")
public class ChocolateFrosting implements Frosting {

    @Override
    public String getFrostingType() {
        return "Chocolate Frosting";
    }
}
