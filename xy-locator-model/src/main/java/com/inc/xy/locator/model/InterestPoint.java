package com.inc.xy.locator.model;

import lombok.*;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Optional;

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
    private Integer xCoordinate;

    @NotNull
    @Column(nullable = false)
    private Integer yCoordinate;

    public boolean hasValidName() {
        return !StringUtils.isEmpty(this.pointName);
    }

    public boolean hasValidCoordinates() {
        return this.xCoordinate != null && this.yCoordinate != null && this.xCoordinate >= 0 && this.yCoordinate >= 0;
    }
}
