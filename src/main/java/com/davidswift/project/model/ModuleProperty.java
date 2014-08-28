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
 * Created by david on 8/17/2014.
 */
public class ModuleProperty implements IAddToDB, IRemoveFromDb, IUpdateInDB {
  private static final Logger LOGGER = Logger.getLogger(ModuleProperty.class.getName());
  private final SimpleIntegerProperty moduleID;
  private final SimpleIntegerProperty courseID;
  private final SimpleStringProperty moduleName;
  private final SimpleStringProperty moduleLecturer;

  private ModuleProperty(final ModulePropertyBuilder modulePropertyBuilder) {
    super();
    this.moduleID = new SimpleIntegerProperty(modulePropertyBuilder.moduleID);
    this.courseID = new SimpleIntegerProperty(modulePropertyBuilder.courseID);
    this.moduleName = new SimpleStringProperty(modulePropertyBuilder.moduleName);
    this.moduleLecturer = new SimpleStringProperty(modulePropertyBuilder.moduleLecturer);
  }

  public SimpleIntegerProperty moduleIDProperty() {
    return this.moduleID;
  }

  public SimpleIntegerProperty courseIDProperty() {
    return this.courseID;
  }

  public SimpleStringProperty moduleNameProperty() {
    return this.moduleName;
  }

  public SimpleStringProperty moduleLecturerProperty() {
    return this.moduleLecturer;
  }

  @Override
  public void addToDB() {
    LOGGER.log(Level.INFO, "Adding new module...");
    try {
      this.addToDB(Table.MODULE.getValue(), this.getModuleID(),
          this.getModuleName(), this.getModuleLecturer(), this.getCourseID());
      LOGGER.log(Level.INFO, "New module added");
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to add new module", e);
    }
  }

  protected int getModuleID() {
    return this.moduleID.get();
  }

  protected int getCourseID() {
    return this.courseID.get();
  }

  protected String getModuleName() {
    return this.moduleName.get();
  }

  public void setModuleName(final String moduleName) {
    this.moduleName.set(moduleName);
  }

  protected String getModuleLecturer() {
    return this.moduleLecturer.get();
  }

  public void setModuleLecturer(final String moduleLecturer) {
    this.moduleLecturer.set(moduleLecturer);
  }

  @Override
  public void removeFromDB() {
    try {
      removeFromDB(Table.MODULE.getValue(), this.getModuleID());
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to delete module", e);
    }
  }

  @Override
  public void update(final Object... args) {
    //TODO decide if any values can be updated
  }

  public static class ModulePropertyBuilder {
    private int moduleID;
    private int courseID;
    private String moduleName;
    private String moduleLecturer;

    private ModulePropertyBuilder() {super();}

    public static ModulePropertyBuilder createModulePropertyBuilder() {
      return new
          ModulePropertyBuilder();
    }

    public ModulePropertyBuilder setModuleID(final int moduleID) {
      this.moduleID = moduleID;
      return this;
    }

    public ModulePropertyBuilder setCourseID(final int courseID) {
      this.courseID = courseID;
      return this;
    }

    public ModulePropertyBuilder setModuleName(final String moduleName) {
      this.moduleName = moduleName;
      return this;
    }

    public ModulePropertyBuilder setModuleLecturer(final String moduleLecturer) {
      this.moduleLecturer = moduleLecturer;
      return this;
    }

    public ModuleProperty createModuleProperty() {
      return new ModuleProperty(this);
    }
  }
}
