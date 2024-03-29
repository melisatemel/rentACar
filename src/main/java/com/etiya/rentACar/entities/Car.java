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
@Table(name="cars")
public class Car extends BaseEntity {


    @Column(name="dailyPrice")
    private double dailyPrice;

    @Column(name="kilometer")
    private double kilometer;

    @Column(name="modelYear")
    private int modelYear;

    @Column(name="plate")
    private String plate;

    @Column(name="status")
    private int status;

    @ManyToOne
    @JoinColumn(name="modelId")
    private Model model;

    @ManyToOne()
    @JoinColumn(name = "rentalBranchId")
    private RentalBranch rentalBranch;

    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;
}
