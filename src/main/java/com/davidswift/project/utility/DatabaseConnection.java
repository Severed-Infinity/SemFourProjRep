package com.davidswift.project.utility;

import com.davidswift.project.references.*;
import oracle.jdbc.pool.*;

import javax.sql.*;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

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
  private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());
  private static final long serialVersionUID = 1L;
  private transient PooledConnection connection;
  private DBLocation dblocation;

  private DatabaseConnection(final DBLocation location) throws
      SQLException {
    super();
    final OracleConnectionPoolDataSource dataSource = new OracleConnectionPoolDataSource();
    switch (location) {
      case LOCAL:
        LOGGER.log(Level.INFO, "Connecting Locally");
        dataSource.setDriverType("oracle.jdbc.driver.OracleDriver");
        dataSource.setURL("jdbc:oracle:thin:@localhost:1521:XE");
        this.connection = dataSource.getPooledConnection("admin", "admin");
        //         this.connection = DriverManager.getConnection
        // ("jdbc:oracle:thin:@localhost:1521:XE",
        //                "admin",
        //                "admin");
        break;
      case COLLEGE:
        LOGGER.log(Level.INFO, "Connecting to College Network");
        dataSource.setDriverType("oracle.jdbc.driver.OracleDriver");
        dataSource.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
        this.connection = dataSource.getPooledConnection("X00073017", "db03Dec91");
        //        this.connection = DriverManager.getConnection("jdbc:oracle:thin:@//10.10.2
        // .7:1521/global1",
        //            "X00073017",
        //            "db03Dec91");
        break;
    }
  }

  protected Object readResolve() {
    return getInstance();
  }

  public static Connection getInstance() {
    //    return DatabaseConnectionHolder.INSTANCE.get();
    Connection returnConnection = null;
    try {
      LOGGER.log(Level.INFO, "Retrieving Database Connection instance");
      returnConnection = DatabaseConnectionHolder.DATABASE_CONNECTION_OPTIONAL.get()
          .get()
          .getConnection().getConnection();
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to retrieve a Database Connection Instance", e);
    }
    return returnConnection;
  }

  protected PooledConnection getConnection() {
    return this.connection;
  }

  public static void setDblocation(final DBLocation dblocation) {
    DatabaseConnectionHolder.setDblocation(dblocation);
  }

  private static final class DatabaseConnectionHolder {
    public static final ThreadLocal<Optional<DatabaseConnection>> DATABASE_CONNECTION_OPTIONAL =
        new ThreadLocal<Optional<DatabaseConnection>>() {
          @Override
          protected Optional<DatabaseConnection> initialValue() {
            Optional<DatabaseConnection> returnNewDatabaseConnection = Optional.empty();
            try {
              returnNewDatabaseConnection = Optional.of
                  (new DatabaseConnection(dblocation));
            } catch (final SQLException e) {
              LOGGER.log(Level.SEVERE, "Could not establish a Database Connection", e);
            }
            return returnNewDatabaseConnection;
          }

        };
    //TODO dynamic injection of location
    private static DBLocation dblocation;

    public static DBLocation getDblocation() {
      return dblocation;
    }

    public static void setDblocation(final DBLocation dblocation) {
      DatabaseConnectionHolder.dblocation = dblocation;
    }
  }
}