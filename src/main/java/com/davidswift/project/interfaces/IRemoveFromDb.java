package com.davidswift.project.interfaces;

import com.davidswift.project.utility.*;

import java.sql.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/29/2014.
 */
public interface IRemoveFromDb {
  //TODO change object args to int (for ID)
  public default <String, T> void removeFromDB(String table, T t) throws SQLException {
    java.lang.String delete = "Delete from " + table + "table where " + table +
        "_ID = "
        + t;
    System.out.println(delete);
    PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement
        (delete);
    ps.executeQuery();
  }
}
