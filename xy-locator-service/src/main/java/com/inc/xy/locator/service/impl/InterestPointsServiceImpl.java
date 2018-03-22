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
        validateFields(interestPoint);
        verifyDuplicateName(interestPoint.getPointName());
        verifyDuplicateCoordinates(interestPoint);
        return repository.save(interestPoint);
    }

    public List<InterestPoint> findAll() {
        return repository.findAll();
    }

    public List<InterestPoint> findByProximity(Integer xCoordinate, Integer yCoordinate, Integer radius) {
        return null;
    }

    private void verifyDuplicateName(String pointName) {
        Optional.ofNullable(repository.findByPointName(pointName)).ifPresent(p -> {throw new RuntimeException("duplicated name");});
    }

    private void verifyDuplicateCoordinates(InterestPoint interestPoint) {
        Optional.ofNullable(repository.findByXCoordinateAndYCoordinate(interestPoint.getXCoordinate(),
                interestPoint.getYCoordinate())).ifPresent(p -> {throw new RuntimeException("duplicated coordinates");});
    }

    private void validateFields(InterestPoint interestPoint) {
        if(!interestPoint.hasValidName()) throw new RuntimeException("bad name");
        if(!interestPoint.hasValidCoordinates()) throw new RuntimeException("bad coordinates");
    }
}
