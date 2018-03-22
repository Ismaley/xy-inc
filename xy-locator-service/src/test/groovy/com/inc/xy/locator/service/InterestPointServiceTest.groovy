package com.inc.xy.locator.service

import com.inc.xy.locator.model.InterestPoint
import com.inc.xy.locator.model.PointSearchParam
import com.inc.xy.locator.repository.InterestPointsRepository
import com.inc.xy.locator.service.impl.InterestPointsServiceImpl
import spock.lang.Specification

class InterestPointServiceTest extends Specification {

    InterestPointsRepository repository = Mock()

    InterestPointsService service = new InterestPointsServiceImpl(repository)

    InterestPoint interestPoint

    def xCoordinate = 22
    def yCoordinate = 30

    def setup() {
        interestPoint = InterestPoint.builder()
                .id(1L)
                .pointName("name")
                .yCoordinate(xCoordinate)
                .xCoordinate(yCoordinate)
                .build()
    }

    def "should create point"() {
        given:
        def point = buildInterestPoint("name", xCoordinate, yCoordinate)

        when:
        service.create(point)

        then:
        1 * repository.findByPointName(point.getPointName())
        1 * repository.findByXCoordinateAndYCoordinate(xCoordinate, yCoordinate)
        1 * repository.save(point)
    }

    def "should not create point with invalid name"() {
        given:
        def point = buildInterestPoint(null, xCoordinate, yCoordinate)

        when:
        service.create(point)

        then:
        thrown(RuntimeException)
        0 * repository.findByPointName(point.getPointName())
        0 * repository.findByXCoordinateAndYCoordinate(xCoordinate, yCoordinate)
        0 * repository.save(point)
    }

    def "should not create point with duplicated name"() {
        given:
        def point = buildInterestPoint("name", xCoordinate, yCoordinate)

        when:
        service.create(point)

        then:
        1 * repository.findByPointName(point.getPointName()) >> interestPoint
        thrown(RuntimeException)
        0 * repository.findByXCoordinateAndYCoordinate(xCoordinate, yCoordinate)
        0 * repository.save(point)
    }

    def "should not create point with duplicated coordinates"() {
        given:
        def point = buildInterestPoint("name", xCoordinate, yCoordinate)

        when:
        service.create(point)

        then:
        1 * repository.findByPointName(point.getPointName())
        1 * repository.findByXCoordinateAndYCoordinate(xCoordinate, yCoordinate) >> interestPoint
        thrown(RuntimeException)
        0 * repository.save(point)
    }

    def "should not create point with invalid coordinates"() {
        given:
        def point = buildInterestPoint("name", null, yCoordinate)

        when:
        service.create(point)

        then:
        thrown(RuntimeException)
        0 * repository.findByPointName(point.getPointName())
        0 * repository.findByXCoordinateAndYCoordinate(null, yCoordinate)
        0 * repository.save(point)
    }

    def "should find all points"() {
        when:
        def points = service.findAll()

        then:
        1 * repository.findAll() >> Arrays.asList(interestPoint, interestPoint)
        points.size() == 2
    }

    def "should find points by proximity"() {
        given:
        def searchParam = PointSearchParam.builder()
            .xCoordinate(xCoordinate)
            .yCoordinate(yCoordinate)
            .radius(10)
            .build()

        when:
        def points = service.findByProximity(searchParam)

        then:
        1 * repository.findByXCoordinateIsLessThanEqualAndYCoordinateIsLessThanEqual(searchParam.getXcoordinateParam(),
                searchParam.getYcoordinateParam()) >> Arrays.asList(interestPoint, interestPoint)
        points.size() == 2
    }

    def "should not find points by proximity if searchParam is invalid"() {
        given:
        def searchParam = PointSearchParam.builder()
                .xCoordinate(xCoordinate)
                .yCoordinate(yCoordinate)
                .radius(-10)
                .build()

        when:
        service.findByProximity(searchParam)

        then:
        thrown(RuntimeException)
        0 * repository.findByXCoordinateIsLessThanEqualAndYCoordinateIsLessThanEqual(searchParam.getXcoordinateParam(),
                searchParam.getYcoordinateParam())
    }

    InterestPoint buildInterestPoint(String pointName, Integer xCoordinate, Integer yCoordinate) {
        return InterestPoint.builder()
                .pointName(pointName)
                .yCoordinate(yCoordinate)
                .xCoordinate(xCoordinate)
                .build()
    }
}
