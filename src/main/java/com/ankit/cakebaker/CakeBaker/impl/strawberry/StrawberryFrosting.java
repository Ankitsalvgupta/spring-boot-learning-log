package com.ankit.cakebaker.CakeBaker.impl.strawberry;

import com.ankit.cakebaker.CakeBaker.model.Frosting;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("strawFrost")
public class StrawberryFrosting implements Frosting {

    @Override
    public String getFrostingType() {
        return "Strawberry Frosting";
    }
}
