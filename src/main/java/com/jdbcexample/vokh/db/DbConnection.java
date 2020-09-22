package com.jdbcexample.vokh.db;

public interface DbConnection extends AutoCloseable{

    boolean initConnection(String DB_URL, String USER, String PASS);

    default boolean initConnection() {
        return initConnection(null, null, null);
    }

}
