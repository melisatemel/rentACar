package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rental_branches")
public class RentalBranch extends BaseEntity {

    @ManyToOne()
    @JoinColumn(name = "cityId")
    private City city;

    @OneToMany(mappedBy = "rentalBranch")
    private List<Car> cars;
}
