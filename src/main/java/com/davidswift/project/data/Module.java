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
 * Created by david on 7/16/2014.
 */
public class Module implements IAddToDB, IRemoveFromDb, IUpdateInDB {
  public static final Logger LOGGER = Logger.getLogger(Module.class.getName());
  private final int moduleID;
  private final int courseID;
  private String moduleName;
  private String moduleLecturer;

  private Module(
      final int moduleID,
      final String moduleName,
      final String moduleLecturer,
      final int courseID
  ) {
    super();
    this.moduleID = moduleID;
    this.moduleName = moduleName;
    this.moduleLecturer = moduleLecturer;
    this.courseID = courseID;
  }

  public static Module createModule(
      final int moduleID,
      final String moduleName,
      final String moduleLecturer,
      final int courseID
  ) {return new Module(moduleID, moduleName, moduleLecturer, courseID);}

  @Override
  public String toString() {
    return "Module{" +
        "moduleID=" + this.moduleID +
        ", moduleName='" + this.moduleName + '\'' +
        ", moduleLecturer='" + this.moduleLecturer + '\'' +
        ", courseID=" + this.courseID +
        '}';
  }

  @Override
  public void update(final Object... args) {
    this.setModuleName((String)args[0]);
//    this.setModuleLecturer((String)args[1]);
  }

  public int getModuleID() {
    return this.moduleID;
  }

  public String getModuleName() {
    return this.moduleName;
  }

  protected void setModuleName(final String moduleName) {
    this.moduleName = moduleName;
  }

  public String getModuleLecturer() {
    return this.moduleLecturer;
  }

  protected void setModuleLecturer(final String moduleLecturer) {
    this.moduleLecturer = moduleLecturer;
  }

  public int getCourseID() {
    return this.courseID;
  }

  @Override
  public void addToDB() {
  }

  @Override
  public void removeFromDB() {
  }
}
