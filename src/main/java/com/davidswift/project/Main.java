package com.davidswift.project;

import com.davidswift.project.data.*;
import com.davidswift.project.utility.*;
import com.davidswift.project.view.*;
import javafx.application.*;

<<<<<<< HEAD
=======
import java.sql.*;
import java.util.logging.*;

>>>>>>> origin/testing
/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/29/2014.
 */
public final class Main {
  private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

  private Main() {super();}

  //Testing code
<<<<<<< HEAD
  public static void main(String[] args) {
    System.out.println(DatabaseConnection.getInstance());
    BuildDatabase.createBuildDatabase();
    User testUser = User.createUser(1, "testFirst", "testLast", "testPass", "testDept");
<<<<<<< HEAD
//    try {
//      testUser.removeFromDB(Table.USER.getValue(), testUser.getUserID());
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
//
//    try {
//      testUser.addToDB(Table.USER.getValue(),
//          testUser.getUserID(), testUser.getUserFirstName(),
//          testUser.getUserLastName(), testUser.getUserPassword(), testUser.getDepartment());
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
=======
    try {
      testUser.removeFromDB(Table.USER.getValue(), testUser.getUserID());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      testUser.addToDB(Table.USER.getValue(),
          testUser.getUserID(), testUser.getUserFirstName(),
          testUser.getUserLastName(), testUser.getUserPassword(), testUser.getDepartment());
    } catch (SQLException e) {
      e.printStackTrace();
    }
=======
  public static void main(final String... args) {
    //build database
    LOGGER.log(Level.INFO, String.valueOf(DatabaseConnection.getInstance()));
//    BuildDatabase.createBuildDatabase();
//    testData();
    Application.launch(Interface.class, args);
  }
>>>>>>> origin/testing

  public static void testData() {
    //creating a test course
    final Course testCourse = Course.createCourse(1, "testCourse", "testHead", 4, "testDept");
    try {
      testCourse.addToDB(Table.COURSE.getValue(), testCourse.getCourseID(),
          testCourse.getCourseName(), testCourse.getCourseHead(), testCourse.getCourseLength(),
          testCourse.getDepartment());
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to create a Course", e);
    }
    LOGGER.log(Level.INFO, testCourse.toString());
    try {
      testCourse.updateInDB(Table.COURSE.getValue(), new String[] {"course_head"},
          "testHeadChanged", testCourse.getCourseID());
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to update Course " + testCourse.toString(), e);
    }
<<<<<<< HEAD
    System.out.println(testCourse.toString());
>>>>>>> origin/testing
=======
    LOGGER.log(Level.INFO, testCourse.toString());
    //creating a test user
//    User testUser = User.createUser(1, "testFirst", "testLast", "testPass", 1,
//        UserType.FULL_TIME.getType());
//    LOGGER.log(Level.INFO, testUser.toString());
//    //    try {
//    //      testUser.removeFromDB(Table.USER.getValue(), testUser.getUserID());
//    //    } catch (SQLException e) {
//    //      e.printStackTrace();
//    //    }
//    try {
//      testUser.addToDB(Table.USER.getValue(),
//          testUser.getUserID(), testUser.getUserFirstName(),
//          testUser.getUserLastName(), testUser.getUserPassword(),
//          testUser.getCourseID(), testUser.getType());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to add a User", e);
//    }
//    //creating a test room
//    Room testRoom = Room.createRoom(1, 100, "testDepartment", false);
//    Room testRoom2 = Room.createRoom(2, 200, "testDepartment2", true, 3);
//    LOGGER.log(Level.INFO, testRoom.toString());
//    LOGGER.log(Level.INFO, testRoom2.toString());
//    try {
//      testRoom.addToDB(Table.ROOM.getValue(), testRoom.getRoomID(), testRoom.getRoomSeating(),
//          testRoom.getDepartment(), String.valueOf(testRoom.isLab()),
//          testRoom.getSharedRoomNumber());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to add a Room", e);
//    }
//    try {
//      testRoom2.addToDB(Table.ROOM.getValue(), testRoom2.getRoomID(), testRoom2.getRoomSeating(),
//          testRoom2.getDepartment(), String.valueOf(testRoom2.isLab()),
//          testRoom2.getSharedRoomNumber());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to add a Room", e);
//    }
//    try {
//      testRoom2.removeFromDB(Table.ROOM.getValue(), testRoom2.getRoomID());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to remove " + testRoom2.toString(), e);
//    }
//    //creating a test module
//    Module testModule = Module.createModule(1, "testModule", "testLectuer", 1);
//    try {
//      testModule.addToDB(Table.MODULE.getValue(), testModule.getModuleID(),
//          testModule.getModuleName(), testModule.getModuleLecturer(), testModule.getCourseID());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to add a Module", e);
//    }
//    LOGGER.log(Level.INFO, testModule.toString());
//    try {
//      testModule.updateInDB(Table.MODULE.getValue(),
//          new String[] {"module_name", "module_lecturer"},
//          "testNameUpdated", testModule.getModuleLecturer(), testModule.getModuleID());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to update " + testModule.toString(), e);
//    }
//    LOGGER.log(Level.INFO, testModule.toString());
//    //    try {
//    //      testModule.removeFromDB(Table.MODULE.getValue(), testModule.getModuleID());
//    //    } catch (SQLException e) {
//    //      e.printStackTrace();
//    //    }
//    //creating a test class
//    Class testClass = Class.createClass(1, 1, "testDay", 9.00);
//    try {
//      testClass.addToDB(Table.CLASS.getValue(), testClass.getRoomID(), testClass.getModuleID(),
//          testClass.getDay(), testClass.getTime());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to add a Class", e);
//    }
//    LOGGER.log(Level.INFO, testClass.toString());
//    //TODO change format of class to better work with IRemoveFromDB
//    //    try {
//    //      testClass.removeFromDB(Table.CLASS.getValue(), testClass.getRoomID());
//    //    } catch (SQLException e) {
//    //      e.printStackTrace();
//    //    }
//    //TODO change format of class to better work with IUpdateInDB
//    //    try {
//    //      testClass.updateInDB(Table.CLASS.getValue(), new String[] {"room_id"}, 2, 1);
//    //    } catch (SQLException e) {
//    //      e.printStackTrace();
//    //    }
//    UserProperty userPropertyTest = UserPropertyBuilder
//        .createUserPropertyTestBuilder()
//        .setUserID(
//            1)
//        .setUserFirstName(
//            "testPropFirst").setUserLastName("testPropSecond").setUserPassword(
//            "testPropPass").setCourseID(1).setUserType(
//            UserType.ADMIN.getType()).createUserPropertyTest();
//    LOGGER.log(Level.INFO, userPropertyTest.toString());
//    LOGGER.log(Level.INFO, userPropertyTest.getUserFirstName());
//    UserProperty userPropertyTest1 = UserPropertyBuilder
//        .createUserPropertyTestBuilder().setUserFirstName(
//        "Test " +
//            "Prop First").setUserLastName("Test Prop Last").setUserPassword("Test Prop Pass")
//        .createUserPropertyTest();
//    LOGGER.log(Level.INFO, userPropertyTest1.toString());
//    LOGGER.log(Level.INFO, String.valueOf(DatabaseConnection.getInstance()));
//    BuildDatabase.createBuildDatabase();
//    //creating a test course
//    testCourse = Course.createCourse(1, "testCourse", "testHead", 4, "testDept");
//    try {
//      testCourse.addToDB(Table.COURSE.getValue(), testCourse.getCourseID(),
//          testCourse.getCourseName(), testCourse.getCourseHead(), testCourse.getCourseLength(),
//          testCourse.getDepartment());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to create a Course", e);
//    }
//    LOGGER.log(Level.INFO, testCourse.toString());
//    try {
//      testCourse.updateInDB(Table.COURSE.getValue(), new String[] {"course_head"},
//          "testHeadChanged", testCourse.getCourseID());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to update Course " + testCourse.toString(), e);
//    }
//    LOGGER.log(Level.INFO, testCourse.toString());
//    //creating a test user
//    testUser = User.createUser(1, "testFirst", "testLast", "testPass", 1,
//        UserType.FULL_TIME.getType());
//    LOGGER.log(Level.INFO, testUser.toString());
//    //    try {
//    //      testUser.removeFromDB(Table.USER.getValue(), testUser.getUserID());
//    //    } catch (SQLException e) {
//    //      e.printStackTrace();
//    //    }
//    try {
//      testUser.addToDB(Table.USER.getValue(),
//          testUser.getUserID(), testUser.getUserFirstName(),
//          testUser.getUserLastName(), testUser.getUserPassword(),
//          testUser.getCourseID(), testUser.getType());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to add a User", e);
//    }
//    //creating a test room
//    testRoom = Room.createRoom(1, 100, "testDepartment", false);
//    testRoom2 = Room.createRoom(2, 200, "testDepartment2", true, 3);
//    LOGGER.log(Level.INFO, testRoom.toString());
//    LOGGER.log(Level.INFO, testRoom2.toString());
//    try {
//      testRoom.addToDB(Table.ROOM.getValue(), testRoom.getRoomID(), testRoom.getRoomSeating(),
//          testRoom.getDepartment(), String.valueOf(testRoom.isLab()),
//          testRoom.getSharedRoomNumber());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to add a Room", e);
//    }
//    try {
//      testRoom2.addToDB(Table.ROOM.getValue(), testRoom2.getRoomID(), testRoom2.getRoomSeating(),
//          testRoom2.getDepartment(), String.valueOf(testRoom2.isLab()),
//          testRoom2.getSharedRoomNumber());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to add a Room", e);
//    }
//    try {
//      testRoom2.removeFromDB(Table.ROOM.getValue(), testRoom2.getRoomID());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to remove " + testRoom2.toString(), e);
//    }
//    //creating a test module
//    testModule = Module.createModule(1, "testModule", "testLectuer", 1);
//    try {
//      testModule.addToDB(Table.MODULE.getValue(), testModule.getModuleID(),
//          testModule.getModuleName(), testModule.getModuleLecturer(), testModule.getCourseID());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to add a Module", e);
//    }
//    LOGGER.log(Level.INFO, testModule.toString());
//    try {
//      testModule.updateInDB(Table.MODULE.getValue(),
//          new String[] {"module_name", "module_lecturer"},
//          "testNameUpdated", testModule.getModuleLecturer(), testModule.getModuleID());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to update " + testModule.toString(), e);
//    }
//    LOGGER.log(Level.INFO, testModule.toString());
//    //    try {
//    //      testModule.removeFromDB(Table.MODULE.getValue(), testModule.getModuleID());
//    //    } catch (SQLException e) {
//    //      e.printStackTrace();
//    //    }
//    //creating a test class
//    testClass = Class.createClass(1, 1, "testDay", 9.00);
//    try {
//      testClass.addToDB(Table.CLASS.getValue(), testClass.getRoomID(), testClass.getModuleID(),
//          testClass.getDay(), testClass.getTime());
//    } catch (final SQLException e) {
//      LOGGER.log(Level.SEVERE, "Unable to add a Class", e);
//    }
//    LOGGER.log(Level.INFO, testClass.toString());
//    //TODO change format of class to better work with IRemoveFromDB
//    //    try {
//    //      testClass.removeFromDB(Table.CLASS.getValue(), testClass.getRoomID());
//    //    } catch (SQLException e) {
//    //      e.printStackTrace();
//    //    }
//    //TODO change format of class to better work with IUpdateInDB
//    //    try {
//    //      testClass.updateInDB(Table.CLASS.getValue(), new String[] {"room_id"}, 2, 1);
//    //    } catch (SQLException e) {
//    //      e.printStackTrace();
//    //    }
//    userPropertyTest = UserPropertyBuilder
//        .createUserPropertyTestBuilder()
//        .setUserID(
//            1)
//        .setUserFirstName(
//            "testPropFirst").setUserLastName("testPropSecond").setUserPassword(
//            "testPropPass").setCourseID(1).setUserType(
//            UserType.ADMIN.getType()).createUserPropertyTest();
//    LOGGER.log(Level.INFO, userPropertyTest.toString());
//    LOGGER.log(Level.INFO, userPropertyTest.getUserFirstName());
//    userPropertyTest1 = UserPropertyBuilder
//        .createUserPropertyTestBuilder().setUserFirstName(
//            "Test " +
//                "Prop First").setUserLastName("Test Prop Last").setUserPassword("Test Prop Pass")
//        .createUserPropertyTest();
//    LOGGER.log(Level.INFO, userPropertyTest1.toString());
>>>>>>> origin/testing
  }
}
