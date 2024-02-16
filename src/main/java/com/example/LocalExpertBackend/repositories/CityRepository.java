package com.example.LocalExpertBackend.repositories;

import com.example.LocalExpertBackend.classes.City;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CityRepository extends Repository<City,Long> {
    List<City> findAllByOrderByName();

    City findById(Long cityId);
}
