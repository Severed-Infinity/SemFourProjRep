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
public class RoomProperty implements IAddToDB {
  private static final Logger LOGGER = Logger.getLogger(RoomProperty.class.getName());
  private final SimpleIntegerProperty roomID;
  private final SimpleIntegerProperty roomSeating;
  private final SimpleStringProperty department;
  private final SimpleBooleanProperty isLab;
  private final SimpleIntegerProperty sharedRoomNumber;

  private RoomProperty(final RoomPropertyBuilder roomPropertyBuilder) {
    super();
    this.roomID = new SimpleIntegerProperty(roomPropertyBuilder.roomID);
    this.roomSeating = new SimpleIntegerProperty(roomPropertyBuilder.roomSeating);
    this.department = new SimpleStringProperty(roomPropertyBuilder.department);
    this.isLab = new SimpleBooleanProperty(roomPropertyBuilder.isLab);
    this.sharedRoomNumber = new SimpleIntegerProperty(roomPropertyBuilder.sharedRoomNumber);
  }

  public static RoomProperty createRoomProperty() {
    return RoomPropertyBuilder.createRoomPropertyBuilder()
        .createRoomProperty();
  }

  public SimpleIntegerProperty roomIDProperty() {
    return this.roomID;
  }

  public SimpleIntegerProperty roomSeatingProperty() {
    return this.roomSeating;
  }

  public SimpleStringProperty departmentProperty() {
    return this.department;
  }

  public SimpleBooleanProperty labProperty() {
    return this.isLab;
  }

  public SimpleIntegerProperty sharedRoomNumberProperty() {
    return this.sharedRoomNumber;
  }

  @Override
  public void addToDB() {
    LOGGER.log(Level.INFO, "Adding new room...");
    try {
      addToDB(Table.ROOM.getValue(), this.getRoomID(), this.getRoomSeating(),
          this.getDepartment(), this.isLab(), this.getSharedRoomNumber());
      LOGGER.log(Level.INFO, "New room added.");
    } catch (final SQLException e) {
      LOGGER.log(Level.SEVERE, "Unable to add a room", e);
    }
  }

  protected int getRoomID() {
    return this.roomID.get();
  }

  protected int getRoomSeating() {
    return this.roomSeating.get();
  }

  protected String getDepartment() {
    return this.department.get();
  }

  protected boolean isLab() {
    return this.isLab.get();
  }

  protected int getSharedRoomNumber() {
    return this.sharedRoomNumber.get();
  }

  public static class RoomPropertyBuilder {
    public int roomID;
    public int roomSeating;
    public String department;
    public boolean isLab;
    public int sharedRoomNumber;

    private RoomPropertyBuilder() {super();}

    public static RoomPropertyBuilder createRoomPropertyBuilder() {
      return new RoomPropertyBuilder
          ();
    }

    public RoomPropertyBuilder setRoomID(final int roomID) {
      this.roomID = roomID;
      return this;
    }

    public RoomPropertyBuilder setRoomSeating(final int roomSeating) {
      this.roomSeating = roomSeating;
      return this;
    }

    public RoomPropertyBuilder setDepartment(final String department) {
      this.department = department;
      return this;
    }

    public RoomPropertyBuilder setLab(final boolean isLab) {
      this.isLab = isLab;
      return this;
    }

    public RoomPropertyBuilder setSharedRoomNumber(final int sharedRoomNumber) {
      this.sharedRoomNumber = sharedRoomNumber;
      return this;
    }

    public RoomProperty createRoomProperty() {
      return new RoomProperty(this);
    }
  }
}
