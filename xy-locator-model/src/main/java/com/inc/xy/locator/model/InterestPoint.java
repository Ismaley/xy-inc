package com.inc.xy.locator.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

@Entity
public class InterestPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_sequence")
    @SequenceGenerator(name = "point_sequence", sequenceName = "point_sequence", allocationSize = 1)
    private Long id;

    @NotEmpty
    @Column(nullable = false)
    private String pointName;

    @NotEmpty
    @Column(nullable = false)
    private Float xCoordinate;

    @NotEmpty
    @Column(nullable = false)
    private Float yCoordinate;

}
