package com.etiya.rentACar;

import com.etiya.rentACar.entities.Brand;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RentACarApplication {

	public static void main(String[] args) {

		SpringApplication.run(RentACarApplication.class, args);
	}

	@Bean	//instance oluşturmak için.	dışarıdan nesne oluşturduk ama instance ı yok. Bu yüzden IoC ye dahil etmek için @Bean kullanılır.
	public ModelMapper getModelMapper(){
		return new ModelMapper();
	}

}
