package com.oraclejava.react.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oraclejava.react.api.model.Movie;
import com.oraclejava.react.api.*;
@RestController
public class MovieRestController {
	@Autowired
	private MovieRepository movieRepository;
	
	@GetMapping("/findAllMovies")
	public List<Movie> findAllMovies() {
		return movieRepository.list();
	}
}























