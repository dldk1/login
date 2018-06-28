package com.devil.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.devil.db.DBConn;
import com.devil.vo.UserVO;

public class UserDAO {
	public static void insertUser(UserVO vo) throws Exception {
		// DB접속
		Connection db = DBConn.getConnection();
		// 쿼리 날려서 유저 정보를 삽입
		// insert into user (name, phone, email,pw) valuse ('이름','전화번호','이메일','비밀번호');
		String sql = "insert into user (name, phone, email, pw) values (?, ?, ?, ?)";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, vo.getName());
		pstmt.setString(2, vo.getPhone());
		pstmt.setString(3, vo.getEmail());
		pstmt.setString(4, vo.getPw());

		// 쿼리실행
		pstmt.executeUpdate();
		db.close();
	}

	// vo 객체를 넣어서 email pw 정보 확인
	public static Boolean getUser(UserVO vo) throws Exception {

		Connection db = DBConn.getConnection();

		// 쿼리 날려서 유저 정보를 검색
		String sql = "select * from user where email = ?";
		// String sql = "select * from user where email = ? and pw = ?";
		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, vo.getEmail());
		// DB에서 email과 함께 검색해서 넣어도 됨. 검색된 데이터가 있으면 로그인. 없으면 로그인 실패
		// pstmt.setString(2, vo.getPw());

		// email 만으로 검색해서 데이터가 있으면 pw DB 데이터와 vo.getPw() 데이터와 비교

		// 쿼리 실행해서 결과값 반환
		Boolean isLogin = false;
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) { // 검색된 데이터가 있으면 로그인 / 패스워드 체크 후 로그인 성공 여부
			String dbPw = rs.getString("pw");
			if (dbPw.equals(vo.getPw())) { // 입력한 패스워드가 데이터베이스 패스워드와 일치할 때
				isLogin = true;
			} else { // 불일치할 때
						// isLogin 이 false가 기본값이라서 else 문은 사실상 불필요~!~!!!!~!!~~~~~~~~!!!!
			}
		} else { // email 정보가 없음

		}

		db.close();
		return isLogin;
	}

	// String email, pw 를 매개변수로 넣어서 UserVO값을 반환
	// 들어가는 변수 String email, String pw
	// 리턴 받는 형은 UserVO
	public static UserVO getUser(String email, String pw) throws Exception {

		Connection db = DBConn.getConnection();

		// 쿼리 날려서 유저 정보를 검색
		String sql = "select * from user where email = ? and pw = ?";

		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, email);
		pstmt.setString(2, pw);
		UserVO vo = null;
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) { // 검색된 데이터가 있으면 로그인 / 패스워드 체크 후 로그인 성공 여부
			vo = new UserVO();
			vo.setName(rs.getString("name"));
			vo.setEmail(rs.getString("email"));
			vo.setPhone(rs.getString("phone"));
		}
		db.close();
		return vo;
	}

	/*
	 * 사용법 : 리턴 타입은 Boolean ID가 있으면 true 없으면 false Boolean isEmail=emailCheck(id);
	 * isID값을 비교
	 */
	public static Boolean emailCheck(String id) throws Exception {

		Connection db = DBConn.getConnection();

		// 쿼리 날려서 유저 정보를 검색
		String sql = "select * from user where email = ?";

		PreparedStatement pstmt = db.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		Boolean isEmail = rs.next();

		db.close();
		return isEmail;
	}

	// 사용자 정보 리스트를 가져오는 메tho드
	// UserVO는 사용자 하나의 정보
	// 리스트에 UserVO를 담으면 여러개의 사용자 정보를 받아 올 수 있겠네 ?
	/*
	 * getUser를 호출하면 리스트를 받을 수 있다. 
	 * 사용법 : ArrayList<UserVO> getList = UserDAO.getUser();
	 */
	public static ArrayList<UserVO> getUser() throws Exception {

		Connection db = DBConn.getConnection();

		// 쿼리 날려서 유저 정보를 검색
		String sql = "select * from user";

		PreparedStatement pstmt = db.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(); // 쿼리를 DB에 날려서 rs값을 받음

		// 사용자 정보를 담는 리스트
		ArrayList<UserVO> userList = new ArrayList<>();

		// 사용자 정보가 한줄씩 읽어서 DB정보의 user 테이블의 마지막까지 읽어서 더이상 읽을 정보가 없으면 while문 종료
		while (rs.next()) {
			UserVO vo = new UserVO(); // 사용자 정보를 담는 객체
			vo.setU_idx(rs.getInt("u_idx"));
			vo.setName(rs.getString("name"));
			vo.setPw(rs.getString("pw"));
			vo.setEmail(rs.getString("email"));
			vo.setPhone(rs.getString("phone"));
			userList.add(vo); //사용자 정보 객체를 리스트에 담기
		}
		db.close();
		return userList;
	}
}