package com.oraclejava.react.api.controller;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oraclejava.react.api.model.Movie;
@Repository
public class MovieRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<Movie> list() {
		return sqlSession.selectList("movie.list");
	}
}
