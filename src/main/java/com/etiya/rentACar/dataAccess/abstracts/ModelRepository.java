package com.etiya.rentACar.dataAccess.abstracts;

import com.etiya.rentACar.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
                                                            //Primary Key tipi belli olur burada id
public interface ModelRepository extends JpaRepository<Model,Integer> {
    //JPA dabulunmayan ve bizim eklemek istediğimiz methodları da burada yazabiliriz
    //SQL komutları yazılır. Data daki verilere erişilik
    Optional<Model> findByNameIgnoreCase(String name);
}
