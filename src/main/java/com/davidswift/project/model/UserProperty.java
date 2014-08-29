package com.davidswift.project.model;

import com.davidswift.project.references.*;
import com.davidswift.project.utility.*;
import javafx.beans.property.*;

import java.sql.*;
import java.util.logging.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 7/27/2014.
 */
public class UserProperty implements IAddToDB, IRemoveFromDb {
  private static final Logger LOGGER = Logger.getLogger(UserProperty.class.getName());
  private final SimpleIntegerProperty userID;
  private final SimpleStringProperty userFirstName;
  private final SimpleStringProperty userLastName;
  private final SimpleStringProperty userPassword;
  private final SimpleIntegerProperty courseID;
  private final SimpleStringProperty userType;
  //  public UserProperty(
  //      final int userID,
  //      final String userFirstName,
  //      final String userLastName,
  //      final String userPassword,
  //      final int courseID,
  //      final String userType
  //  ) {
  //    this.userID = new SimpleIntegerProperty(userID);
  //    this.userFirstName = new SimpleStringProperty(userFirstName);
  //    this.userLastName = new SimpleStringProperty(userLastName);
  //    this.userPassword = new SimpleStringProperty(userPassword);
  //    this.courseID = new SimpleIntegerProperty(courseID);
  //    this.userType = new SimpleStringProperty(userType);
  //  }

  private UserProperty(final UserPropertyBuilder userPropertyBuilder) {
    super();
    this.userID = new SimpleIntegerProperty(userPropertyBuilder.userID);
    this.userFirstName = new SimpleStringProperty(userPropertyBuilder.userFirstName);
    this.userLastName = new SimpleStringProperty(userPropertyBuilder.userLastName);
    this.userPassword = new SimpleStringProperty(userPropertyBuilder.userPassword);
    this.courseID = new SimpleIntegerProperty(userPropertyBuilder.courseID);
    this.userType = new SimpleStringProperty(userPropertyBuilder.userType);
  }

  @Override
  public String toString() {
    LOGGER.log(Level.INFO, "Printing");
    return "UserProperty{" +
        "userID=" + this.userID +
        ", userFirstName=" + this.userFirstName +
        ", userLastName=" + this.userLastName +
        ", userPassword=" + this.userPassword +
        ", courseID=" + this.courseID +
        ", userType=" + this.userType +
        '}';
  }

  public SimpleIntegerProperty userIDProperty() {
    return this.userID;
  }

  public SimpleStringProperty userFirstNameProperty() {
    return this.userFirstName;
  }

  public SimpleStringProperty userLastNameProperty() {
    return this.userLastName;
  }

  public SimpleStringProperty userPasswordProperty() {
    return this.userPassword;
  }

  public SimpleIntegerProperty courseIDProperty() {
    return this.courseID;
  }

  public SimpleStringProperty userTypeProperty() {
    return this.userType;
  }

  @Override
  public void addToDB() {
    LOGGER.log(Level.INFO, "Adding new user...");
    try {
      this.addToDB(Table.USER.getValue(), this.getUserID(), this.getUserFirstName(),
          this.getUserLastName(), this.getUserPassword(), this.getCourseID(), this.getUserType());
      LOGGER.log(Level.INFO, "New user added");
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to add new user", e);
    }
  }

  protected String getUserFirstName() {
    return this.userFirstName.get();
  }

  protected String getUserLastName() {
    return this.userLastName.get();
  }

  protected String getUserPassword() {
    return this.userPassword.get();
  }

  protected int getCourseID() {
    return this.courseID.get();
  }

  protected String getUserType() {
    return this.userType.get();
  }

  protected int getUserID() {
    return this.userID.get();
  }

  @Override
  public void removeFromDB() {
    try {
      removeFromDB(Table.USER.getValue(), this.getUserID());
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to delete user", e);
    }
  }

  public static class UserPropertyBuilder {
    private int userID;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private int courseID;
    private String userType;

    private UserPropertyBuilder() {super();}

    public static UserPropertyBuilder createUserPropertyBuilder() {
      return new
          UserPropertyBuilder();
    }

    public UserPropertyBuilder setUserID(final int userID) {
      this.userID = userID;
      return this;
    }

    public UserPropertyBuilder setUserFirstName(final String userFirstName) {
      this.userFirstName = userFirstName;
      return this;
    }

    public UserPropertyBuilder setUserLastName(final String userLastName) {
      this.userLastName = userLastName;
      return this;
    }

    public UserPropertyBuilder setUserPassword(final String userPassword) {
      this.userPassword = userPassword;
      return this;
    }

    public UserPropertyBuilder setCourseID(final int courseID) {
      this.courseID = courseID;
      return this;
    }

    public UserPropertyBuilder setUserType(final String userType) {
      this.userType = userType;
      return this;
    }

    public UserProperty createUserPropertyTest() {
      return new UserProperty(this);
    }
  }
}
