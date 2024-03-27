package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor     //Parametreli constructor   oluşturur
@NoArgsConstructor
@Entity     //veritabanı tablosudur
@Table(name="orders")
public class Invoice extends BaseEntity {
    @Column(name = "totalPrice")
    private double totalPrice;
}
