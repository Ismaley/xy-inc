package com.inc.xy.locator.model;


import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PointSearchParam {

    private Integer latitude;

    private Integer longitude;

    private Integer radius;

    public Integer getLatitudePlusRadius() {
        if(isValid()) {
            return this.latitude + this.radius;
        }
        return null;
    }

    public Integer getLatitudeMinusRadius() {
        if(isValid()) {
            return this.latitude - this.radius;
        }
        return null;
    }

    public Integer getLongitudePlusRadius() {
        if(isValid()) {
            return this.longitude + this.radius;
        }
        return null;
    }

    public Integer getLongitudeMinusRadius() {
        if(isValid()) {
            return this.longitude - this.radius;
        }
        return null;
    }

    public boolean isValid() {
        return areNotNullFields() && isValidRadius() && areValidCoordinates();
    }

    private boolean areNotNullFields() {
        return this.radius != null && this.latitude != null && this.longitude != null;
    }

    private boolean isValidRadius() {
        return this.radius > 0;
    }

    private boolean areValidCoordinates() {
        return this.latitude >= 0 && this.longitude >= 0;
    }
}
