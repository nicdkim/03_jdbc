package com.ohgiraffers.section01;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();
        Properties props = new Properties();
        Scanner sc = new Scanner(System.in);

        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        PreparedStatement pstmt3 = null;

        ResultSet rset1 = null;
        ResultSet rset2 = null;
        List<Map<Integer, String>> categoryList = null;

        int result = 0;
        int result2 = 0;

        System.out.println("-----메뉴 추가-----");
        System.out.print("메뉴 이름 : ");
        String menuName = sc.nextLine();
        System.out.print("메뉴 가격 : ");
        int price = sc.nextInt();
        System.out.print("카테고리 코드 : ");
        int categoryCode = sc.nextInt();
        sc.nextLine();
        System.out.print("주문 상태 (Y/N) : ");
        String orderableStatus = sc.nextLine();
        System.out.println("------------------");

        try {
            props.loadFromXML(new FileInputStream("src/main/resources/mapper/menu-query.xml"));
            String query = props.getProperty("selectLastMenuCode");
            String query2 = props.getProperty("selectAllCategoryList");
            String query3 = props.getProperty("insertMenu");

            pstmt1 = con.prepareStatement(query);
            pstmt2 = con.prepareStatement(query2);
            pstmt3 = con.prepareStatement(query3);

            rset1 = pstmt1.executeQuery();

            if(rset1.next()) {
                result = rset1.getInt("MAX(MENU_CODE)");
            }
            System.out.println("최신 메뉴 코드 : " + result);

            rset2 = pstmt2.executeQuery();
            categoryList = new ArrayList<>();
            while(rset2.next()) {
                Map<Integer, String> category = new HashMap<>();
                category.put(rset2.getInt("CATEGORY_CODE"), rset2.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }
            System.out.println("categoryList : " + categoryList);

            pstmt3.setString(1, menuName);
            pstmt3.setInt(2, price);
            pstmt3.setInt(3, categoryCode);
            pstmt3.setString(4, orderableStatus);

            result2 = pstmt3.executeUpdate();
            if(result2 > 0) {
                System.out.println("메뉴가 추가 되었습니다.");
            } else {
                System.out.println("다시 시도해 주세요.");
            }

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt1);
            close(rset1);
        }
    }
}
