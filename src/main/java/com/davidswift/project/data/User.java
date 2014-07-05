package com.davidswift.project.data;

import com.davidswift.project.utility.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/30/2014.
 */
public final class User implements IAddToDB, IRemoveFromDb, IUpdateInDB {
  private final int userID;
  private final String userFirstName;
  private final String userLastName;
  private final String userPassword;
  private final String department;
//  public HashMap<String, Object> updateConditionals = new HashMap<>(5);

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

//    updateConditionals.put("USER_ID", this.getUserID());
//    updateConditionals.put("USER_FIRST_NAME", this.getUserFirstName());
//    updateConditionals.put("USER_LAST_NAME", this.getUserLastName());
//    updateConditionals.put("USER_PASSWORD", this.getUserPassword());
//    updateConditionals.put("DEPARTMENT", this.getDepartment());
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

  @Override
  public void update(final Object... args) {
  }
}
