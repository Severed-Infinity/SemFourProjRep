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
public interface IUpdateInDB {
  public static final Logger LOGGER = Logger.getLogger(IUpdateInDB.class.getName());

  public default void updateInDB(
      final String table, final String[] columns, final Object... args
  ) throws SQLException {
    int i = 1;
    final StringBuilder update = new StringBuilder(16).append("Update ").append(table).append
        ("TABLE set ");
    for (int i1 = 0, columnsLength = columns.length; i1 < columnsLength; i1++) {
      final String column = columns[i1];
      update.append(column.toUpperCase()).append(" = ? ");
      if (i1 != columnsLength - 1) {
        update.append(", ");
      }
    }
    update.append("Where ").append(table).append("_ID = ?");
    final String updateInDBString = update.toString();
    try (Connection connection = DatabaseConnection.getInstance(); PreparedStatement ps =
        connection.prepareStatement
        (updateInDBString)) {
      for (final Object arg : args) {
        if (arg instanceof Date) {
          ps.setTimestamp(i++, new Timestamp(((Date)arg).getTime()));
        } else if (arg instanceof Integer) {
          ps.setInt(i++, (Integer)arg);
        } else if (arg instanceof Long) {
          ps.setLong(i++, (Long)arg);
        } else if (arg instanceof Double) {
          ps.setDouble(i++, (Double)arg);
        } else if (arg instanceof Float) {
          ps.setFloat(i++, (Float)arg);
        } else {
          ps.setString(i++, (String)arg);
        }
      }
      LOGGER.log(Level.INFO, String.valueOf(update));
      ps.executeUpdate();
      this.update(args);
      ps.close();
    }
  }

  //must pass in all values that can be modified
  public abstract void update(Object... args);

}
