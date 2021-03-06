package com.inc.xy.locator.service

import com.inc.xy.locator.model.InterestPoint
import com.inc.xy.locator.model.PointSearchParam
import com.inc.xy.locator.repository.InterestPointsRepository
import com.inc.xy.locator.service.exceptions.BusinessException
import com.inc.xy.locator.service.impl.InterestPointsServiceImpl
import spock.lang.Specification

import static com.inc.xy.locator.service.exceptions.BusinessException.ErrorCode.*

class InterestPointServiceTest extends Specification {

    InterestPointsRepository repository = Mock()

    InterestPointsService service = new InterestPointsServiceImpl(repository)

    InterestPoint interestPoint

    def latitude = 22
    def longitude = 30

    def setup() {
        interestPoint = InterestPoint.builder()
                .id(1L)
                .pointName("name")
                .longitude(latitude)
                .latitude(longitude)
                .build()
    }

    def "should create point"() {
        given:
        def point = buildInterestPoint("name", latitude, longitude)

        when:
        service.create(point)

        then:
        1 * repository.findByPointName(point.getPointName())
        1 * repository.save(point)
    }

    def "should not create point with invalid name"() {
        given:
        def point = buildInterestPoint(null, latitude, longitude)

        when:
        service.create(point)

        then:
        def e = thrown(BusinessException)
        0 * repository.findByPointName(_)
        0 * repository.save(_)
        e.getMessage() == INVALID_NAME.message
    }

    def "should not create point with duplicated name"() {
        given:
        def point = buildInterestPoint("name", latitude, longitude)

        when:
        service.create(point)

        then:
        1 * repository.findByPointName(point.getPointName()) >> interestPoint
        def e = thrown(BusinessException)
        0 * repository.save(_)
        e.getMessage() == DUPLICATED_NAME.message
    }

    def "should not create point with invalid coordinates"() {
        given:
        def point = buildInterestPoint("name", null, longitude)

        when:
        service.create(point)

        then:
        def e = thrown(BusinessException)
        0 * repository.findByPointName(_)
        0 * repository.save(_)
        e.getMessage() == INVALID_COORDINATES.message
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
            .latitude(20)
            .longitude(10)
            .radius(10)
            .build()

        def dinnerPlace = InterestPoint.builder()
            .pointName("dinnerPlace")
            .latitude(27)
            .longitude(12)
            .build()

        def steakHouse = InterestPoint.builder()
                .pointName("steakHouse")
                .latitude(28)
                .longitude(2)
                .build()

        when:
        def points = service.findByProximity(searchParam)

        then:
        1 * repository.findByLatitudeIsBetweenAndLongitudeIsBetween(searchParam.getLatitudeMinusRadius(),
                searchParam.getLatitudePlusRadius(), searchParam.getLongitudeMinusRadius(),
                searchParam.getLongitudePlusRadius()) >> Arrays.asList(dinnerPlace, steakHouse)
        points.size() == 1
        points.get(0) == dinnerPlace

    }

    def "should not find points by proximity if searchParam is invalid"() {
        given:
        def searchParam = PointSearchParam.builder()
                .latitude(latitude)
                .longitude(longitude)
                .radius(-10)
                .build()

        when:
        service.findByProximity(searchParam)

        then:
        def e = thrown(BusinessException)
        0 * repository.findByLatitudeIsBetweenAndLongitudeIsBetween(_, _, _, _)
        e.getMessage() == INVALID_SEARCH_PARAM.message
    }

    InterestPoint buildInterestPoint(String pointName, Integer latitude, Integer longitude) {
        return InterestPoint.builder()
                .pointName(pointName)
                .longitude(longitude)
                .latitude(latitude)
                .build()
    }
}
