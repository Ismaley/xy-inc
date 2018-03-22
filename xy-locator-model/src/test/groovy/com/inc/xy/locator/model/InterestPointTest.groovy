package com.inc.xy.locator.model

import spock.lang.Specification
import spock.lang.Unroll

class InterestPointTest extends Specification {

    Integer xCoordinate = 22
    Integer yCoordinate = 22

    def "pointName should be valid"() {
        given:
        def point = buildInterestPoint("name", xCoordinate, yCoordinate)

        when:
        def isValid = point.hasValidName()

        then:
        isValid
    }

    @Unroll
    def "pointName should not be valid when name is #situation"() {
        given:
        def point = buildInterestPoint(name, xCoordinate, yCoordinate)

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
        def point = buildInterestPoint("name", xCoordinate, yCoordinate)

        when:
        def areValid = point.hasValidCoordinates()

        then:
        areValid
    }

    @Unroll
    def "coordinates should not be valid when #situation"() {
        given:
        def point = buildInterestPoint("name", xCoordinateParam, yCoordinateParam)

        when:
        def areValid = point.hasValidCoordinates()

        then:
        !areValid

        where:
        xCoordinateParam  || yCoordinateParam || situation
        null              || null             || "both coordinates are null"
        null              || 22               || "xCoordinate is null"
        22                || null             || "yCoordinate is null"
        22                || -22              || "yCoordinate is negative"
        -22               || 22               || "xCoordinate is negative"
        -22               || null             || "xCoordinate is negative and yCoordinate is null"
        null              || -22              || "xCoordinate is negative and yCoordinate is null"
        -22               || -22              || "both coordinates are negative"
    }

    InterestPoint buildInterestPoint(String pointName, Integer xCoordinate, Integer yCoordinate) {
        return InterestPoint.builder()
                .pointName(pointName)
                .yCoordinate(yCoordinate)
                .xCoordinate(xCoordinate)
                .build()
    }
}
