package com.davidswift.project.data;

import com.davidswift.project.utility.*;

import java.util.logging.*;

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
  public static final Logger LOGGER = Logger.getLogger(User.class.getName());
  private final int userID;
  private final String userFirstName;
  private final String userLastName;
  private final String userPassword;
  private final int courseID;
  private final String type;

  private User(
      final int userID,
      final String userFirstName,
      final String userLastName,
      final String userPassword,
      final int courseID,
      final String type
  ) {
    super();
    this.userID = userID;
    this.userFirstName = userFirstName;
    this.userLastName = userLastName;
    this.userPassword = userPassword;
    this.courseID = courseID;
    //    updateConditionals.put("USER_ID", this.getUserID());
    //    updateConditionals.put("USER_FIRST_NAME", this.getUserFirstName());
    //    updateConditionals.put("USER_LAST_NAME", this.getUserLastName());
    //    updateConditionals.put("USER_PASSWORD", this.getUserPassword());
    //    updateConditionals.put("DEPARTMENT", this.getDepartment());
    this.type = type;
  }

  public static User createUser(
      final int userID,
      final String userFirstName,
      final String userLastName,
      final String userPassword,
      final int courseID,
      final String type
  ) {
    return new User(userID, userFirstName, userLastName, userPassword, courseID, type);
  }

  @Override
  public String toString() {
    return "User{" +
        "userID=" + this.userID +
        ", userFirstName='" + this.userFirstName + '\'' +
        ", userLastName='" + this.userLastName + '\'' +
        ", userPassword='" + this.userPassword + '\'' +
        ", courseID=" + this.courseID +
        ", type='" + this.type + '\'' +
        '}';
  }

  public int getUserID() {
    return this.userID;
  }

  public String getUserFirstName() {
    return this.userFirstName;
  }

  public String getUserLastName() {
    return this.userLastName;
  }

  public String getUserPassword() {
    return this.userPassword;
  }

  @Override
  public void update(final Object... args) {
  }

  public int getCourseID() {
    return this.courseID;
  }

  public String getType() {
    return this.type;
  }

  @Override
  public void addToDB() {
  }

  @Override
  public void removeFromDB() {
  }
}
