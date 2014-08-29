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
 * Created by david on 8/21/2014.
 */
public class ClassProperty implements IAddToDB, IRemoveFromDb, IUpdateInDB {
  private static final Logger LOGGER = Logger.getLogger(ClassProperty.class.getName());
  private final SimpleIntegerProperty classID;
  private final SimpleStringProperty day;
  private final SimpleDoubleProperty timeSlot;
  private final SimpleIntegerProperty roomNumber;
  private final SimpleIntegerProperty moduleID;

  private ClassProperty(
      final ClassPropertyBuilder classPropertyBuilder
  ) {
    super();
    this.classID = new SimpleIntegerProperty(classPropertyBuilder.classID);
    this.day = new SimpleStringProperty(classPropertyBuilder.day);
    this.timeSlot = new SimpleDoubleProperty(classPropertyBuilder.timeSlot);
    this.roomNumber = new SimpleIntegerProperty(classPropertyBuilder.roomNumber);
    this.moduleID = new SimpleIntegerProperty(classPropertyBuilder.moduleID);
  }

  public SimpleIntegerProperty classIDProperty() {
    return this.classID;
  }

  public SimpleStringProperty dayProperty() {
    return this.day;
  }

  public SimpleDoubleProperty timeSlotProperty() {
    return this.timeSlot;
  }

  public SimpleIntegerProperty roomNumberProperty() {
    return this.roomNumber;
  }

  public SimpleIntegerProperty moduleIDProperty() {
    return this.moduleID;
  }

  @Override
  public void addToDB() {
    LOGGER.log(Level.INFO, "Adding new class...");
    try {
      addToDB(Table.CLASS.getValue(), this.getClassID(), this.getDay(), this.getTimeSlot(),
          this.getRoomNumber(), this.getModuleID());
      LOGGER.log(Level.INFO, "New class added");
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to add new class", e);
    }
  }

  protected int getClassID() {
    return this.classID.get();
  }

  protected String getDay() {
    return this.day.get();
  }

  protected void setDay(final String day) {
    this.day.set(day);
  }

  protected double getTimeSlot() {
    return this.timeSlot.get();
  }

  protected void setTimeSlot(final double timeSlot) {
    this.timeSlot.set(timeSlot);
  }

  protected int getRoomNumber() {
    return this.roomNumber.get();
  }

  protected void setRoomNumber(final int roomNumber) {
    this.roomNumber.set(roomNumber);
  }

  @Override
  public String toString() {
    return "ClassProperty{" +
        "classID=" + this.classID +
        ", day=" + this.day +
        ", timeSlot=" + this.timeSlot +
        ", roomNumber=" + this.roomNumber +
        ", moduleID=" + this.moduleID +
        '}';
  }

  protected int getModuleID() {
    return this.moduleID.get();
  }

  protected void setModuleID(final int moduleID) {
    this.moduleID.set(moduleID);
  }

  @Override
  public void removeFromDB() {
    //todo add delete
  }

  @Override
  public void update(final Object... args) {
    try {
      updateInDB(Table.CLASS.getValue(), new String[] {"DAY", "TIME_SLOT", "ROOM_ID", "MODULE_ID"},
          args);
      this.setDay((String)args[0]);
      this.setTimeSlot((Double)args[1]);
      this.setRoomNumber((Integer)args[2]);
      this.setModuleID((Integer)args[3]);
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to update class", e);
    }
  }

  public static class ClassPropertyBuilder {
    private int classID;
    private String day;
    private double timeSlot;
    private int roomNumber;
    private int moduleID;

    private ClassPropertyBuilder() {super();}

    public static ClassPropertyBuilder createClassPropertyBuilder() {return new
        ClassPropertyBuilder();}

    public ClassPropertyBuilder setClassID(final int classID) {
      this.classID = classID;
      return this;
    }

    public ClassPropertyBuilder setDay(final String day) {
      this.day = day;
      return this;
    }

    public ClassPropertyBuilder setTimeSlot(final double timeSlot) {
      this.timeSlot = timeSlot;
      return this;
    }

    public ClassPropertyBuilder setRoomNumber(final int roomNumber) {
      this.roomNumber = roomNumber;
      return this;
    }

    public ClassPropertyBuilder setModuleID(final int moduleID) {
      this.moduleID = moduleID;
      return this;
    }

    public ClassProperty createClassProperty() {
      return new ClassProperty(this);
    }
  }
}
