package com.devil.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.devil.db.DBConn;
import com.devil.vo.UserVO;

public class UserDAO {
	public static void InsertUser(UserVO vo) throws Exception {
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
	public static Boolean GetUser(UserVO vo) throws Exception {
        
        Connection db = DBConn.getConnection();
        
        // 쿼리 날려서 유저 정보를 검색
        String sql = "select * from user where email = ?";
//        String sql = "select * from user where email = ? and pw = ?";
        PreparedStatement pstmt = db.prepareStatement(sql);
        pstmt.setString(1, vo.getEmail());
        // DB에서 email과 함께 검색해서 넣어도 됨. 검색된 데이터가 있으면 로그인. 없으면 로그인 실패
//        pstmt.setString(2, vo.getPw()); 
        
        // email 만으로 검색해서 데이터가 있으면 pw DB 데이터와 vo.getPw() 데이터와 비교
        
        // 쿼리 실행해서 결과값 반환
        Boolean isLogin = false;
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) { // 검색된 데이터가 있으면 로그인 / 패스워드 체크 후 로그인 성공 여부
                String dbPw = rs.getString("pw");
                if (dbPw.equals(vo.getPw())) { // 입력한 패스워드가 데이터베이스 패스워드와 일치할 때
                        isLogin = true;
                }
                else { // 불일치할 때
                        // isLogin 이 false가 기본값이라서 else  문은 사실상 불필요~!~!!!!~!!~~~~~~~~!!!!
                }
        }
        else { // email 정보가 없음
                
        }
        
        db.close();
        return isLogin;
}
}
