package com.davidswift.project.model;

import com.davidswift.project.data.*;
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
 * Created by david on 8/12/2014.
 */
public class CourseProperty implements IAddToDB, IRemoveFromDb, IUpdateInDB {
  private static final Logger LOGGER = Logger.getLogger(Course.class.getName());
  private final SimpleIntegerProperty courseID;
  private final SimpleStringProperty courseName;
  private final SimpleIntegerProperty courseLength;
  private final SimpleStringProperty department;
  private SimpleStringProperty courseHead;

  private CourseProperty(
      final CoursePropertyBuilder coursePropertyBuilder
  ) {
    super();
    this.courseID = new SimpleIntegerProperty(coursePropertyBuilder.courseID);
    this.courseName = new SimpleStringProperty(coursePropertyBuilder.courseName);
    this.courseLength = new SimpleIntegerProperty(coursePropertyBuilder.courseLength);
    this.department = new SimpleStringProperty(coursePropertyBuilder.department);
    this.courseHead = new SimpleStringProperty(coursePropertyBuilder.courseHead);
  }

  public SimpleIntegerProperty courseIDProperty() {
    return this.courseID;
  }

  public SimpleStringProperty courseNameProperty() {
    return this.courseName;
  }

  public SimpleIntegerProperty courseLengthProperty() {
    return this.courseLength;
  }

  public SimpleStringProperty departmentProperty() {
    return this.department;
  }

  public SimpleStringProperty courseHeadProperty() {
    return this.courseHead;
  }

  @Override
  public void update(final Object... args) {
    try {
      this.updateInDB(Table.COURSE.getValue(), new String[] {"COURSE_HEAD"}, args);
      this.setCourseHead(new SimpleStringProperty((String)args[0]));
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to update course", e);
    }

  }

  @Override
  public void addToDB() {
    LOGGER.log(Level.INFO, "Adding new course...");
    try {
      this.addToDB(Table.COURSE.getValue(), this.getCourseID(), this.getCourseName(),
          this.getCourseHead(), this.getCourseLength(), this.getDepartment());
      LOGGER.log(Level.INFO, "New course added");
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to add new course", e);
    }
  }

  protected String getDepartment() {
    return this.department.get();
  }

  protected int getCourseID() {
    return this.courseID.get();
  }

  protected String getCourseName() {
    return this.courseName.get();
  }

  protected int getCourseLength() {
    return this.courseLength.get();
  }

  protected String getCourseHead() {
    return this.courseHead.get();
  }

  protected void setCourseHead(final SimpleStringProperty courseHead) {
    this.courseHead = courseHead;
  }

  @Override
  public void removeFromDB() {
    try {
      removeFromDB(Table.COURSE.getValue(), this.getCourseID());
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to delete course", e);
    }
  }

  public static class CoursePropertyBuilder {
    private int courseID;
    private String courseName;
    private int courseLength;
    private String department;
    private String courseHead;

    private CoursePropertyBuilder() {super();}

    public static CoursePropertyBuilder createCoursePropertyBuilder() {
      return new
          CoursePropertyBuilder();
    }

    public CoursePropertyBuilder setCourseID(final int courseID) {
      this.courseID = courseID;
      return this;
    }

    public CoursePropertyBuilder setCourseName(final String courseName) {
      this.courseName = courseName;
      return this;
    }

    public CoursePropertyBuilder setCourseLength(final int courseLength) {
      this.courseLength = courseLength;
      return this;
    }

    public CoursePropertyBuilder setDepartment(final String department) {
      this.department = department;
      return this;
    }

    public CoursePropertyBuilder setCourseHead(final String courseHead) {
      this.courseHead = courseHead;
      return this;
    }

    public CourseProperty createCourseProperty() {
      return new CourseProperty(this);
    }
  }
}
