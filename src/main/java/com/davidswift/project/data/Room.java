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
public class Room implements IAddToDB, IRemoveFromDb {
  public static final Logger LOGGER = Logger.getLogger(Room.class.getName());
  private final int roomID;
  private final int roomSeating;
  private final String department;
  private final boolean isLab;
  private final int sharedRoomNumber;

  private Room(
      final int roomID,
      final int roomSeating,
      final String department,
      final boolean isLab,
      final int sharedRoomNumber
  ) {
    super();
    this.roomID = roomID;
    this.roomSeating = roomSeating;
    this.department = department;
    this.isLab = isLab;
    this.sharedRoomNumber = sharedRoomNumber;
  }

  private Room(
      final int roomID,
      final int roomSeating,
      final String department,
      final boolean isLab
  ) {
    super();
    this.roomID = roomID;
    this.roomSeating = roomSeating;
    this.department = department;
    this.isLab = isLab;
    this.sharedRoomNumber = 0;
  }

  public static Room createRoom(
      final int roomNumber,
      final int roomSeating,
      final String department,
      final boolean isLab,
      final int sharedRoomNumber
  ) {return new Room(roomNumber, roomSeating, department, isLab, sharedRoomNumber);}

  public static Room createRoom(
      final int roomNumber,
      final int roomSeating,
      final String department,
      final boolean isLab
  ) {return new Room(roomNumber, roomSeating, department, isLab); }

  @Override
  public String toString() {
    return "Room{" +
        "roomID=" + this.roomID +
        ", roomSeating=" + this.roomSeating +
        ", department='" + this.department + '\'' +
        ", isLab=" + this.isLab +
        ", sharedRoomNumber=" + this.sharedRoomNumber +
        '}';
  }

  public int getRoomID() {
    return this.roomID;
  }

  public int getRoomSeating() {
    return this.roomSeating;
  }

  public String getDepartment() {
    return this.department;
  }

  public boolean isLab() {
    return this.isLab;
  }

  public int getSharedRoomNumber() {
    return this.sharedRoomNumber;
  }

  @Override
  public void addToDB() {
  }

  @Override
  public void removeFromDB() {
  }
}
