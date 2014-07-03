package com.davidswift.project;

import com.davidswift.project.controller.*;
import com.davidswift.project.data.*;
import com.davidswift.project.references.*;

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

    PreparedStatement psTest;

    ConnectionController connectionController = null;
    try {
      connectionController = ConnectionController.createConnectionController(DBLocation.LOCAL);
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    if (connectionController != null) {
      System.out.println(connectionController.getConnection());
      BuildDatabase.createBuildDatabase(connectionController.getConnection());
    }

    //TODO test functionality of addToDB
    User testUser = User.createUser(1, "testFirst", "testLast", "testPass", "testDept");
    try {
      testUser.addToDB(psTest, "Usertable", testUser.getUserID(), testUser.getUserFirstName(),
          testUser.getUserLastName(), testUser.getUserPassword(), testUser.getDepartment());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
