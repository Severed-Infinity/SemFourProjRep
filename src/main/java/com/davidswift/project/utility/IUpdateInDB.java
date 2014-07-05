package com.davidswift.project.utility;

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
@FunctionalInterface
public interface IUpdateInDB {
  public default void updateInDB(
      String table, String[] columns, Object... args
  ) throws SQLException {
    int i = 1;
    StringBuilder update = new StringBuilder().append("Update ").append(table).append("table set ");
    for (String column : columns) {
      update.append(column.toUpperCase()).append(" = ? ");
    }
    update.append("Where ").append(table).append("_ID = ?");
    PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement
        (update.toString());
    for (Object arg : args) {
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
    System.out.println(update);
    ps.executeUpdate();
    this.update(args);
  }

  public abstract void update(Object... args);

}
