package com.jdbcexample.vokh;

import com.sun.deploy.util.StringUtils;
import lombok.extern.java.Log;

import java.sql.*;

@Log
public class Main {
    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/jdbc-test-2";
    static final String USER = "postgres";
    static final String PASS = "admin";

    public static void main(String[] argv) {

        log.info("PostgreSQL JDBC Driver connectioning");

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement statement = connection.createStatement()) {
            if (!statement.isClosed()) {
                log.info("!statement.isClosed() = true");
                log.info("!statement.isClosed() = true");

                {
                    // любые запросы SQL

                    // statement.execute("insert into students (name, password, email) values ('vova3', 'password', 'gog@gmail.com');");
                }

                {
                    // insert update delete запросы
                    // получать данные этим методом нельзя
                    // возвращает кол-во записей, в которые метод внёс измеения

                    //int rowsUpdated = statement.executeUpdate("insert into students (name, password, email) values ('vova4', 'password', 'gog@gmail.com');");
                    //log.info( rowsUpdated + " rows updated ");
                }

                {
                    // данным методом можно выполнять только select
                    // возвращает ResultSet

                    ResultSet set = statement.executeQuery("Select * from students;");
                    set.next(); // переход на следующкю строку таблцы
                    log.info(set.getString("name")); // взять значение из записи по имени столбца
                    set.next();
                    log.info(set.getString("name"));
                }

                {
                    // Пакетная обработка запросов
                    // обработка нескольких запросов одновременно

                    statement.addBatch("insert into students (name, password, email) values ('goga1', 'password', 'gog@gmail.com');");
                    statement.addBatch("insert into students (name, password, email) values ('goga2', 'password', 'gog@gmail.com');");
                    statement.addBatch("insert into students (name, password, email) values ('goga3', 'password', 'gog@gmail.com');");
                    statement.executeBatch();
                    statement.clearBatch(); // затирает предидущие записи внутри Batch
                    statement.getConnection();
                    statement.isClosed();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
