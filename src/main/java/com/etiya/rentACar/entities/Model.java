package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name="models")

public class Model extends BaseEntity {
    @Column(name="name")
    private String name;

    @ManyToOne()
    @JoinColumn(name="brandId")
    private Brand brand;

    @ManyToOne()
    @JoinColumn(name="transmissionId")
    private Transmission transmission;

    @ManyToOne()
    @JoinColumn(name="fuelId")
    private Fuel fuel;

    @OneToMany(mappedBy = "model")
    private List<Car> cars;}
