package com.davidswift.project.controller;

import com.davidswift.project.model.*;
import com.davidswift.project.model.RoomProperty.*;
import com.davidswift.project.utility.*;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import java.util.stream.*;

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
        LOGGER.log(Level.SEVERE, "Failed to create User table", e);
      }
      try {
        buildModuleTable();
      } catch (final SQLException e) {
        LOGGER.log(Level.SEVERE, "Failed to create Module table", e);
      }
      try {
        buildRoomTable();
      } catch (final SQLException e) {
        LOGGER.log(Level.SEVERE, "Failed to create Room table", e);
      }
      try {
        buildClassTable();
      } catch (final SQLException e) {
        LOGGER.log(Level.SEVERE, "Failed to create Class table", e);
      }
    }

  }

  //TODO include foreign keys
  private void buildRoomTable() throws SQLException {
    //create table
    final String sqlString = ("CREATE TABLE ROOMTABLE(ROOM_ID INTEGER NOT NULL PRIMARY KEY, " +
        "ROOM_SEATING INTEGER NOT NULL, DEPARTMENT VARCHAR2(15), LAB VARCHAR(5) NOT NULL, " +
        "SHARED_ROOM_NUMBER INTEGER)");
    try (Connection connection = DatabaseConnection.getInstance();
        PreparedStatement preparedStatement = connection
            .prepareStatement(sqlString)) {
      LOGGER.log(Level.INFO, "Created table: ROOMTABLE");
      preparedStatement.execute();
      addRooms();
    }
  }

  private void addRooms() {
    final List<RoomProperty> defaultRooms = new ArrayList<>(100);
    //First floor rooms
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(1).setRoomSeating(143)
            .setLab(false).setSharedRoomNumber(1).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(5).setRoomSeating(50)
        .setLab(false).setSharedRoomNumber(5).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(12).setRoomSeating(20)
            .setDepartment("Media")
            .setLab(true).setSharedRoomNumber(14).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(13).setRoomSeating(1000)
            .setDepartment("Engineering")
            .setLab(false).setSharedRoomNumber(13).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(15).setRoomSeating(66)
            .setLab(false).setSharedRoomNumber(15).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(16).setRoomSeating(21)
            .setDepartment("Languages")
            .setLab(true).setSharedRoomNumber(16).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(17).setRoomSeating(1000)
            .setDepartment("Engineering")
            .setLab(true).setSharedRoomNumber(17).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(18).setRoomSeating(21)
            .setDepartment("Media")
            .setLab(true).setSharedRoomNumber(18).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(19).setRoomSeating(20)
            .setDepartment("Engineering")
            .setLab(true).setSharedRoomNumber(19).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(20).setRoomSeating(10)
            .setDepartment("Media")
            .setLab(false).setSharedRoomNumber(20).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(21).setRoomSeating(66)
            .setLab(false).setSharedRoomNumber(21).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(22).setRoomSeating(45)
            .setLab(false).setSharedRoomNumber(22).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(23).setRoomSeating(66)
            .setLab(false).setSharedRoomNumber(23).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(25).setRoomSeating(143)
            .setLab(false).setSharedRoomNumber(25).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(27).setRoomSeating(1000)
            .setDepartment("Engineering")
            .setLab(true).setSharedRoomNumber(27).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(29).setRoomSeating(143)
            .setLab(false).setSharedRoomNumber(29).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(31).setRoomSeating(1000)
            .setDepartment("Science")
            .setLab(true).setSharedRoomNumber(31).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(32).setRoomSeating(54)
            .setLab(false).setSharedRoomNumber(32).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(40).setRoomSeating(36)
            .setDepartment("Business")
            .setLab(false).setSharedRoomNumber(40).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(42).setRoomSeating(40)
            .setDepartment("Engineering")
            .setLab(false).setSharedRoomNumber(42).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(46).setRoomSeating(32)
            .setDepartment("Business")
            .setLab(false).setSharedRoomNumber(46).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(48).setRoomSeating(1000)
            .setDepartment("Media")
            .setLab(false).setSharedRoomNumber(48).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(50).setRoomSeating(40)
            .setDepartment("Business")
            .setLab(false).setSharedRoomNumber(50).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(52).setRoomSeating(40)
            .setDepartment("Business")
            .setLab(false).setSharedRoomNumber(52).createRoomProperty());
    defaultRooms.add(
        RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(54).setRoomSeating(40)
            .setDepartment("Business")
            .setLab(false).setSharedRoomNumber(50).createRoomProperty());
    //Second floor rooms
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(101)
        .setRoomSeating(30)
        .setDepartment("Culinary arts")
        .setLab(true).setSharedRoomNumber(30).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(107)
        .setRoomSeating(28)
        .setDepartment("Media")
        .setLab(true).setSharedRoomNumber(107).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(111)
        .setRoomSeating(1000)
        .setDepartment("Science")
        .setLab(true).setSharedRoomNumber(111).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(113)
        .setRoomSeating(1000)
        .setDepartment("Science")
        .setLab(true).setSharedRoomNumber(115).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(116)
        .setRoomSeating(21)
        .setDepartment("Science")
        .setLab(true).setSharedRoomNumber(116).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(118)
        .setRoomSeating(40)
        .setDepartment("Science")
        .setLab(false).setSharedRoomNumber(118).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(119)
        .setRoomSeating(1000)
        .setDepartment("Science")
        .setLab(true).setSharedRoomNumber(121).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(125)
        .setRoomSeating(10)
        .setDepartment("Sports Science")
        .setLab(true).setSharedRoomNumber(125).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(127)
        .setRoomSeating(14)
        .setDepartment("Science")
        .setLab(true).setSharedRoomNumber(127).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(128)
        .setRoomSeating(36)
        .setDepartment("Engineering")
        .setLab(false).setSharedRoomNumber(128).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(129)
        .setRoomSeating(16)
        .setDepartment("Science")
        .setLab(true).setSharedRoomNumber(129).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(130)
        .setRoomSeating(36)
        .setDepartment("Engineering")
        .setLab(false).setSharedRoomNumber(130).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(131)
        .setRoomSeating(16)
        .setDepartment("Science")
        .setLab(true).setSharedRoomNumber(131).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(132)
        .setRoomSeating(20)
        .setDepartment("Media")
        .setLab(true).setSharedRoomNumber(132).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(136)
        .setRoomSeating(36)
        .setDepartment("Science")
        .setLab(false).setSharedRoomNumber(136).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(138)
        .setRoomSeating(70)
        .setLab(false).setSharedRoomNumber(140).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(142)
        .setRoomSeating(40)
        .setDepartment("Business")
        .setLab(false).setSharedRoomNumber(142).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(144)
        .setRoomSeating(30)
        .setDepartment("Engineering")
        .setLab(false).setSharedRoomNumber(144).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(146)
        .setRoomSeating(70)
        .setDepartment("Science")
        .setLab(false).setSharedRoomNumber(148).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(149)
        .setRoomSeating(30)
        .setDepartment("Science")
        .setLab(true).setSharedRoomNumber(149).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(150)
        .setRoomSeating(65)
        .setLab(false).setSharedRoomNumber(152).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(151)
        .setRoomSeating(12)
        .setDepartment("Science")
        .setLab(true).setSharedRoomNumber(151).createRoomProperty());
    //Third floor rooms
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(201)
        .setRoomSeating(40)
        .setDepartment("Business")
        .setLab(true).setSharedRoomNumber(201).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(202)
        .setRoomSeating(5)
        .setDepartment("Media")
        .setLab(true).setSharedRoomNumber(202).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(203)
        .setRoomSeating(40)
        .setDepartment("Business")
        .setLab(true).setSharedRoomNumber(203).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(204)
        .setRoomSeating(8)
        .setDepartment("Media")
        .setLab(true).setSharedRoomNumber(204).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(208)
        .setRoomSeating(20)
        .setDepartment("Engineering")
        .setLab(true).setSharedRoomNumber(208).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(209)
        .setRoomSeating(40)
        .setDepartment("Engineering")
        .setLab(true).setSharedRoomNumber(209).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(210)
        .setRoomSeating(20)
        .setDepartment("Business")
        .setLab(false).setSharedRoomNumber(210).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(211)
        .setRoomSeating(1000)
        .setDepartment("Engineering")
        .setLab(true).setSharedRoomNumber(211).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(212)
        .setRoomSeating(25)
        .setDepartment("Engineering")
        .setLab(false).setSharedRoomNumber(212).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(214)
        .setRoomSeating(54)
        .setDepartment("Engineering")
        .setLab(false).setSharedRoomNumber(214).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(215)
        .setRoomSeating(40)
        .setDepartment("Engineering")
        .setLab(true).setSharedRoomNumber(215).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(216)
        .setRoomSeating(26)
        .setDepartment("Computing")
        .setLab(true).setSharedRoomNumber(216).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(217)
        .setRoomSeating(20)
        .setDepartment("Engineering")
        .setLab(true).setSharedRoomNumber(217).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(218)
        .setRoomSeating(26)
        .setDepartment("Engineering")
        .setLab(true).setSharedRoomNumber(218).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(219)
        .setRoomSeating(20)
        .setDepartment("Engineering")
        .setLab(true).setSharedRoomNumber(219).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(220)
        .setRoomSeating(26)
        .setDepartment("Computing")
        .setLab(true).setSharedRoomNumber(220).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(221)
        .setRoomSeating(20)
        .setDepartment("Engineering")
        .setLab(true).setSharedRoomNumber(221).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(223)
        .setRoomSeating(60)
        .setDepartment("Engineering")
        .setLab(true).setSharedRoomNumber(223).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(225)
        .setRoomSeating(60)
        .setDepartment("Engineering")
        .setLab(false).setSharedRoomNumber(225).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(227)
        .setRoomSeating(40)
        .setDepartment("Computing")
        .setLab(true).setSharedRoomNumber(227).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(229)
        .setRoomSeating(42)
        .setDepartment("Computing")
        .setLab(true).setSharedRoomNumber(229).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(231)
        .setRoomSeating(40)
        .setDepartment("Computing")
        .setLab(true).setSharedRoomNumber(231).createRoomProperty());
    defaultRooms.add(RoomPropertyBuilder.createRoomPropertyBuilder().setRoomID(233)
        .setRoomSeating(32)
        .setDepartment("Computing")
        .setLab(true).setSharedRoomNumber(233).createRoomProperty());
    //Add all to database
    try (Stream<RoomProperty> stream = defaultRooms.parallelStream()) {
      stream.forEach(RoomProperty::addToDB);
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
    //TODO make sure DOUBLE PRECISION works with double
    final String sqlString = "CREATE TABLE CLASSTABLE(CLASS_ID INTEGER NOT NULL, " +
        "DAY VARCHAR2(10) NOT NULL, TIME_SLOT DOUBLE PRECISION NOT NULL, " +
        "ROOM_ID INTEGER NOT NULL " +
        "REFERENCES ROOMTABLE (ROOM_ID), MODULE_ID INTEGER NOT NULL REFERENCES MODULETABLE " +
        "(MODULE_ID), PRIMARY KEY (CLASS_ID, DAY, TIME_SLOT, ROOM_ID))";
    try (Connection connection = DatabaseConnection.getInstance();
        PreparedStatement preparedStatement = connection
            .prepareStatement(
                sqlString)) {
      preparedStatement.executeUpdate();
      LOGGER.log(Level.INFO, "Created table: CLASSTABLE");
    }

  }

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
    try (Connection connection = DatabaseConnection.getInstance();
        ResultSet resultSet =
            connection.getMetaData().getTables(null, null, "CLASSTABLE",
                null);
        PreparedStatement preparedStatement = connection
            .prepareStatement(
                "Drop table CLASSTABLE")
    ) {
      LOGGER.log(Level.INFO, "Deleting table: CLASSTABLE");
      preparedStatement.execute();

    } catch (final SQLException ex) {
      throw new SQLException("Table 'CLASSTABLE' does not exist \n" + ex);
    }
  }

  private void wipeRoomTable() throws SQLException {
    try (Connection connection = DatabaseConnection.getInstance(); ResultSet resultSet =
        connection.getMetaData().getTables(null, null, "ROOMTABLE",
            null); PreparedStatement preparedStatement = connection
        .prepareStatement(
            "Drop table ROOMTABLE")) {
      LOGGER.log(Level.INFO, "Deleting table: ROOMTABLE");
      if (resultSet.next()) {
        preparedStatement.execute();
      }
    } catch (final SQLException ex) {
      throw new SQLException("Table 'ROOMTABLE' does not exist \n" + ex);
    }
  }

  private void wipeModuleTable() throws SQLException {
    try (Connection connection = DatabaseConnection.getInstance(); ResultSet resultSet =
        connection.getMetaData().getTables(null, null, "MODULETABLE",
            null); PreparedStatement preparedStatement = connection
        .prepareStatement(
            "Drop table moduletable")) {
      LOGGER.log(Level.INFO, "Deleting table: MODULETABLE");
      if (resultSet.next()) {
        preparedStatement.execute();
      }
    } catch (final SQLException ex) {
      throw new SQLException("Table 'MODULETABLE' does not exist \n" + ex);
    }
  }

  private void wipeUserTable() throws SQLException {
    try (Connection connection = DatabaseConnection.getInstance(); ResultSet resultSet =
        connection.getMetaData().getTables(null, null, "USERTABLE",
            null); PreparedStatement preparedStatement = connection
        .prepareStatement(
            "Drop table usertable")) {
      LOGGER.log(Level.INFO, "Deleting table: USERTABLE");
      if (resultSet.next()) {
        preparedStatement.execute();
      }
    } catch (final SQLException ex) {
      throw new SQLException("Table 'USERTABLE' does not exist \n" + ex);
    }
  }

  private void wipeCourseTable() throws SQLException {
    try (Connection connection = DatabaseConnection.getInstance(); ResultSet resultSet =
        connection.getMetaData().getTables(null, null, "COURSETABLE",
            null); PreparedStatement preparedStatement = connection
        .prepareStatement(
            "Drop table coursetable")) {
      LOGGER.log(Level.INFO, "Deleting table: COURSETABLE");
      if (resultSet.next()) {
        preparedStatement.execute();
      }
    } catch (final SQLException ex) {
      throw new SQLException("Table 'COURSETABLE' does not exist \n" + ex);
    }
  }

  public static BuildDatabase createBuildDatabase() {
    return new BuildDatabase
        ();
  }
}
