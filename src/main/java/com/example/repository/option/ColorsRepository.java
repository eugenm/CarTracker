package com.example.repository.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.option.Color;

//@RepositoryRestController()
@Repository
public interface ColorsRepository extends JpaRepository<Color, Integer> {

}
