package com.inc.xy.locator.api.to;


import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PointSearchParamTO {

    private Float xCoordinate;

    private Float yCoordinate;

    private Float radius;

}
