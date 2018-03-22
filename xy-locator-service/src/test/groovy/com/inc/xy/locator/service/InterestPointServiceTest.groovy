package com.inc.xy.locator.service

import com.inc.xy.locator.model.InterestPoint
import com.inc.xy.locator.repository.InterestPointsRepository
import com.inc.xy.locator.service.impl.InterestPointsServiceImpl
import spock.lang.Specification

class InterestPointServiceTest extends Specification {

    InterestPointsRepository repository = Mock()

    InterestPointsService service = new InterestPointsServiceImpl(repository)

    InterestPoint interestPoint

    def setup() {
        interestPoint = InterestPoint.builder()
                .id(1L)
                .pointName("name")
                .yCoordinate(33.235)
                .xCoordinate(23.323)
                .build()
    }

    def "should create point"() {
        given:
        def point = buildInterestPoint("name", 22.45, 22.45)

        when:
        service.create(point)

        then:
        1 * repository.findByPointName(point.getPointName())
        1 * repository.save(point)
    }

    def "should not create point with invalid name"() {
        given:
        def point = buildInterestPoint(null, 22.45, 22.45)

        when:
        service.create(point)

        then:
        1 * repository.findByPointName(point.getPointName())
        thrown(RuntimeException)
        0 * repository.save(point)
    }

    def "should not create point with duplicated invalid name"() {
        given:
        def point = buildInterestPoint("name", 22.45, 22.45)

        when:
        service.create(point)

        then:
        1 * repository.findByPointName(point.getPointName()) >> interestPoint
        thrown(RuntimeException)
        0 * repository.save(point)
    }

    def "should not create point with invalid coordinates"() {
        given:
        def point = buildInterestPoint("name", null, 22.45)

        when:
        service.create(point)

        then:
        1 * repository.findByPointName(point.getPointName())
        thrown(RuntimeException)
        0 * repository.save(point)
    }

    def "should find all points"() {
        when:
        def points = service.findAll()

        then:
        1 * repository.findAll() >> Arrays.asList(interestPoint, interestPoint)
        points.size() == 2
    }



    InterestPoint buildInterestPoint(String pointName, Double xCoordinate, Double yCoordinate) {
        return InterestPoint.builder()
                .pointName(pointName)
                .yCoordinate(yCoordinate)
                .xCoordinate(xCoordinate)
                .build()
    }

}
