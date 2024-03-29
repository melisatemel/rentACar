package com.etiya.rentACar.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data   //Getter Setter varmış gibi davranması için kullanılır   ----->   LOMBOK sayesinde
@NoArgsConstructor      // Parametresiz constructor oluşturur
@AllArgsConstructor     //Parametreli constructor   oluşturur
@MappedSuperclass       //ana class veri tabanında oluşturma kalıtımda olanların içindeki alanları paylaş
public class BaseEntity {
    @Id                 //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //strateji belirler, (identity nin 1 arttırılması stratejisi kullandık burada)
    @Column(name="id")
    private int id;

    @Column(name="createdDate")
    private LocalDateTime createdDate;

    @Column(name="updatedDate")
    private LocalDateTime updatedDate;

    @Column(name="deletedDate")
    private  LocalDateTime deletedDate;


    //İnheritance edildiği için hangi işlemleri yapılacağı anlaşılıyor
    @PrePersist //Create işlemi olduğunda bu methodu çalıştır
    public void onCreate(){
        createdDate = LocalDateTime.now();
    }
    @PreUpdate  //Update işlemi olduğunda methodu çalıştır
    public void onUpdate(){
        updatedDate = LocalDateTime.now();
    }


}
