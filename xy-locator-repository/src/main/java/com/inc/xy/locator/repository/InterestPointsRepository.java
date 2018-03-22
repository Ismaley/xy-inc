package com.inc.xy.locator.repository;

import com.inc.xy.locator.model.InterestPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestPointsRepository extends JpaRepository<InterestPoint, Long> {

    List<InterestPoint> findByXCoordinateIsLessThanEqualAndYCoordinateIsLessThanEqual(Integer xCoordinateParam, Integer yCoordinateParam);

    InterestPoint findByPointName(String name);

    InterestPoint findByXCoordinateAndYCoordinate(Integer xCoordinate, Integer yCoordinate);
}
