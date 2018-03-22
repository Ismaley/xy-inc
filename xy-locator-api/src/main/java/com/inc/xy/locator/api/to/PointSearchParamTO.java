package com.inc.xy.locator.api.to;


import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PointSearchParamTO {

    private Integer xCoordinate;

    private Integer yCoordinate;

    private Integer radius;

}
