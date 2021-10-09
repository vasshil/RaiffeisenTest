package com.app.raiffeisen.database;


import com.app.raiffeisen.socks.SocksData;
import org.springframework.http.ResponseEntity;

import java.sql.*;

import static com.app.raiffeisen.database.Values.*;

public class DataBaseController {

    private static DataBaseController INSTANCE = null;

    private Connection connection;


    public DataBaseController() {

        try {
            connection = DriverManager.getConnection(urlConnect, "admin", "admin");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public ResponseEntity changeSocks(SocksData socksData) {

        try {
            Statement statement = connection.createStatement();
            String task =
                    "UPDATE "+ dataBaseName +
                    " SET quantity = quantity" + socksData.getQuantity() +
                            " where color = '" + socksData.getColor() +
                            "' and cottonpart = " + socksData.getCottonPart() + ";";
            ResultSet result = statement.executeQuery(task);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return ResponseEntity.ok().build();
    }

    public long getSocks(String color, String operation, String cottonPart) {

        long sum = 0;

        switch (operation) {
            case "moreThan":
                operation = ">";

                break;
            case "lessThan":
                operation = "<";

                break;
            case "equal":
                operation = "=";

                break;
        }

        try {
            Statement statement = connection.createStatement();
            String task =
                    "select * from " + dataBaseName +
                            " where color = '" + color + "' and cottonpart " + operation + " " + cottonPart;

            ResultSet result = statement.executeQuery(task);
            while (result.next()) {
                long quantity = result.getLong(4);
                sum += quantity;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return sum;
    }


    public static DataBaseController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataBaseController();
        }
        return INSTANCE;
    }

}
