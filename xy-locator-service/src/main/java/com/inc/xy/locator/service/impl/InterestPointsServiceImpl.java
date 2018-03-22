package com.inc.xy.locator.service.impl;

import com.inc.xy.locator.model.InterestPoint;
import com.inc.xy.locator.repository.InterestPointsRepository;
import com.inc.xy.locator.service.InterestPointsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class InterestPointsServiceImpl implements InterestPointsService {

    private InterestPointsRepository repository;

    @Autowired
    public InterestPointsServiceImpl(InterestPointsRepository repository) {
        this.repository = repository;
    }

    public InterestPoint create(InterestPoint interestPoint) {
        verifyDuplicate(interestPoint.getPointName());
        return repository.save(interestPoint);
    }

    public List<InterestPoint> findAll() {
        return null;
    }

    public List<InterestPoint> findByProximity(Float xCoordinate, Float yCoordinate, Float radius) {
        return null;
    }

    private void verifyDuplicate(String pointName) {
        Optional.ofNullable(repository.findByPointName(pointName)).ifPresent(p -> {throw new RuntimeException("found");});
    }
}
