package com.ohgiraffers.section02.dto;

public class MenuDTO {

    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;

    public MenuDTO(String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public MenuDTO() {

    }

    public MenuDTO menuName(String menuName) {
        this.menuName = menuName;
        return this;
    }

    public MenuDTO menuPrice(int menuPrice) {
        if(menuPrice <= 0) {
            System.out.println("음수가 입력됨..");
        } else {
            this.menuPrice = menuPrice;
        }
        return this;
    }

    public MenuDTO categoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
        return this;
    }

    public MenuDTO orderableStatus(String orderableStatus) {
        if (orderableStatus.equals("예") || orderableStatus.equals("Y")) {
            this.orderableStatus = "Y";
        } else if (orderableStatus.equals("아니오") || orderableStatus.equals("N")) {
            this.orderableStatus = "N";
        } else {
            System.out.println("잘못 입력됨..");
        }
        return this;
    }


    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
