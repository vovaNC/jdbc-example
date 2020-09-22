package com.jdbcexample.vokh.db;

import lombok.Getter;
import lombok.extern.java.Log;

import java.sql.*;

@Log
public class DbPostgresSqlConnection implements DbConnection {

    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/jdbc-test-2";
    static final String USER = "postgres";
    static final String PASS = "admin";

    @Getter
    private Connection connection;
    @Getter
    private Statement statement;

    @Override
    public boolean initConnection() {
        return initConnection(null, null, null);
    }

    @Override
    public boolean initConnection(String DB_URL, String USER, String PASS) {
        log.info("PostgreSQL JDBC Driver connectioning");

        try {
            if (DB_URL!=null && USER!=null && PASS!=null) {
                log.info("Custom connection params");
                this.connection = DriverManager.getConnection(DB_URL, USER, PASS);
            } else {
                log.info("Standart connection params");
                this.connection = DriverManager.getConnection(DbPostgresSqlConnection.DB_URL, DbPostgresSqlConnection.USER, DbPostgresSqlConnection.PASS);
            }

            this.statement = connection.createStatement();

            if (!statement.isClosed() && !connection.isClosed()) {
                return true;
            }
        } catch (SQLException throwables) {
            log.info("Something went wrong while connection to DB");
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public void close() throws Exception {
        if(statement!=null && !statement.isClosed()) {
            statement.close();
        }
        if(connection!=null && !connection.isClosed()) {
            connection.close();
        }
    }
}

/**
 * examples
 *
 *
 *                 {
 *                     // любые запросы SQL
 *
 *                     // statement.execute("insert into students (name, password, email) values ('vova3', 'password', 'gog@gmail.com');");
 *                 }
 *
 *                 {
 *                     // insert update delete запросы
 *                     // получать данные этим методом нельзя
 *                     // возвращает кол-во записей, в которые метод внёс измеения
 *
 *                     //int rowsUpdated = statement.executeUpdate("insert into students (name, password, email) values ('vova4', 'password', 'gog@gmail.com');");
 *                     //log.info( rowsUpdated + " rows updated ");
 *                 }
 *
 *                 {
 *                     // данным методом можно выполнять только select
 *                     // возвращает ResultSet
 *
 *                     ResultSet set = statement.executeQuery("Select * from students;");
 *                     set.next(); // переход на следующкю строку таблцы
 *                     log.info(set.getString("name")); // взять значение из записи по имени столбца
 *                     set.next();
 *                     log.info(set.getString("name"));
 *                 }
 *
 *                 {
 *                     // Пакетная обработка запросов
 *                     // обработка нескольких запросов одновременно
 *
 *                     statement.addBatch("insert into students (name, password, email) values ('goga1', 'password', 'gog@gmail.com');");
 *                     statement.addBatch("insert into students (name, password, email) values ('goga2', 'password', 'gog@gmail.com');");
 *                     statement.addBatch("insert into students (name, password, email) values ('goga3', 'password', 'gog@gmail.com');");
 *                     statement.executeBatch();
 *                     statement.clearBatch(); // затирает предидущие записи внутри Batch
 *                     statement.getConnection();
 *                     statement.isClosed();
 *                 }*/
