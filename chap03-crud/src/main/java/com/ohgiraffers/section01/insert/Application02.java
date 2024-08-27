package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application02 {
    public static void main(String[] args) {

        /*
        * 사용자가 원하는 메뉴를 등록할 수 있도록 만들어주세요.
        * 등록이 완료되면 성공, 실패하면 실패라고 출력 해주세요.
        * */

        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();
        PreparedStatement pstmt = null;

        Properties prop = new Properties();

        int result = 0;

        System.out.print("메뉴 : ");
        String menuName = sc.nextLine();
        System.out.print("가격 : ");
        int price = sc.nextInt();
        System.out.print("카테고리 코드 : ");
        int categoryCode = sc.nextInt();
        sc.nextLine();
        System.out.print("주문 상태 (Y/N): ");
        String orderableStatus = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("insertMenu"));
            pstmt.setString(1, menuName);
            pstmt.setInt(2, price);
            pstmt.setInt(3,categoryCode);
            pstmt.setString(4,orderableStatus);

            result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("메뉴 등록이 완료되었습니다.");
            } else {
                System.out.println("메뉴 등록에 실패했습니다.");
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
