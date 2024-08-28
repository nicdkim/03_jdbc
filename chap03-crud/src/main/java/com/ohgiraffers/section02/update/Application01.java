package com.ohgiraffers.section02.update;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application01 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        System.out.print("변경하실 메뉴 이름을 입력 해주세요 : ");
        String a = sc.nextLine();
        System.out.print("어떤 메뉴 이름으로 변경 하시겠습니까 ? : ");
        String b = sc.nextLine();
        System.out.print("바꿀 메뉴의 가격을 입력 해주세요 : ");
        int c = sc.nextInt();
        System.out.println("바꿀 메뉴의 타입을 선택 해주세요");
        System.out.println("4. 한식, 5. 중식, 6. 일식, 7. 퓨전");
        System.out.print("선택 : ");
        int d = sc.nextInt();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("updateMenu"));

            pstmt.setString(4, a);
            pstmt.setString(1, b);
            pstmt.setInt(2, c);
            pstmt.setInt(3, d);

            result = pstmt.executeUpdate();

            if(result == 1) {
                System.out.println("변경에 성공하였습니다.");
            } else {
                System.out.println("다시 시도해 주세요.");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }
    }
}
