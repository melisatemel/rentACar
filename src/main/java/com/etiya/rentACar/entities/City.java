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
@Table(name="cities")
public class City extends BaseEntity {
    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "city")
    private List<RentalBranch>  rentalBranches;
}
