package com.davidswift.project.utility;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/29/2014.
 */
public interface IUpdateInDB {
  public default <T> void updateInDB(String table, T t) {

  }
}
