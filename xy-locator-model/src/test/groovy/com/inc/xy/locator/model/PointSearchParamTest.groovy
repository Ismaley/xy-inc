package com.inc.xy.locator.model

import spock.lang.Specification
import spock.lang.Unroll

class PointSearchParamTest extends Specification {

    def xCoordinate = 30
    def yCoordinate = 20
    def radius = 10

    @Unroll
    def "param should be invalid when #situation"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .xCoordinate(xCoordinate)
                .yCoordinate(yCoordinate)
                .radius(radius)
                .build()

        when:
        def isValid = param.isValid()

        then:
        !isValid

        where:
        xCoordinate || yCoordinate || radius || situation
        null        || null        || null   || "all attributes null"
        null        || 22          || 22     || "xCoordinate is null"
        null        || null        || 22     || "yCoordinate is null"
        -22         || 22          || 22     || "xCoordinate is negative"
        22          || -22         || 22     || "yCoordinate is negative"
        22          || 22          || null   || "radius is null"
        22          || 22          || 0      || "radius is 0"
        22          || 22          || -22    || "radius is negative"
    }

    def "should return xCoordinateParam"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .xCoordinate(xCoordinate)
                .yCoordinate(yCoordinate)
                .radius(radius)
                .build()

        when:
        def xCoordinateParam = param.getXcoordinateParam()

        then:
        xCoordinateParam != null
        xCoordinateParam == xCoordinate + radius
    }

    def "should return yCoordinateParam"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .xCoordinate(xCoordinate)
                .yCoordinate(yCoordinate)
                .radius(radius)
                .build()

        when:
        def yCoordinateParam = param.getYcoordinateParam()

        then:
        yCoordinateParam != null
        yCoordinateParam == yCoordinate + radius
    }

    def "should return null yCoordinateParam if PointSearchParam is not valid"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .xCoordinate(null)
                .yCoordinate(yCoordinate)
                .radius(radius)
                .build()

        when:
        def yCoordinateParam = param.getYcoordinateParam()

        then:
        yCoordinateParam == null
    }

    def "should return null xCoordinateParam if PointSearchParam is not valid"() {
        given:
        PointSearchParam param = PointSearchParam.builder()
                .xCoordinate(xCoordinate)
                .yCoordinate(yCoordinate)
                .radius(null)
                .build()

        when:
        def xCoordinateParam = param.getXcoordinateParam()

        then:
        xCoordinateParam == null
    }
}
