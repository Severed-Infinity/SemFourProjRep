package com.davidswift.project.controller;

import com.davidswift.project.utility.*;

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
public final class BuildDatabase {
  private PreparedStatement preparedStatement;

  private BuildDatabase() {
    try {
      wipeDatabase();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        buildUserTable();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        buildCourseTable();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        buildModuleTable();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        buildRoomTable();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

  }

  //TODO include foreign keys
  private void buildRoomTable() throws SQLException {
    //create table
    String sqlString = ("CREATE TABLE ROOMTABLE(ROOM_NUMBER INTEGER NOT NULL PRIMARY KEY, " +
        "ROOM_SEATING INTEGER NOT NULL, DEPARTMENT_NAME VARCHAR2(15), LAB VARCHAR2(5) NOT NULL, " +
        "SHARED_ROOM_NUMBER INTEGER)");
    preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement
        (sqlString);
    System.out.println("Creating table: ROOMTABLE");
    preparedStatement.executeUpdate();
  }

  private void buildModuleTable() throws SQLException {
    //create table
    String sqlString = "CREATE TABLE MODULETABLE(MODULE_ID INTEGER NOT NULL PRIMARY KEY, " +
        "MODULE_NAME VARCHAR2(50) NOT NULL, MODULE_LECTUER VARCHAR2(30), " +
        "COURSE_ID INTEGER NOT NULL )";
    preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(
        sqlString);
    System.out.println("Creating table: MODULETABLE");
    preparedStatement.executeUpdate();
  }

  private void buildCourseTable() throws SQLException {
    //create table
    String sqlString = "CREATE TABLE COURSETABLE(COURSE_ID INTEGER NOT NULL PRIMARY KEY, " +
        "COURSE_NAME VARCHAR2(50) NOT NULL, COURSE_HEAD VARCHAR2(30) NOT NULL, " +
        "COURSE_LENGTH INTEGER NOT NULL, DEPARTMENT_NAME VARCHAR2(50) NOT NULL)";
    preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(
        sqlString);
    System.out.println("Creating table: COURSETABLE");
    preparedStatement.executeUpdate();
  }

  private void buildUserTable() throws SQLException {
    //create table
    String sqlString = "CREATE TABLE USERTABLE(USER_ID INTEGER NOT NULL PRIMARY KEY, " +
        "USER_FIRST_NAME VARCHAR2(15), USER_LAST_NAME VARCHAR2(15), USER_PASSWORD VARCHAR2(10), " +
        "DEPARTMENT VARCHAR2(30))";
    preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(
        sqlString);
    System.out.println("Creating table: USERTABLE");
    preparedStatement.executeUpdate();
  }
//TODO change the order in which the delete is executed
  private void wipeDatabase() throws SQLException {
    final DatabaseMetaData tableExists = DatabaseConnection.getInstance().getConnection()
        .getMetaData();
    //try delete tables if exists
    ResultSet resultSet = tableExists.getTables(null, null, "ROOMTABLE", null);
    if (resultSet.next()) {
      try {
        preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(
            "Drop table roomtable");
        System.out.println("Deleting table: ROOMTABLE");
        preparedStatement.execute();
      } catch (SQLException ex) {
        throw new SQLException("Table 'ROOMTABLE' does not exist");
      }
    }
    //try delete tables if they exist
    resultSet = tableExists.getTables(null, null, "MODULETABLE", null);
    if (resultSet.next()) {
      try {
        preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(
            "Drop table moduletable");
        System.out.println("Deleting table: MODULETABLE");
        preparedStatement.execute();
      } catch (SQLException ex) {
        throw new SQLException("Table 'MODULETABLE' does not exist");
      }
    }
    //try delete tables if they exist
    resultSet = tableExists.getTables(null, null, "COURSETABLE", null);
    if (resultSet.next()) {
      try {
        preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(
            "Drop table coursetable");
        System.out.println("Deleting table: COURSETABLE");
        preparedStatement.execute();
      } catch (SQLException ex) {
        throw new SQLException("Table 'COURSETABLE' does not exist");
      }
    }
    //try delete tables if they exist
    resultSet = tableExists.getTables(null, null, "USERTABLE", null);
    if (resultSet.next()) {
      try {
        preparedStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(
            "Drop table usertable");
        System.out.println("Deleting table: USERTABLE");
        preparedStatement.execute();
      } catch (SQLException ex) {
        throw new SQLException("Table 'USERTABLE' does not exist");
      }
    }
  }

  public static BuildDatabase createBuildDatabase() {
    return new BuildDatabase
        ();
  }
}
