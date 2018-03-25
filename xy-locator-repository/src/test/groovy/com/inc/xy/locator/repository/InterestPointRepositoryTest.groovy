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
        repository.save(buildInterestPoint("point1", 1, 3))
        repository.save(buildInterestPoint("point2", 3, 4))
        repository.save(buildInterestPoint("point3", 3, 3))
        repository.save(buildInterestPoint("point4", 3, 2))
        repository.save(buildInterestPoint("point5", 4, 1))
        repository.save(buildInterestPoint("point6", 2, 3))

        when:
        def foundPoints = repository.findByLatitudeIsBetweenAndLongitudeIsBetween(1, 3, 2, 4)

        then:
        foundPoints.size() == 5
        foundPoints.get(0).getPointName() == "point1"
        foundPoints.get(1).getPointName() == "point2"
        foundPoints.get(2).getPointName() == "point3"
        foundPoints.get(3).getPointName() == "point4"
        foundPoints.get(4).getPointName() == "point6"
    }


    InterestPoint buildInterestPoint(String pointName, Integer latitude, Integer longitude) {
        return InterestPoint.builder()
            .pointName(pointName)
            .longitude(longitude)
            .latitude(latitude)
            .build()
    }
}
