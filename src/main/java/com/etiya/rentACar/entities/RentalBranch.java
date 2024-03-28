package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
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
@Table(name="rentalBranches")
public class RentalBranch extends BaseEntity {

    @Column(name="name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "cityId")
    private City city;

    @OneToMany(mappedBy = "rentalBranch")
    private List<Car> cars;
}
