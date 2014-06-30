package com.davidswift.project;

import com.davidswift.project.controller.*;
import com.davidswift.project.enums.*;

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

  public static void main(String[] args) {
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
  }
}
