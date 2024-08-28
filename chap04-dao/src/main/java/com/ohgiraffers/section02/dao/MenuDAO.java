package com.ohgiraffers.section02.dao;

import com.ohgiraffers.section02.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuDAO {

    // 데이터 액세스 오브젝트 - 데이터베이스와 상호작용을 할 클래스

    private Properties prop = new Properties();

    public MenuDAO(String url) {
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int selectLastMenuCode(Connection con) {
        Statement stmt = null;
        ResultSet rset = null;
        int maxCode = 0;

        String query = prop.getProperty("selectLastMenuCode");

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            if(rset.next()) {
                maxCode = rset.getInt("MAX(MENU_CODE)");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(stmt);
            close(rset);
        }
        return maxCode;
    }

    public List<Map<Integer, String>>selectAllCategoryList(Connection con) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<Map<Integer, String>> categoryList = null;

        String query = prop.getProperty("selectAllCategoryList");

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();
            categoryList = new ArrayList<>();
            while(rset.next()) {
                Map<Integer, String> category = new HashMap<>();
                category.put(rset.getInt("CATEGORY_CODE"), rset.getString("CATEGORY_NAME"));
                categoryList.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        return categoryList;
    }

    public int insertMenu(Connection con, MenuDTO menuDTO) {
        PreparedStatement pstmt = null;
        int result = 0;
        Properties props = prop;
        String query = props.getProperty("insertMenu");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, menuDTO.getMenuName());
            pstmt.setInt(2, menuDTO.getMenuPrice());
            pstmt.setInt(3, menuDTO.getCategoryCode());
            pstmt.setString(4, menuDTO.getOrderableStatus());

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("잘못된 값이 입력됨...");
        } catch (NullPointerException e) {
            System.out.println("잘못된 값이 입력됨...");
        }finally {
            close(con);
            close(pstmt);
        }
        return result;
    }

}
















