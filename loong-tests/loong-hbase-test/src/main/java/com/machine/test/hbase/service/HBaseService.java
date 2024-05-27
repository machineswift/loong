package com.machine.test.hbase.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;

@Service
public class HBaseService {

    @Value("${spring.phoenix.zookeeper.quorum}")
    private String zookeeperQuorum;

    @Value("${spring.phoenix.zookeeper.znode}")
    private String znodeParent;

    public void createTable() throws SQLException {
        String url = "jdbc:phoenix:" + zookeeperQuorum + znodeParent;
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "CREATE TABLE IF NOT EXISTS USER_TABLE (" +
                    "ID VARCHAR NOT NULL PRIMARY KEY, " +
                    "NAME VARCHAR, " +
                    "AGE INTEGER)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.execute(sql);
        }
    }

    public void insertData(String id, String name, int age) throws SQLException {
        String url = "jdbc:phoenix:" + zookeeperQuorum + znodeParent;
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "UPSERT INTO USER_TABLE VALUES (?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setInt(3, age);
            statement.executeUpdate();
            conn.commit();
        }
    }

    public String selectData(String id) throws SQLException {
        String url = "jdbc:phoenix:" + zookeeperQuorum + znodeParent;
        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "SELECT * FROM USER_TABLE WHERE ID = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("NAME") + ", " + rs.getInt("AGE");
            }
        }
        return null;
    }
}
