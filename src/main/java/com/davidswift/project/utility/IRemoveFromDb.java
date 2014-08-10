package com.davidswift.project.utility;

import java.sql.*;
import java.util.logging.*;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/29/2014.
 */
@FunctionalInterface
public interface IRemoveFromDb {
  public static final Logger LOGGER = Logger.getLogger(IRemoveFromDb.class.getName());

  public default <T> void removeFromDB(final String table, final T t) throws SQLException {
    final String delete = "Delete from " + table + "TABLE where " + table +
        "_ID = "
        + t;
    LOGGER.log(Level.INFO, delete);
    try (Connection connection = DatabaseConnection.getInstance(); PreparedStatement ps =
        connection.prepareStatement
        (delete)) {
      ps.executeUpdate();
      ps.close();
    }
  }

  public abstract void removeFromDB();
}
