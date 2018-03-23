package com.inc.xy.locator.model

import spock.lang.Specification
import spock.lang.Unroll

class PointSearchParamTest extends Specification {

    def latitude = 30
    def longitude = 20
    def radius = 10

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

    def "should return latitudeParam"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .latitude(latitude)
                .longitude(longitude)
                .radius(radius)
                .build()

        when:
        def latitudeParam = param.getLatitudeParam()

        then:
        latitudeParam != null
        latitudeParam == latitude + radius
    }

    def "should return longitudeParam"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .latitude(latitude)
                .longitude(longitude)
                .radius(radius)
                .build()

        when:
        def longitudeParam = param.getLongitudeParam()

        then:
        longitudeParam != null
        longitudeParam == longitude + radius
    }

    def "should return null longitudeParam if PointSearchParam is not valid"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .latitude(null)
                .longitude(longitude)
                .radius(radius)
                .build()

        when:
        def longitudeParam = param.getLongitudeParam()

        then:
        longitudeParam == null
    }

    def "should return null latitude if PointSearchParam is not valid"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .latitude(this.latitude)
                .longitude(longitude)
                .radius(null)
                .build()

        when:
        def latitudeParam = param.getLatitudeParam()

        then:
        latitudeParam == null
    }
}
