package com.davidswift.project.controller;

import com.davidswift.project.utility.*;

import java.sql.*;
import java.util.logging.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/29/2014.
 */
public final class BuildDatabase {
  private static final Logger LOGGER = Logger.getLogger(BuildDatabase.class.getName());

  private BuildDatabase() {
    super();
    try {
      wipeDatabase();
    } finally {
      try {
        buildCourseTable();
      } catch (final SQLException e) {
        LOGGER.log(Level.SEVERE, "Failed to create Course table", e);
      }
      try {
        buildUserTable();
      } catch (final SQLException e) {
        LOGGER.log(Level.SEVERE, "Failed to create Course table", e);
      }
      try {
        buildModuleTable();
      } catch (final SQLException e) {
        LOGGER.log(Level.SEVERE, "Failed to create Course table", e);
      }
      try {
        buildRoomTable();
      } catch (final SQLException e) {
        LOGGER.log(Level.SEVERE, "Failed to create Course table", e);
      }
      try {
        buildClassTable();
      } catch (final SQLException e) {
        LOGGER.log(Level.SEVERE, "Failed to create Course table", e);
      }
    }

  }

  //TODO include foreign keys
  private void buildRoomTable() throws SQLException {
    //create table
    final String sqlString = ("CREATE TABLE ROOMTABLE(ROOM_ID INTEGER NOT NULL PRIMARY KEY, " +
        "ROOM_SEATING INTEGER NOT NULL, DEPARTMENT VARCHAR2(15), LAB VARCHAR2(5) NOT NULL, " +
        "SHARED_ROOM_NUMBER INTEGER)");
    try (Connection connection = DatabaseConnection.getInstance();
        PreparedStatement preparedStatement = connection
            .prepareStatement(sqlString)) {
      LOGGER.log(Level.INFO, "Created table: ROOMTABLE");
      preparedStatement.execute();
    }
  }

  private void buildModuleTable() throws SQLException {
    //create table
    final String sqlString = "CREATE TABLE MODULETABLE(MODULE_ID INTEGER NOT NULL PRIMARY KEY, " +
        "MODULE_NAME VARCHAR2(50) NOT NULL, MODULE_lecturer VARCHAR2(30), " +
        "COURSE_ID INTEGER NOT NULL REFERENCES COURSETABLE (COURSE_ID))";
    try (Connection connection = DatabaseConnection.getInstance();
        PreparedStatement preparedStatement = connection
            .prepareStatement(
                sqlString)) {
      LOGGER.log(Level.INFO, "Created table: MODULETABLE");
      preparedStatement.executeUpdate();
    }
  }

  private void buildCourseTable() throws SQLException {
    //create table
    final String sqlString = "CREATE TABLE COURSETABLE(COURSE_ID INTEGER NOT NULL PRIMARY KEY, " +
        "COURSE_NAME VARCHAR2(50) NOT NULL, COURSE_HEAD VARCHAR2(30) NOT NULL, " +
        "COURSE_LENGTH INTEGER NOT NULL, DEPARTMENT VARCHAR2(50) NOT NULL)";
    try (Connection connection = DatabaseConnection.getInstance();
        PreparedStatement preparedStatement = connection
            .prepareStatement(
                sqlString)) {
      LOGGER.log(Level.INFO, "Created table: COURSETABLE");
      preparedStatement.executeUpdate();
    }

  }

  private void buildUserTable() throws SQLException {
    //create table
    final String sqlString = "CREATE TABLE USERTABLE(USER_ID INTEGER NOT NULL PRIMARY KEY, " +
        "USER_FIRST_NAME VARCHAR2(15), USER_LAST_NAME VARCHAR2(15), USER_PASSWORD VARCHAR2(10), " +
        "COURSE_ID integer references coursetable (course_id), TYPE varchar2(10))";
    try (Connection connection = DatabaseConnection.getInstance();
        PreparedStatement preparedStatement = connection
            .prepareStatement(
                sqlString)) {
      LOGGER.log(Level.INFO, "Created table: USERTABLE");
      preparedStatement.executeUpdate();
    }

  }

  //TODO modify types to valid day-time
  private void buildClassTable() throws SQLException {
    final String sqlString = "CREATE TABLE CLASSTABLE(ROOM_ID INTEGER NOT NULL REFERENCES " +
        "ROOMTABLE (ROOM_ID), " +
        "MODULE_ID INTEGER NOT NULL references moduletable (module_id), " +
        "DAY VARCHAR2(10) NOT NULL, TIME_SLOT varchar2(5) NOT NULL, PRIMARY KEY(ROOM_ID, " +
        "MODULE_ID) )";
    try (Connection connection = DatabaseConnection.getInstance();
        PreparedStatement preparedStatement = connection
            .prepareStatement(
                sqlString)) {
      LOGGER.log(Level.INFO, "Created table: CLASSTABLE");
      preparedStatement.executeUpdate();
    }

  }

  //TODO change the order in which the delete is executed
  private void wipeDatabase() {
    try {
      wipeClassTable();
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Class Table was not Deleted", e);
    }
    try {
      wipeRoomTable();
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Room Table was not Deleted", e);
    }
    try {
      wipeModuleTable();
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Module Table was not Deleted", e);
    }
    try {
      wipeUserTable();
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "User Table was not Deleted", e);
    }
    try {
      wipeCourseTable();
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Course Table was not Deleted", e);
    }
  }

  private void wipeClassTable() throws SQLException {
    final DatabaseMetaData tableExists;
    try (Connection connection = DatabaseConnection.getInstance()) {
      tableExists = connection
          .getMetaData();
      //try delete tables if exists
      try (ResultSet resultSet = tableExists.getTables(null, null, "CLASSTABLE",
          null); PreparedStatement preparedStatement = connection
          .prepareStatement(
              "Drop table classtable")) {
        LOGGER.log(Level.INFO, "Deleting table: CLASSTABLE");
        preparedStatement.execute();
      } catch (final SQLException ex) {
        throw new SQLException("Table 'CLASSTABLE' does not exist \n" + ex);
      }
    }
  }

  private void wipeRoomTable() throws SQLException {
    try (Connection connection = DatabaseConnection.getInstance()) {
      final DatabaseMetaData tableExists = connection
          .getMetaData();
      //try delete tables if exists
      try (ResultSet resultSet = tableExists.getTables(null, null, "ROOMTABLE", null);
          PreparedStatement preparedStatement = connection
              .prepareStatement(
                  "Drop table roomtable")) {
        LOGGER.log(Level.INFO, "Deleting table: ROOMTABLE");
        preparedStatement.execute();
      } catch (final SQLException ex) {
        throw new SQLException("Table 'ROOMTABLE' does not exist \n" + ex);
      }
    }
  }

  private void wipeModuleTable() throws SQLException {
    try (Connection connection = DatabaseConnection.getInstance()) {
      final DatabaseMetaData tableExists = connection
          .getMetaData();
      //try delete tables if they exist
      try (ResultSet resultSet = tableExists.getTables(null, null, "MODULETABLE", null);
          PreparedStatement preparedStatement = connection
              .prepareStatement(
                  "Drop table moduletable")) {
        LOGGER.log(Level.INFO, "Deleting table: MODULETABLE");
        preparedStatement.execute();
      } catch (final SQLException ex) {
        throw new SQLException("Table 'MODULETABLE' does not exist \n" + ex);
      }
    }
  }

  private void wipeUserTable() throws SQLException {
    try (Connection connection = DatabaseConnection.getInstance()) {
      final DatabaseMetaData tableExists = connection
          .getMetaData();
      //try delete tables if they exist
      try (ResultSet resultSet = tableExists.getTables(null, null, "USERTABLE", null);
          PreparedStatement preparedStatement = connection
              .prepareStatement(
                  "Drop table usertable")) {
        LOGGER.log(Level.INFO, "Deleting table: USERTABLE");
        preparedStatement.execute();
      } catch (final SQLException ex) {
        throw new SQLException("Table 'USERTABLE' does not exist \n" + ex);
      }
    }
  }

  private void wipeCourseTable() throws SQLException {
    try (Connection connection = DatabaseConnection.getInstance()) {
      final DatabaseMetaData tableExists = connection
          .getMetaData();
      //try delete tables if they exist
      try (ResultSet resultSet = tableExists.getTables(null, null, "COURSETABLE", null);
          PreparedStatement preparedStatement = connection
              .prepareStatement(
                  "Drop table coursetable")) {
        LOGGER.log(Level.INFO, "Deleting table: COURSETABLE");
        preparedStatement.execute();
      } catch (final SQLException ex) {
        throw new SQLException("Table 'COURSETABLE' does not exist \n" + ex);
      }
    }
  }

  public static BuildDatabase createBuildDatabase() {
    return new BuildDatabase();
  }
}
