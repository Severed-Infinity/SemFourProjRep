package com.davidswift.project.references;

import javafx.beans.property.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 7/26/2014.
 */
public enum UserType {
  FULL_TIME("Full Time"), PART_TIME("Part Time"), ADMIN("Admin");
  private final SimpleStringProperty type;

  UserType(final String type) {this.type = new SimpleStringProperty(type);}

  public SimpleStringProperty typeProperty() {
    return this.type;
  }

  public String getType() {
    return this.type.get();
  }

  @Override
  public String toString() {
    return "UserType{" +
        "type='" + this.type + '\'' +
        '}';
  }
}
