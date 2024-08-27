package com.ohgiraffers.understand;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Application {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse("src/main/java/com/ohgiraffers/understand/employee.xml");

        NodeList nodeList = doc.getElementsByTagName("entry");

        String url = "jdbc:mysql://localhost:3306/employee";
        String user = "gangnam";
        String password = "gangnam";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                String key = node.getAttributes().getNamedItem("key").getNodeValue();
                String query = node.getTextContent().trim();

                System.out.println("쿼리 : " + key + ": " + query);

                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    int columnCount = rs.getMetaData().getColumnCount();
                    for (int col = 1; col <= columnCount; col++) {
                        System.out.print(rs.getString(col) + "\t");
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
