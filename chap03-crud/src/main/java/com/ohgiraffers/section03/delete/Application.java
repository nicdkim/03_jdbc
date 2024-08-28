package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;
import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        System.out.print("삭제할 메뉴를 입력해 주세요 : ");
        String a = sc.nextLine();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("deleteMenu"));
            pstmt.setString(1, a);

            result = pstmt.executeUpdate();

            if(result == 1) {
                System.out.println("삭제에 성공하였습니다.");
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
