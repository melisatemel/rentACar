package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
@Entity     //veritabanı tablosudur
@Table(name="cars")
public class Car extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name="dailyPrice")
    private double dailyPrice;

    @Column(name="kilometer")
    private double kilometer;
                                         //Fields

    @Column(name="modelYear")
    private int modelYear;

    @Column(name="plate")
    private String plate;

    @Column(name="status")
    private int status;


    @ManyToOne              //Arabanın modelini tutmalıyız. bir modelin çokça arabası olabilir
    @JoinColumn(name="model_id") //FK
    private Model model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rentalBranchId")
    private RentalBranch rentalBranch;


    @OneToMany(mappedBy = "car")
    private List<Rental> rentals;
}
