package org.example;

import java.sql.*;

public class DBConnection {

    Connection conn;
    public void doConnectDB() {
        String connectionStr = "jdbc:mysql://localhost:3306/"+Utills.DB_NAME;
        String userName = "root";
        String password = "";
        try {
           conn = DriverManager.getConnection(connectionStr, userName, password);
            System.out.println("DB Connection is successful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void fetchData() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM "+Utills.TABLE_NAME+" INNER JOIN "+Utills.TABLE_NAME_1+
                    " ON "+Utills.TABLE_NAME+"."+Utills.ORDERID1+" = "+Utills.TABLE_NAME_1+"."+Utills.OrderID);


            while (rs.next()) {
                String order_name = rs.getString(Utills.OrderName);
                int price = rs.getInt(Utills.OrderPrice);
                float tip = rs.getFloat(Utills.OrderTips);
                String customer_name=rs.getString(Utills.CustomerName);
                System.out.println("CustomerName:- "+customer_name+"  OrderName: " + order_name + ", OrderPrice: " + price + ", Tip For Employee: "+tip);
            }


        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void fetchData1() {
        try {
            Statement stmt = conn.createStatement();

            ResultSet rs1 = stmt.executeQuery("SELECT * FROM "+Utills.TABLE_NAME+" ORDER BY "+Utills.OrderPrice+" DESC");

            System.out.println("Ordering in descending order:- ");
            while (rs1.next()) {
                String order_name = rs1.getString(Utills.OrderName);
                int price = rs1.getInt(Utills.OrderPrice);
                float tip = rs1.getFloat(Utills.OrderTips);
                System.out.println("OrderName: " + order_name + ", OrderPrice: " + price + ", Tip For Employee: "+tip);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
