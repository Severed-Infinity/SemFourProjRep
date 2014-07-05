package com.davidswift.project;

import com.davidswift.project.controller.*;
import com.davidswift.project.data.*;
import com.davidswift.project.references.*;
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
public final class Main {
  private Main() {}

  //Testing code
  public static void main(String[] args) {
    System.out.println(DatabaseConnection.getInstance());
    BuildDatabase.createBuildDatabase();
    User testUser = User.createUser(1, "testFirst", "testLast", "testPass", "testDept");
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

    Course testCourse = Course.createCourse(1, "testCourse", "testHead", 4, "testDept");
    try {
      testCourse.addToDB(Table.COURSE.getValue(), testCourse.getCourseID(),
          testCourse.getCourseName(), testCourse.getCourseHead(), testCourse.getCourseLength(),
          testCourse.getDepartment());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    try {
      testCourse.updateInDB(Table.COURSE.getValue(), new String[] {"course_head"},
          "testHeadChanged");
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
