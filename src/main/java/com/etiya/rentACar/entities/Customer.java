package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="customers")
public class Customer extends BaseEntity {
    @Column(name="fullName")
    private String fullName;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private String phone;

    @Column(name="nationalNo")
    private String nationalNo;

    @Column(name="birthDate")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "customer")
    private List<Rental> rentals;
}
