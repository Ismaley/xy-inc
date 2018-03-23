package com.inc.xy.locator.repository

import com.inc.xy.locator.model.InterestPoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

@ContextConfiguration
@SpringBootTest(classes = RepositoryTestConfig.class)
class InterestPointRepositoryTest extends Specification {

    @Autowired
    InterestPointsRepository repository

    def setup() {
        repository.deleteAll()
    }

    def "should not save interest point with duplicated name"(){
        given:
        repository.save(buildInterestPoint("point1", 22, 55))

        when:
        repository.save(buildInterestPoint("point1", 22, 22))

        then:
        thrown(DataIntegrityViolationException)
    }

    @Unroll
    def "should not save interest point with null #field"(){
        when:
        repository.save(buildInterestPoint(name, latitude, longitude))

        then:
        thrown(DataIntegrityViolationException)

        where:
        name    || latitude || longitude || field
        null    || 22          || 22          || "name"
        "name"  || null        || 22          || "latitude"
        "name"  || 22          || null        || "longitude"
        "name"  || null        || null        || "latitude and longitude"
        null    || null        || null        || "all fields"
    }

    def "should find points inside given coordinates"() {
        given:
        repository.save(buildInterestPoint("point1", 22, 32))
        repository.save(buildInterestPoint("point2", 22, 61))
        repository.save(buildInterestPoint("point3", 12, 15))

        when:
        def foundPoints = repository.findByLatitudeIsLessThanEqualAndLongitudeIsLessThanEqual(22, 32)

        then:
        foundPoints.size() == 2
        foundPoints.get(0).getPointName() == "point1"
        foundPoints.get(1).getPointName() == "point3"
    }

    def "should find point by coordinates"() {
        given:
        def latitude = 22
        def longitude = 32
        InterestPoint point = repository.save(buildInterestPoint("point1", latitude, longitude))
        def pointId = point.getId()

        when:
        def foundPoint = repository.findByLatitudeAndLongitude(latitude, longitude)

        then:
        foundPoint != null
        foundPoint.getId() == pointId
        foundPoint.getLatitude() == latitude
        foundPoint.getLongitude() == longitude
    }

    InterestPoint buildInterestPoint(String pointName, Integer latitude, Integer longitude) {
        return InterestPoint.builder()
            .pointName(pointName)
            .longitude(longitude)
            .latitude(latitude)
            .build()
    }
}
