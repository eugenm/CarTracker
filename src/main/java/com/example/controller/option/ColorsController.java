package com.example.controller.option;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.assertj.core.groups.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.option.Color;
import com.example.repository.option.ColorsRepository;

@RestController
@RequestMapping(path = "api/option", produces = "application/json")
public class ColorsController {

	public static final Logger logger = LoggerFactory.getLogger(ColorsController.class);

	@Autowired
	ColorsRepository repository;

	// read: /api/option/colors.json (GET)
	@RequestMapping(value = "/colors.json", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map> listAll() {
		logger.info("Getting all Colors");
		List<Color> colors = (List<Color>) repository.findAll();
		Map<String, Object> body = new HashMap();
		if (colors.isEmpty()) {
			logger.debug("List was empty");
			body.put("success", false);
			return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
		}
		logger.debug("Success");
		body.put("success", true);
		body.put("count", colors.size());
		body.put("data", colors);
		return new ResponseEntity<Map>(body, HttpStatus.OK);
	}

	@RequestMapping(value = "colors/{id}.json", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getWithId(@PathVariable Integer id) {
		logger.info("Getting Color by ID");
		Color color = repository.findOne(id);
		if (color == null) {
			logger.error("ID not found");
			Map<String, Object> body = new HashMap();
			body.put("success", false);
			return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
		}
		logger.debug("Success");
		return new ResponseEntity<Color>(color, HttpStatus.OK);
	}

	// create: /api/option/colors.json (POST)
	@RequestMapping(value = "/colors.json", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<Map> create(@RequestBody Color color) {
		logger.info("Creating new Color");
		Color newColor = repository.save(color);
		Map<String, Object> body = new HashMap();
		if (newColor == null) {
			logger.error("Error on creating new Entity");
			body.put("success", false);
			return new ResponseEntity<Map>(body, HttpStatus.CONFLICT);
		}
		logger.debug("Success");
		body.put("success", true);
		body.put("data", newColor);
		body.put("message", "Color created!");
		return new ResponseEntity<Map>(body, HttpStatus.CREATED);
	}

	// update: /api/option/colors/{id}.json (PUT)
	@RequestMapping(value = "/colors/{id}.json", method = RequestMethod.PUT)
	public ResponseEntity<Map> update(@PathVariable Integer id, @RequestBody Color color) {
		logger.info("Updateing Color");
		Color foundColor = repository.findOne(id);
		Map<String, Object> body = new HashMap();
		if (foundColor == null) {
			logger.error("Error no Color found on ID");
			body.put("success", false);
			return new ResponseEntity<Map>(body, HttpStatus.NOT_FOUND);
		}
		foundColor.setActive(color.isActive());
		foundColor.setLongName(color.getLongName());
		foundColor.setShortName(color.getShortName());

		Color saved = repository.save(foundColor);
		logger.debug("Success");
		body.put("success", true);
		body.put("data", saved);
		body.put("message", "Color updated!");
		return new ResponseEntity<Map>(body, HttpStatus.OK);
	}

	// delete: /api/option/colors/{id}.json (DELETE)
	@RequestMapping(value = "colors/{id}.json", method = RequestMethod.DELETE)
	public ResponseEntity<Map> delete(@PathVariable Integer id) {
		Color color = repository.findOne(id);
		Map<String, Object> body = new HashMap();
		if (color == null) {
			body.put("success", false);
			return new ResponseEntity<>(body, HttpStatus.CONFLICT);
		}
		repository.delete(color);
		body.put("success", true);
		body.put("message", "Color deleted!");
		return new ResponseEntity<Map>(body, HttpStatus.OK);
	}
}
