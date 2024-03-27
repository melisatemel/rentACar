package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
@Entity     //veritabanı tablosudur. Bağlantıyı kurar
@Table(name="models")

public class Model extends BaseEntity {
    @Column(name="name")
    private String name;


    @ManyToOne(fetch = FetchType.LAZY)      //brandId çok kere geçeceği için many ile başladık
    @JoinColumn(name="brandId")    //model tablosunun içine yeni column eklendi, foreign key
    private Brand brand;           //gelen tip Brand tipinde brand, BRAND class ındaki name in tanımlandığı kısım ileride brand. dediğimizde name in çıkması için

    @OneToMany(mappedBy = "model")
    @JsonIgnore
    private List<Car> cars;

    @ManyToOne(fetch = FetchType.LAZY)                  //birden fazla model olabilir ama bir tane vites olabilir.
    @JoinColumn(name="transmissionId")//model tablosunun içine yeni column eklendi, transmissinonId. Yani iki tablo arasındaki ilişkiyi kurduk
    private Transmission transmission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fuelId")
    private Fuel fuel;
}
