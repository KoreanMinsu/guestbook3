package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private DataSource dataSource;
	
//field	
	private Connection conn=null;
	private PreparedStatement ps=null;
	private ResultSet rs=null;
	
//connection	
	private void getConnection() {
		try {
			
			conn=dataSource.getConnection();
		}catch (SQLException e) {
			System.out.println("error"+e);
		}
		
	}
	
//resource close
	private void close() {
		try {
			if(rs!=null) {
				rs.close();
			}
			if(ps!=null) {
				ps.close();
			}
			if(conn!=null) {
				conn.close();
			}
		} catch(SQLException e) {
			System.out.println("error"+e);
		}
	}
	
//insert
	public int insert(GuestbookVo gb) {
		int count = -1;
		getConnection();
		
		try {
			String query = "";
			query += " insert into guestbook ";
			query += " values( seq_no.nextval, ?, ?, ?, sysdate ) ";
			ps=conn.prepareStatement(query);
			ps.setString(1, gb.getName());
			ps.setString(2, gb.getPassword());
			ps.setString(3, gb.getContent());
			
			count = ps.executeUpdate();
			
			if(count>0) {
				System.out.println(count+"건이 등록됨");
			}else {
				System.out.println("관리자 문의");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}
	
//delete
	public int delete(GuestbookVo gb) {
		int count = -1;
		getConnection();
		
		try {
			String query = "";
			query+= " delete from guestbook ";
			query+= " where no = ? ";
			query+= " and password = ? ";
			
			ps=conn.prepareStatement(query);
			ps.setInt(1, gb.getNo());
			ps.setString(2, gb.getPassword());
			
			count = ps.executeUpdate();
			
			if(count>0) {
				System.out.println(count+"건이 삭제됨");
			}else {
				System.out.println("관리자문의");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		close();
		return count;
	}
	
//list	
	
	
	public List<GuestbookVo> getList(){
		List<GuestbookVo> guestbookList = new ArrayList<GuestbookVo>();
		
		getConnection();
		
		try {
			String query = "";
			query += " select no, ";
			query += "        name, ";
			query += "        password, ";
			query += "        content, ";
			query += "        reg_date ";
			query += " from guestbook ";
			query += " order by no ASC ";
			
			ps = conn.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String regDate = rs.getString("reg_date");
				
				GuestbookVo guestbookVo = new GuestbookVo(no, name, password, content, regDate);
				
				guestbookList.add(guestbookVo);
			}
			
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		close();
		return guestbookList;
	}
}
