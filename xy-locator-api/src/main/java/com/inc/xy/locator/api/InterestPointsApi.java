package com.inc.xy.locator.api;

import com.inc.xy.locator.api.to.InterestPointTO;
import com.inc.xy.locator.api.to.PointSearchParamTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface InterestPointsApi {

    @PostMapping(value = {"/interest-points"})
    @ResponseBody
    InterestPointTO create(@Valid InterestPointTO interestPoint);

    @GetMapping(value = {"/interest-points"})
    @ResponseBody
    List<InterestPointTO> findAll();

    @GetMapping(value = {"/interest-points/byProximity"})
    @ResponseBody
    List<InterestPointTO> findByProximity(PointSearchParamTO param);
}
