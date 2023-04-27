package com.oraclejava.react.api.controller;

import org.springframework.http.*;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.react.api.model.Movie;

@Controller
@ResponseBody
public class MovieController {
	
	@Autowired
	private MovieRepository movieRepository;


	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

	
//	@RequestMapping("/")
//	public String home() {
//		return "index";
//	}
//	
	

	@GetMapping("/findAllMovies")
	public List<Movie> findAllMovies() {
		return movieRepository.list();
	}
	
	@GetMapping("/detailMovie/{id}")
	public Movie detail(@PathVariable int id) {
		System.out.println(id);
		return movieRepository.findById(id);
	}
	
	@DeleteMapping("/deleteMovie/{id}")
	public int deleteMovie(@PathVariable int id) {
		Movie movie= movieRepository.findById(id);
		movieRepository.delete(movie);
		return 1;
	}
	
	//영화 이미지 다운로더
			@GetMapping("/download/{id}")
			public ResponseEntity<byte[]> download(@PathVariable int id) {
				Movie movie = movieRepository.findById(id);
				byte[] isr = movie.getMovie_picture();
				if (isr != null) {
					String filename = id + ".jpg";
					HttpHeaders respHeaders = new HttpHeaders();
					respHeaders.setContentLength(isr.length);
					respHeaders.setContentType(new MediaType("jpeg", "jpg"));
					respHeaders.setCacheControl(
							"must-revalidate, post-check=0, pre-check=0, no-cache, no-store, max-age=0");
					respHeaders.set(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=" + filename);
					
					return new ResponseEntity<>(isr, respHeaders, HttpStatus.OK);
				} else {
					return null;
				}
			}
	
			
			@PostMapping(value="/saveMovie", 
			         produces = MediaType.APPLICATION_JSON_VALUE, 
			         consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
			   public int saveMovie(@RequestPart Movie movie,
			                  @RequestPart(required = false)
			                  MultipartFile img) {
			      System.out.println(movie);
			      if (img != null) {
			         try {
			            movie.setMovie_picture(img.getBytes());
			         } catch (IOException e) {
			            // TODO Auto-generated catch block
			            e.printStackTrace();
			         }
			      }
			      
			      if(movie.getMovie_id() == null) {
			    	  movieRepository.insert(movie);
			      }else {
			    	  movieRepository.update(movie);
			      }
			      
			      return 1;
			
			}
			
			
			@GetMapping("/findByTitle/{title}")
			public List<Movie> findByTitle(@PathVariable String title) {
				return movieRepository.search(title);
			}

	
	
	
}
















