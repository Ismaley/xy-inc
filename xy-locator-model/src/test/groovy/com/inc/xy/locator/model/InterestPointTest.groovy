package com.inc.xy.locator.model

import spock.lang.Specification
import spock.lang.Unroll

class InterestPointTest extends Specification {

    def "pointName should be valid"() {
        given:
        def point = buildInterestPoint("name", 22.2, 22.2)

        when:
        def isValid = point.hasValidName()

        then:
        isValid
    }

    @Unroll
    def "pointName should not be valid when name is #situation"() {
        given:
        def point = buildInterestPoint(name, 22.2, 22.2)

        when:
        def isValid = point.hasValidName()

        then:
        !isValid

        where:
        name    || situation
        ""      || "empty"
        null    || "null"
    }

    def "coordinates should be valid"() {
        given:
        def point = buildInterestPoint("name", 22.2, 22.2)

        when:
        def areValid = point.hasValidCoordinates()

        then:
        areValid
    }

    @Unroll
    def "coordinates should not be valid when #situation"() {
        given:
        def point = buildInterestPoint("name", xCoordinate, yCoordinate)

        when:
        def areValid = point.hasValidCoordinates()

        then:
        !areValid

        where:
        xCoordinate  || yCoordinate || situation
        null         || null        || "both coordinates are null"
        null         || 22.2        || "xCoordinate is null"
        22.2         || null        || "yCoordinate is null"
        22.2         || -22.2       || "yCoordinate is negative"
        -22.2        || 22.2        || "xCoordinate is negative"
        -22.2        || null        || "xCoordinate is negative and yCoordinate is null"
        null         || -22.2       || "xCoordinate is negative and yCoordinate is null"
        -22.2        || -22.2       || "both coordinates are negative"
    }

    InterestPoint buildInterestPoint(String pointName, Double xCoordinate, Double yCoordinate) {
        return InterestPoint.builder()
                .pointName(pointName)
                .yCoordinate(yCoordinate)
                .xCoordinate(xCoordinate)
                .build()
    }
}
