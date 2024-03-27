package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
@Entity //bu class  bir veritabanı varlığısın
@Table(name="brands")

public class Brand extends BaseEntity {

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "brand")  //brand classından one to many gidecek models e
    @JsonIgnore                     //tabloların sonsuz döngüye girmesini önler, belirli alanların dikkate alınmamasını sağlıyor
    private List<Model> models;     //birden çok model olduğu için listeledik

}
