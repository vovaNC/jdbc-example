package com.jdbcexample.vokh;

import com.jdbcexample.vokh.db.DbPostgresSqlConnection;
import lombok.extern.java.Log;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@Log
public class Main {

    public static void main(String[] argv) {


        try (DbPostgresSqlConnection postgresSql = new DbPostgresSqlConnection()) {
            postgresSql.initConnection();

            Connection connection = postgresSql.getConnection();
            Statement statement = postgresSql.getStatement();

            ResultSet resultSet = statement.executeQuery("Select * from students;");

            while (resultSet.next()) {
                // todo add fields to Student object
                int id = resultSet.getInt(1); // в resultSet номерация столбцов начинается с 1
                String name = resultSet.getString(2);
                String sname = resultSet.getString(3);
                String email = resultSet.getString(4);
                log.info("id: " + id + " name: " + name + " sname:" + sname + " email" + email);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
