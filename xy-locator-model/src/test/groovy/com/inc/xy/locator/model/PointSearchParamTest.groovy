package com.inc.xy.locator.model

import spock.lang.Specification
import spock.lang.Unroll

class PointSearchParamTest extends Specification {

    def latitude = 30
    def longitude = 20
    def radius = 10

    def "should return latitudePlusRadius"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .latitude(latitude)
                .longitude(longitude)
                .radius(radius)
                .build()

        when:
        def latitudeParam = param.getLatitudePlusRadius()

        then:
        latitudeParam != null
        latitudeParam == latitude + radius
    }

    def "should return latitudeMinusRadius"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .latitude(latitude)
                .longitude(longitude)
                .radius(radius)
                .build()

        when:
        def latitudeParam = param.getLatitudeMinusRadius()

        then:
        latitudeParam != null
        latitudeParam == latitude - radius
    }

    def "should return longitudePlusRadius"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .latitude(latitude)
                .longitude(longitude)
                .radius(radius)
                .build()

        when:
        def longitudeParam = param.getLongitudePlusRadius()

        then:
        longitudeParam != null
        longitudeParam == longitude + radius
    }

    def "should return longitudeMinusRadius"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .latitude(latitude)
                .longitude(longitude)
                .radius(radius)
                .build()

        when:
        def longitudeParam = param.getLongitudeMinusRadius()

        then:
        longitudeParam != null
        longitudeParam == longitude - radius
    }


    def "should return null latitude if PointSearchParam is not valid"() {
        given:
        PointSearchParam invalidParam = PointSearchParam.builder()
                .latitude(latitude)
                .longitude(longitude)
                .radius(null)
                .build()

        when:
        def latitudePlusRadius = invalidParam.getLatitudePlusRadius()
        def latitudeMinusRadius = invalidParam.getLatitudeMinusRadius()
        def longitudePlusRadius = invalidParam.getLongitudePlusRadius()
        def longitudeMinusRadius = invalidParam.getLongitudeMinusRadius()

        then:
        latitudePlusRadius == null
        latitudeMinusRadius == null
        longitudePlusRadius == null
        longitudeMinusRadius == null
    }

    @Unroll
    def "param should be invalid when #situation"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .latitude(latitude)
                .longitude(longitude)
                .radius(radius)
                .build()

        when:
        def isValid = param.isValid()

        then:
        !isValid

        where:
        latitude || longitude || radius || situation
        null     || null      || null   || "all attributes null"
        null     || 22        || 22     || "latitude is null"
        null     || null      || 22     || "longitude is null"
        -22      || 22        || 22     || "latitude is negative"
        22       || -22       || 22     || "longitude is negative"
        22       || 22        || null   || "radius is null"
        22       || 22        || 0      || "radius is 0"
        22       || 22        || -22    || "radius is negative"
    }
}
