package com.inc.xy.locator.repository;

import com.inc.xy.locator.model.InterestPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InterestPointsRepository extends JpaRepository<InterestPoint, Long> {

    List<InterestPoint> findByLatitudeIsLessThanEqualAndLongitudeIsLessThanEqual(Integer latitude, Integer longitude);

    InterestPoint findByPointName(String name);

    InterestPoint findByLatitudeAndLongitude(Integer latitude, Integer longitude);
}
