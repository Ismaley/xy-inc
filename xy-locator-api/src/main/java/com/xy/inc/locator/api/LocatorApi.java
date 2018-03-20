package com.xy.inc.locator.api;

import com.xy.inc.locator.api.to.InterestPointTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface LocatorApi {

    @PostMapping(value = {"/interest-points"})
    @ResponseBody
    InterestPointTO create(@Valid InterestPointTO interestPoint);

    @GetMapping(value = {"/interest-points"})
    @ResponseBody
    List<InterestPointTO> findAll();

    @GetMapping(value = {"/interest-points/byProximity"})
    @ResponseBody
    List<InterestPointTO> findByProximity(Float xCoordinate, Float yCoordinate, Float radius);
}
