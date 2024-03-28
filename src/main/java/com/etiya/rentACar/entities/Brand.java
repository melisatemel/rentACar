package com.etiya.rentACar.entities;

import com.etiya.rentACar.core.entities.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="brands")

public class Brand extends BaseEntity {

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy = "brand")
    @JsonIgnore
    private List<Model> models;

}
