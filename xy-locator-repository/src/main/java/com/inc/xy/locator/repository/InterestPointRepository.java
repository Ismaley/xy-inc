package com.inc.xy.locator.repository;

import com.inc.xy.locator.model.InterestPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestPointRepository extends JpaRepository<InterestPoint, Long> {

    List<InterestPoint> findByXCoordinateIsLessThanEqualAndYCoordinateIsLessThanEqual(Double xCoordinate, Double yCoordinate);

}
