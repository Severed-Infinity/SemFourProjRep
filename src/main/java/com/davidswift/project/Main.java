package com.davidswift.project;

import com.davidswift.project.data.*;
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
    User testUser = User.createUser(1, "testFirst", "testLast", "testPass", "testDept");
    try {
      testUser.removeFromDB("user", testUser.getUserID());
    } catch (SQLException e) {
      e.printStackTrace();
    }

//    try {
//      testUser.addToDB("user",
//          testUser.getUserID(), testUser.getUserFirstName(),
//          testUser.getUserLastName(), testUser.getUserPassword(), testUser.getDepartment());
//    } catch (SQLException e) {
//      e.printStackTrace();
//    }
  }
}
