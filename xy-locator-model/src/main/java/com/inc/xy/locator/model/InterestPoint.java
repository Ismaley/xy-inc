package com.inc.xy.locator.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Builder
@EqualsAndHashCode(callSuper=false)
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InterestPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "point_sequence")
    @SequenceGenerator(name = "point_sequence", sequenceName = "point_sequence", allocationSize = 1)
    private Long id;

    @NotEmpty
    @NotNull
    @Column(nullable = false)
    private String pointName;

    @NotNull
    @Column(nullable = false)
    private Double xCoordinate;

    @NotNull
    @Column(nullable = false)
    private Double yCoordinate;

}
