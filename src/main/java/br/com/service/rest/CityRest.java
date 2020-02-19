package br.com.service.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.service.entity.City;
import br.com.service.persistence.CityRepository;

@RestController
public class CityRest {

	@Autowired
	private CityRepository cityRepository;

	@RequestMapping(value = "/city", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<City>> getAllCities() {
		ArrayList<City> cities = cityRepository.findAll();

		if (!cities.isEmpty()) {
			return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
		}

		return new ResponseEntity<List<City>>(HttpStatus.OK);
	}

	@RequestMapping(value = "/city/{city}", method = RequestMethod.GET,  produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<City> getByCity(@PathVariable(value = "city") String city) {
		Optional<City> optional = cityRepository.findByCity(city);

		if (optional.isPresent()) {
			return new ResponseEntity<City>(optional.get(), HttpStatus.OK);
		}

		return new ResponseEntity<City>(HttpStatus.OK);
	}

	@RequestMapping(value = "/city", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@Valid @RequestBody City city) {
		try {
			cityRepository.save(city);
			return new ResponseEntity<String>("Cidade criada com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar criar uma cidade", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/city", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@Valid @RequestBody City city) {
		try {
			cityRepository.delete(city);
			return new ResponseEntity<String>("Cidade removida com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Erro ao tentar criar uma cidade", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
