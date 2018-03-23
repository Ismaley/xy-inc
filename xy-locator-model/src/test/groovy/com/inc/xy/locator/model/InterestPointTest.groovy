package com.inc.xy.locator.model

import spock.lang.Specification
import spock.lang.Unroll

class InterestPointTest extends Specification {

    Integer latitude = 22
    Integer longitude = 22

    def "pointName should be valid"() {
        given:
        def point = buildInterestPoint("name", latitude, longitude)

        when:
        def isValid = point.hasValidName()

        then:
        isValid
    }

    @Unroll
    def "pointName should not be valid when name is #situation"() {
        given:
        def point = buildInterestPoint(name, latitude, longitude)

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
        def point = buildInterestPoint("name", latitude, longitude)

        when:
        def areValid = point.hasValidCoordinates()

        then:
        areValid
    }

    @Unroll
    def "coordinates should not be valid when #situation"() {
        given:
        def point = buildInterestPoint("name", latitudeParam, longitudeParam)

        when:
        def areValid = point.hasValidCoordinates()

        then:
        !areValid

        where:
        latitudeParam   || longitudeParam   || situation
        null            || null             || "both coordinates are null"
        null            || 22               || "latitude is null"
        22              || null             || "longitude is null"
        22              || -22              || "longitude is negative"
        -22             || 22               || "latitude is negative"
        -22             || null             || "latitude is negative and longitude is null"
        null            || -22              || "latitude is negative and longitude is null"
        -22             || -22              || "both coordinates are negative"
    }

    InterestPoint buildInterestPoint(String pointName, Integer latitude, Integer longitude) {
        return InterestPoint.builder()
                .pointName(pointName)
                .longitude(longitude)
                .latitude(latitude)
                .build()
    }
}
