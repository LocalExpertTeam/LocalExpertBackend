package com.example.LocalExpertBackend.controllers;

import com.example.LocalExpertBackend.classes.City;
import com.example.LocalExpertBackend.exception.ApiRequestExeption;
import com.example.LocalExpertBackend.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
    @Autowired
    public CityRepository cityRepository;

    @GetMapping("")
    public ResponseEntity<List<City>> findAll() {
        try {
            return ResponseEntity.ok(cityRepository.findAllByOrderByName());
        } catch (Exception e) {
            throw new ApiRequestExeption("Something went wrong");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findCityById(@PathVariable("id") Long cityId) {
        try {
            Optional<City> cityOptional = Optional.ofNullable(cityRepository.findById(cityId));
            if (cityOptional.isPresent()) {
                return ResponseEntity.ok(cityRepository.findById(cityId));
            } else {
                throw new ApiRequestExeption("City not found with id: " + cityId);
            }
        } catch (Exception e) {
            throw new ApiRequestExeption("Something went wrong");
        }
    }
}
