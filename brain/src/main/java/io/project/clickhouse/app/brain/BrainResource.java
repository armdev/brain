/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.project.clickhouse.app.brain;

import io.project.clickhouse.app.brain.dto.AllModel;
import io.project.clickhouse.app.brain.dto.TripModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author armdev
 */
@RestController
@RequestMapping("/api/v2/brain")
@Slf4j
public class BrainResource {

    @Autowired
    public DbConnect dbConnect;

    @GetMapping("/setup/table")
    public ResponseEntity setupTable() {
        try (Connection connection = dbConnect.getConnection()) {

            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("drop table if exists trips")) {
                    System.out.println(rs.next());
                }
                String tripTable = "create table trips(day Date, trip_id UInt32, objectiveValue Float64, violations UInt32, milage Float64, driveMinutes Float64, tripStart Timestamp, tripEnd Timestamp, stopCount UInt32, loadCount UInt32,"
                        + "pickup_latitude Float64, pickup_longitude Float64, dropoff_latitude Float64, dropoff_longitude Float64, pickup String, dropoff String) Engine=Log";

                try (ResultSet rs = stmt.executeQuery(tripTable)) {
                    System.out.println(rs.next());

                }
            }
            return ResponseEntity.status(HttpStatus.OK).body("Table Craated");
        } catch (SQLException ex) {
            Logger.getLogger(BrainResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occured");

    }

    @GetMapping("/trips/total/count")
    public ResponseEntity count() {
        try (Connection connection = dbConnect.getConnection()) {

            String sql = "select count(*) from trips";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println(pstmt);
                    if (rs.next()) {
                      
                        int aInt = rs.getInt(1);
                        return ResponseEntity.status(HttpStatus.OK).body(aInt);
                    }
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(BrainResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occured");

    }

    @PutMapping("/insert/data")
    public ResponseEntity insertData(@RequestBody TripModel tripModel) {

        try (Connection connection = dbConnect.getConnection()) {
            try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO trips VALUES(?,?,?, ?, ?, ?,?,?,?, ?, ?, ?, ?,?, ?,?)")) {
                for (AllModel mm : tripModel.getAllModels()) {
                    pstmt.setDate(1, new Date(System.currentTimeMillis()));
                    pstmt.setInt(2, mm.getTripId());
                    pstmt.setFloat(3, mm.getObjectiveValue());
                    pstmt.setInt(4, mm.getViolations());
                    pstmt.setFloat(5, mm.getMilage());
                    pstmt.setFloat(6, mm.getDriveMinutes());

                    ZonedDateTime start = ZonedDateTime.parse(mm.getTripStart());
                    ZonedDateTime end = ZonedDateTime.parse(mm.getTripEnd());
                    Timestamp timestamp = Timestamp.valueOf(start.toLocalDateTime());

                    Timestamp timestamp1 = Timestamp.valueOf(end.toLocalDateTime());

                    pstmt.setTimestamp(7, timestamp);
                    pstmt.setTimestamp(8, timestamp1);
                    pstmt.setInt(9, mm.getStopCount());
                    pstmt.setInt(10, mm.getLoadCount());
                    pstmt.setFloat(11, mm.getPickupLatitude());
                    pstmt.setFloat(12, mm.getPickuplongitude());
                    pstmt.setFloat(13, mm.getDropoffLatitude());
                    pstmt.setFloat(14, mm.getDropoffLongitude());
                    pstmt.setString(15, mm.getPickUpLocationName() != null ? mm.getPickUpLocationName() : "no_name");
                    pstmt.setString(16, mm.getDropoffLocationName() != null ? mm.getDropoffLocationName() : "no_name");

                    pstmt.addBatch();
                }
                pstmt.executeBatch();
                System.out.println(pstmt);
                return ResponseEntity.status(HttpStatus.OK).body("Data Inserted");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BrainResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occured");
    }

    ///// @GetMapping("/run")
    @Deprecated
    public void test() throws Exception {

        try (Connection connection = DriverManager.getConnection("jdbc:clickhouse://clickhouse:9000/default?user=default&password=default")) {
            try (Statement stmt = connection.createStatement()) {
                try (ResultSet rs = stmt.executeQuery("drop table if exists test_jdbc_example")) {
                    System.out.println(rs.next());
                }
                try (ResultSet rs = stmt.executeQuery("create table test_jdbc_example(day Date, name String, age UInt8) Engine=Log")) {
                    System.out.println(rs.next());
                }
                try (PreparedStatement pstmt = connection.prepareStatement("INSERT INTO test_jdbc_example VALUES(?, ?, ?)")) {
                    for (int i = 1; i <= 200; i++) {
                        pstmt.setDate(1, new Date(System.currentTimeMillis()));
                        if (i % 2 == 0) {
                            pstmt.setString(2, "Kuku San" + i);
                        } else {
                            pstmt.setString(2, "Kuku San");
                        }
                        pstmt.setByte(3, (byte) ((i % 4) * 15));
                        System.out.println(pstmt);
                        pstmt.addBatch();
                    }
                    pstmt.executeBatch();
                }

                try (PreparedStatement pstmt = connection.prepareStatement("select count(*) from test_jdbc_example where age>? and age<=?")) {
                    pstmt.setByte(1, (byte) 10);
                    pstmt.setByte(2, (byte) 30);
                    printCount(pstmt);
                }

                try (PreparedStatement pstmt = connection.prepareStatement("select count(*) from test_jdbc_example where name=?")) {
                    pstmt.setString(1, "Zhang San");
                    printCount(pstmt);
                }
                try (ResultSet rs = stmt.executeQuery("drop table test_jdbc_example")) {
                    System.out.println(rs.next());
                }
            }
        }
    }

    public static void printCount(PreparedStatement pstmt) throws SQLException {
        try (ResultSet rs = pstmt.executeQuery()) {
            System.out.println(pstmt);
            if (rs.next()) {
                log.info(("Count is  " + rs.getInt(1)));
            }
        }
    }
//https://altinity.com/blog/2019/5/23/handling-variable-time-series-efficiently-in-clickhouse
    //https://github.com/housepower/ClickHouse-Native-JDBC/blob/master/examples/src/main/java/examples/ExecuteQuery.javas
}
