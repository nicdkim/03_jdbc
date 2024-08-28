package com.ohgiraffers.section02.controller;

import com.ohgiraffers.section02.dao.MenuDAO;
import com.ohgiraffers.section02.dto.MenuDTO;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class MenuController {

    private MenuDAO menuDAO = new MenuDAO("src/main/resources/mapper/menu-query.xml");

    public void findMaxCode() {
        int result = menuDAO.selectLastMenuCode(getConnection2());
        System.out.println("최신 메뉴 코드 : " + result);
    }

    public void selectAllCategoryList() {
        List<Map<Integer, String>> result = menuDAO.selectAllCategoryList(getConnection2());
        System.out.println("카테고리 리스트 : " + result);
    }

    public void insertMenu() {
        Scanner sc = new Scanner(System.in);
        MenuDTO menuDTO = new MenuDTO();
        System.out.print("메뉴 이름 : ");
        menuDTO.menuName(sc.nextLine());
        System.out.print("메뉴 가격 : ");
        menuDTO.menuPrice(sc.nextInt());
        System.out.print("카테고리 번호 : ");
        menuDTO.categoryCode(sc.nextInt());
        System.out.print("판매 여부 : ");
        sc.nextLine();
        menuDTO.orderableStatus(sc.nextLine());
        
        int result = menuDAO.insertMenu(getConnection2(), menuDTO);
        if(result > 0) {
            System.out.println("메뉴 등록 완료");
        } else {
            System.out.println("메뉴 등록 실패");
        }
    }
}
