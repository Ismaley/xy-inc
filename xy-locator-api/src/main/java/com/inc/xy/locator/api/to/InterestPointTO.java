package com.inc.xy.locator.api.to;


import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class InterestPointTO {

    private Long id;

    private String pointName;

    private Integer latitude;

    private Integer longitude;

}
