package com.davidswift.project.data;

import com.davidswift.project.interfaces.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/30/2014.
 */
public final class User implements IAddToDB, IRemoveFromDb {
  private final int userID;
  private final String userFirstName;
  private final String userLastName;
  private final String userPassword;
  private final String department;

  private User(
      final int userID,
      final String userFirstName,
      final String userLastName,
      final String userPassword,
      final String department
  ) {
    this.userID = userID;
    this.userFirstName = userFirstName;
    this.userLastName = userLastName;
    this.userPassword = userPassword;
    this.department = department;
  }

  public static User createUser(
      final int userID,
      final String userFirstName,
      final String userLastName,
      final String userPassword,
      final String department
  ) {return new User(userID, userFirstName, userLastName, userPassword, department);}

  public int getUserID() {
    return userID;
  }

  public String getUserFirstName() {
    return userFirstName;
  }

  public String getUserLastName() {
    return userLastName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public String getDepartment() {
    return department;
  }
}
