package com.xy.inc.locator.api;

import com.xy.inc.locator.api.to.InterestPointTO;
import org.springframework.web.bind.annotation.PostMapping;

public interface LocatorApi {

    @PostMapping(value = {"/interest-points"})
    InterestPointTO create(InterestPointTO interestPoint);

}
