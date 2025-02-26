package org.example;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {

    // SHA-256 해시 함수
    public static String sha256(String input) {
        String salt = "";
        try {
            // SHA-256 알고리즘 사용
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // salt 적용
            digest.reset();
            digest.update(salt.getBytes(StandardCharsets.UTF_8));

            byte[] hash = digest.digest(input.getBytes());

            // 바이트 배열을 16진수 문자열로 변환
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0'); // 1자리일 경우 앞에 0 추가
                }
                hexString.append(hex);
            }

            return hexString.toString(); // 최종 해시 값 반환
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 알고리즘을 찾을 수 없습니다.", e);
        }
    }

    // 테스트용 main 메서드
    public static void main(String[] args) {
        String input = "xbfflq1#$%"; // 변환 하고자 하는 값 입력
        String hashedValue = sha256(input);

        System.out.println("입력 문자열: " + input);
        System.out.println("SHA-256 해시값: " + hashedValue);
    }
}
