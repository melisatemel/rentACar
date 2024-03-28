package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="rentals")
public class Rental extends BaseEntity {


    @Column(name="startDate")
    private LocalDateTime startDate;

    @Column(name="endDate")
    private LocalDateTime endDate;

    @Column(name="returnDate")
    private LocalDateTime returnDate;

    @Column(name="startKilometer")
    private double startKilometer;

    @Column(name="endKilometer")
    private double endKilometer;

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToMany(mappedBy = "rental")
    private List<Invoice> invoices;
}
