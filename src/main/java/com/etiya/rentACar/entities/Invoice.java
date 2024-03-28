package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="invoices")
public class Invoice extends BaseEntity {
    @Column(name = "totalPrice")
    private double totalPrice;

    @ManyToOne()
    @JoinColumn(name="rentalId")
    private Rental rental;
}
