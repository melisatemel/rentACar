package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
                                                            //Primary Key tipi belli olur burada id
public interface ModelRepository extends JpaRepository<Model,Integer> {

    Optional<Model> findByNameIgnoreCase(String name);
}
