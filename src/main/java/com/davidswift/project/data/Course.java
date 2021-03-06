package com.davidswift.project.data;

import com.davidswift.project.references.*;
import com.davidswift.project.utility.*;

import java.sql.*;
import java.util.logging.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 7/5/2014.
 */
public class Course implements IAddToDB, IRemoveFromDb, IUpdateInDB {
  private static final Logger LOGGER = Logger.getLogger(Course.class.getName());
  private final int courseID;
  private final String courseName;
  private final int courseLength;
  private final String department;
  private String courseHead;

  private Course(
      final int courseID,
      final String courseName,
      final String courseHead,
      final int courseLength,
      final String department
  ) {
    super();
    this.courseID = courseID;
    this.courseName = courseName;
    this.courseHead = courseHead;
    this.courseLength = courseLength;
    this.department = department;
  }

  public static Course createCourse(
      final int courseID,
      final String courseName,
      final String courseHead,
      final int courseLength,
      final String department
  ) {
    return new Course(courseID, courseName, courseHead, courseLength, department);
  }

  @Override
  public String toString() {
    return "Course{" +
        "courseID=" + this.courseID +
        ", courseName='" + this.courseName + '\'' +
        ", courseHead='" + this.courseHead + '\'' +
        ", courseLength=" + this.courseLength +
        ", department='" + this.department + '\'' +
        '}';
  }

  public String getDepartment() {
    return this.department;
  }

  @Override
  public void update(final Object... args) {
    this.setCourseHead((String)args[0]);
  }

  @Override
  public void addToDB() {
    LOGGER.log(Level.INFO, "Adding new course...");
    try {
      this.addToDB(Table.COURSE.getValue(), this.getCourseID(), this.getCourseName(),
          this.getCourseHead(), this.getCourseLength(), this.department);
      LOGGER.log(Level.INFO, "New course added");
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to add new course", e);
    }
  }

  public int getCourseID() {
    return this.courseID;
  }

  public String getCourseHead() {
    return this.courseHead;
  }

  protected void setCourseHead(final String courseHead) {
    this.courseHead = courseHead;
  }

  public int getCourseLength() {
    return this.courseLength;
  }

  public String getCourseName() {
    return this.courseName;
  }

  @Override
  public void removeFromDB() {
  }
}
