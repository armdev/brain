/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.project.clickhouse.app.brain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author root
 */
@Service
@Slf4j
public class DbConnect {

    ///private static final String DB_URL = "jdbc:clickhouse://default:default@clickhouse:9000/default";
    private static final String DB_URL = "jdbc:clickhouse://clickhouse:9000/default?user=default&password=default";

    /**
     * Creates new instance
     *
     * @throws SQLException in case of connection issue
     */
    @PostConstruct
    public void init() throws SQLException {

    }

    public Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
