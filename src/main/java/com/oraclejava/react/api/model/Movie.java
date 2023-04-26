package com.oraclejava.react.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data 
public class Movie { //movie 테이블
	
	
	private int movie_id;
	private String title; //영화제목
	private int price; //가격
	private String synopsis; //줄거리
	private byte[] movie_picture; //영화이미지
	
	
}














