package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession; 
	

//insert
	public int insert(GuestbookVo gbVo) {

		int count = sqlSession.insert("guestbook.insert", gbVo);
		return count;
	}
	
//delete
	public int delete(GuestbookVo gbVo) {
		int count = sqlSession.delete("guestbook.delete", gbVo);
		return count;
	}
	
//list	
	public List<GuestbookVo> getList(){
		List<GuestbookVo> guestbookList = sqlSession.selectList("guestbook.selectList");
		return guestbookList;
	}
}
