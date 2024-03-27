package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
@Entity     //veritabanı tablosudur
@Table(name="rentals")
public class Rental extends BaseEntity {
    //customerid, rentalbranchid
//
//    @Column(name="customerId")
//    private int customerId;

    @Column(name="startDate")
    private LocalDateTime startDate;

    @Column(name="endDate")
    private LocalDateTime endDate;

    @Column(name="returnDate")
    private LocalDateTime returnDate;   //esas getirdiği tarih

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
}
