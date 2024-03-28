package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="cities")
public class City extends BaseEntity {
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "city")
    private List<RentalBranch>  rentalBranches;
}
