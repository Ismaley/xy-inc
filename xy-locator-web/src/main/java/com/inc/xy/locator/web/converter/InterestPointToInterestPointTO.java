package com.inc.xy.locator.web.converter;


import com.inc.xy.locator.api.to.InterestPointTO;
import com.inc.xy.locator.model.InterestPoint;
import org.modelmapper.PropertyMap;

public class InterestPointToInterestPointTO extends PropertyMap<InterestPoint, InterestPointTO> {

    @Override
    protected void configure() {
        map().setId(source.getId());
        map().setPointName(source.getPointName());
        map().setXCoordinate(source.getXCoordinate());
        map().setYCoordinate(source.getYCoordinate());
    }


}
