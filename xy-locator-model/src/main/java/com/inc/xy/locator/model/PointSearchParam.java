package com.inc.xy.locator.model;


import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PointSearchParam {

    private Integer xCoordinate;

    private Integer yCoordinate;

    private Integer radius;

    public Integer getXcoordinateParam() {
        if(isValid()) {
            return this.xCoordinate + this.radius;
        }
        return null;
    }

    public Integer getYcoordinateParam() {
        if(isValid()) {
            return this.yCoordinate + this.radius;
        }
        return null;
    }

    public boolean isValid() {
        return areNotNullFields() && isValidRadius() && areValidCoordinates();
    }

    private boolean areNotNullFields() {
        return this.radius != null && this.xCoordinate != null && this.yCoordinate != null;
    }

    private boolean isValidRadius() {
        return this.radius > 0;
    }

    private boolean areValidCoordinates() {
        return this.xCoordinate >= 0 && this.yCoordinate >= 0;
    }
}
