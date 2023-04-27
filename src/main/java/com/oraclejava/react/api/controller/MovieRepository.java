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
	
	 public List<Movie> search(String keyword) {
			return sqlSession.selectList("movie.search",keyword);
		}
	
	public void insert(Movie movie) { //void는 return 값이 없다
		sqlSession.insert("movie.insert" , movie);
	}
	
	public Movie findById(int id) {
		return sqlSession.selectOne("movie.findById", id);
	}
	
	public void update(Movie movie) {
		sqlSession.update("movie.update",movie);
	}
	


  public void delete(Movie movie) {
		sqlSession.update("movie.delete", movie.getMovie_id());
	}
	
//	public void delete(int id) { 
//		sqlSession.delete("movie.delete" ,id);
//	}
	
  

 
}





























