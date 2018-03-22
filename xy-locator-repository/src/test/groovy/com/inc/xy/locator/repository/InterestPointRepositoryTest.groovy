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
    InterestPointRepository repository

    def setup() {
        repository.deleteAll()
    }

    def "should not save interest point with duplicated name"(){
        given:
        repository.save(buildInterestPoint("point1", 22.6, 55.8))

        when:
        repository.save(buildInterestPoint("point1", 22.2, 22.3))

        then:
        thrown(DataIntegrityViolationException)
    }

    @Unroll
    def "should not save interest point with null #field"(){
        when:
        repository.save(buildInterestPoint(name, xCoordinate, yCoordinate))

        then:
        thrown(DataIntegrityViolationException)

        where:
        name    || xCoordinate || yCoordinate || field
        null    || 22.2        || 22.2        || "name"
        "name"  || null        || 22.2        || "xCoordinate"
        "name"  || 22.2        || null        || "yCoordinate"
        "name"  || null        || null        || "xCoordinate and yCoordinate"
        null    || null        || null        || "all fields"
    }

    def "should find points inside given coordinates"() {
        given:
        repository.save(buildInterestPoint("point1", 22.2, 32.2))
        repository.save(buildInterestPoint("point2", 22.2, 61.2))
        repository.save(buildInterestPoint("point3", 12.2, 15.2))

        when:
        def foundPoints = repository.findByXCoordinateIsLessThanEqualAndYCoordinateIsLessThanEqual(22.2, 32.2)

        then:
        foundPoints.size() == 2
        foundPoints.get(0).getPointName() == "point1"
        foundPoints.get(1).getPointName() == "point3"
    }

    InterestPoint buildInterestPoint(String pointName, Double xCoordinate, Double yCoordinate) {
        return InterestPoint.builder()
            .pointName(pointName)
            .yCoordinate(yCoordinate)
            .xCoordinate(xCoordinate)
            .build()
    }
}
