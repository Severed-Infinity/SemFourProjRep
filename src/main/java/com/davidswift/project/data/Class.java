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
public class Class implements IAddToDB, IRemoveFromDb, IUpdateInDB {
  public static final Logger LOGGER = Logger.getLogger(Class.class.getName());
  //TODO modify types to valid day-time
  private final int roomID;
  private final int moduleID;
  private final String day;
  private final double time;

  private Class(final int roomID, final int moduleID, final String day, final double time) {
    super();
    this.roomID = roomID;
    this.moduleID = moduleID;
    this.day = day;
    this.time = time;
  }

  public static Class createClass(
      final int roomID,
      final int moduleID,
      final String day,
      final double time
  ) {return new Class(roomID, moduleID, day, time);}

  @Override
  public String toString() {
    return "Class{" +
        "roomID=" + this.roomID +
        ", moduleID=" + this.moduleID +
        ", day='" + this.day + '\'' +
        ", time=" + this.time +
        '}';
  }

  public int getRoomID() {
    return this.roomID;
  }

  public int getModuleID() {
    return this.moduleID;
  }

  public String getDay() {
    return this.day;
  }

  public double getTime() {
    return this.time;
  }

  @Override
  public void update(final Object... args) {
  }

  @Override
  public void addToDB() {
  }

  @Override
  public void removeFromDB() {
  }
}
