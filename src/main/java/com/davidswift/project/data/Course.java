package com.davidswift.project.data;

import com.davidswift.project.utility.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 7/5/2014.
 */
public class Course implements IAddToDB, IRemoveFromDb, IUpdateInDB{
//  public HashMap<String, Object> updateConditionals = new HashMap<>(5);
  private int courseID;

  @Override
  public String toString() {
    return "Course{" +
        "courseID=" + courseID +
        ", courseName='" + courseName + '\'' +
        ", courseHead='" + courseHead + '\'' +
        ", courseLength=" + courseLength +
        ", department='" + department + '\'' +
        '}';
  }

  private String courseName;
  private String courseHead;
  private int courseLength;
  private String department;

  private Course(
      final int courseID,
      final String courseName,
      final String courseHead,
      final int courseLength,
      final String department
  ) {
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

  public int getCourseID() {
    return courseID;
  }

  public String getCourseHead() {
    return courseHead;
  }

  public int getCourseLength() {
    return courseLength;
  }

  public String getDepartment() {
    return department;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseHead(final String courseHead) {
    this.courseHead = courseHead;
  }

  @Override
  public void update(final Object... args) {
    this.setCourseHead((String)args[0]);
  }
}
