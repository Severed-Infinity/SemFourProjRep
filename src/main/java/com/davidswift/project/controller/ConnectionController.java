package com.davidswift.project.controller;

import com.davidswift.project.enums.*;

import java.sql.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/29/2014.
 */
public final class ConnectionController {
  private Connection connection;

  private <DBLocation> ConnectionController(DBLocation l) throws ClassNotFoundException,
      SQLException {
    final com.davidswift.project.enums.DBLocation location = (com.davidswift.project.enums
        .DBLocation)l;
    switch (location) {
      case LOCAL:
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "admin",
            "admin");
        break;
      case COLLEGE:
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.10.2.7:1521/global1",
            "X00073017",
            "db03Dec91");
        break;
    }
  }

  public static ConnectionController createConnectionController(DBLocation l) throws
      ClassNotFoundException,
      SQLException {return new ConnectionController(l);}

  public Connection getConnection() {
    return this.connection;
  }

}