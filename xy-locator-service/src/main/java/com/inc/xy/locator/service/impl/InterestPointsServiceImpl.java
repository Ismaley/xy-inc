package com.inc.xy.locator.service.impl;

import com.inc.xy.locator.model.InterestPoint;
import com.inc.xy.locator.model.PointSearchParam;
import com.inc.xy.locator.repository.InterestPointsRepository;
import com.inc.xy.locator.service.InterestPointsService;
import com.inc.xy.locator.service.exceptions.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.inc.xy.locator.service.exceptions.BusinessException.ErrorCode.*;

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
        return repository.save(interestPoint);
    }

    public List<InterestPoint> findAll() {
        return repository.findAll();
    }

    public List<InterestPoint> findByProximity(PointSearchParam param) {
        validateSearchParam(param);
        List<InterestPoint> nearbyPoints = repository.findByLatitudeIsBetweenAndLongitudeIsBetween(param.getLatitudeMinusRadius(), param.getLatitudePlusRadius(),
                param.getLongitudeMinusRadius(), param.getLongitudePlusRadius());
        return nearbyPoints.stream().filter(p -> isInsideSearchRadius(p, param)).collect(Collectors.toList());
    }

    private boolean isInsideSearchRadius(InterestPoint point, PointSearchParam param) {
        return param.getRadius() >= Math.sqrt(Math.pow(point.getLatitude() - param.getLatitude(), 2) + Math.pow(point.getLongitude() - param.getLongitude(), 2));
    }

    private void verifyDuplicateName(String pointName) {
        Optional.ofNullable(repository.findByPointName(pointName)).ifPresent(p -> {throw new BusinessException(DUPLICATED_NAME);});
    }

    private void validateFields(InterestPoint interestPoint) {
        if(!interestPoint.hasValidName()) throw new BusinessException(INVALID_NAME);
        if(!interestPoint.hasValidCoordinates()) throw new BusinessException(INVALID_COORDINATES);
    }

    private void validateSearchParam(PointSearchParam param) {
        if(!param.isValid()) throw new BusinessException(INVALID_SEARCH_PARAM);
    }
}
