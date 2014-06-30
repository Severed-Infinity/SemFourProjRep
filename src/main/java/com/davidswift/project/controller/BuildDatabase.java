package com.davidswift.project.controller;

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
  private final Connection connection;
  private PreparedStatement preparedStatement;

  private BuildDatabase() throws SQLException {
    buildUserTable();
    buildCourseTable();
    buildModuleTable();
    buildRoomTable();
  }

  private void buildRoomTable() throws SQLException {
    String sqlString = ("CREATE TABLE ROOMTABLE(ROOM_NUMBER INTEGER NOT NULL PRIMARY KEY, " +
        "ROOM_SEATING INTEGER NOT NULL, DEPARTMENT_NAME VARCHAR2(15), LAB VARCHAR2(5) NOT NULL, " +
        "SHARED_ROOM_NUMBER INTEGER)");
    preparedStatement = connection.prepareStatement(sqlString);
    preparedStatement.executeUpdate();
  }

  private void buildModuleTable() throws SQLException {
    String sqlString = "CREATE TABLE MODULETABLE(MODULE_ID INTEGER NOT NULL PRIMARY KEY, " +
        "MODULE_NAME VARCHAR2(50) NOT NULL, MODULE_LECTUER VARCHAR2(30), " +
        "COURSE_ID INTEGER NOT NULL )";
    preparedStatement = connection.prepareStatement(sqlString);
    preparedStatement.executeUpdate();
  }

  private void buildCourseTable() throws SQLException {
    String sqlString = "CREATE TABLE COURSETABLE(COURSE_ID INTEGER NOT NULL PRIMARY KEY, " +
        "COURSE_NAME VARCHAR2(50) NOT NULL, COURSE_HEAD VARCHAR2(30) NOT NULL, " +
        "COURSE_LENGTH INTEGER NOT NULL, DEPARTMENT_NAME VARCHAR2(50) NOT NULL)";
    preparedStatement = connection.prepareStatement(sqlString);
    preparedStatement.executeUpdate();
  }

  private void buildUserTable() throws SQLException {
    String sqlString = "CREATE TABLE USERTABLE(USER_ID INTEGER NOT NULL PRIMARY KEY, " +
        "USER_FIRST_NAME VARCHAR2(15), USER_LAST_NAME VARCHAR2(15), USER_PASSWORD VARCHAR2(10), " +
        "DEPARTMENT VARCHAR2(30))";
    preparedStatement = connection.prepareStatement(sqlString);
    preparedStatement.executeUpdate();
  }

  public static BuildDatabase createBuildDatabase() throws SQLException {
    return new BuildDatabase
        ();
  }

}
