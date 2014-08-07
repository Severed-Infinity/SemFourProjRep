package com.davidswift.project.references;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 7/5/2014.
 */
public enum Table {
  USER("user"), COURSE("course"), MODULE("module"), ROOM("room"), CLASS("class");
  private final String value;

  Table(final String value) {this.value = value.toUpperCase();}

  public String getValue() {
    return this.value;
  }
}
