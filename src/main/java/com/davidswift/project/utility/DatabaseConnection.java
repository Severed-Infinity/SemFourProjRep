package com.davidswift.project.utility;

import com.davidswift.project.references.*;

import java.io.*;
import java.sql.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 7/4/2014.
 */
public class DatabaseConnection implements Serializable {
  private static final long serialVersionUID = 1L;
  private Connection connection;

  private <DBLocation> DatabaseConnection(DBLocation l) throws ClassNotFoundException,
      SQLException {
    final com.davidswift.project.references.DBLocation location = (com.davidswift.project.references
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

  protected Object readResolve() {
    return getInstance();
  }

  public static DatabaseConnection getInstance() {
    return DatabaseConnectionHolder.INSTANCE.get();
  }

  public Connection getConnection() {
    return connection;
  }

  private static class DatabaseConnectionHolder {
    public static final ThreadLocal<DatabaseConnection> INSTANCE = new ThreadLocal
        <DatabaseConnection>() {
      @Override
      protected DatabaseConnection initialValue() {
        try {
          //TODO dynamic injection of location
          return new DatabaseConnection(DBLocation.LOCAL);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return null;
      }
    };
  }
}