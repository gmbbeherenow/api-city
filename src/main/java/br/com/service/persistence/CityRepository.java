package br.com.service.persistence;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.service.entity.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

	ArrayList<City> findAll();
	
	Optional<City> findByCity(String city);
}
