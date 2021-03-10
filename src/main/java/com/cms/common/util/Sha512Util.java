package com.cms.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha512Util {
	
	/**
	 * 회원 비밀번호 SHA-512 암호화(단방향)
	 * - Salt 대신에 회원 id를 입력받아 사용함 (전자정부 로그인 참고)
	 * 
	 * @author yoojinkim 
	 * @param password
	 * @param id
	 * @return secure password
	 */
	public static String getSecurePassword(String password, String id) {
		// 비밀번호, 아이디 입력 안 된 경우 빈 값 리턴
		if (password == null) return "";
		if (id == null) return "";
		
		String generatedPassword = null;
		
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.reset();
            md.update(id.getBytes());
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        return generatedPassword;
	}
	
	/**
	 * 암호화 테스트용 메인 메소드 
	 * - 회원가입 기능이 없으므로 이 메소드에서 비밀번호를 암호화해 DB에 insert 시킴
	 * 
	 * @author yoojinkim
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 암호화 테스트 할 사용자 id, pw를 입력
		String id = "test";		
		String password = "test";
		
		// 암호화된 비밀번호 얻어오기
		String encryptedPassword = getSecurePassword(password, id);
		
		System.out.println("ID: " + id);
		System.out.println("Password: " + encryptedPassword);
	}

}
