package com.inc.xy.locator.model;

import lombok.*;
import org.springframework.util.StringUtils;

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

    @NotNull
    @Column(nullable = false)
    private String pointName;

    @NotNull
    @Column(nullable = false)
    private Integer latitude;

    @NotNull
    @Column(nullable = false)
    private Integer longitude;

    public boolean hasValidName() {
        return !StringUtils.isEmpty(this.pointName);
    }

    public boolean hasValidCoordinates() {
        return this.latitude != null && this.longitude != null && this.latitude >= 0 && this.longitude >= 0;
    }
}
