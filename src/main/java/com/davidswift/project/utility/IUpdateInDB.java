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
      update.append(column.toUpperCase()).append(" = ?");
    }
    PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement
        (update.toString());
    System.out.println(update);
    //TODO figure out why it fails here
    ps.executeUpdate();

    ResultSet resultSet = ps.getResultSet();
    while (resultSet.next()) {
      if (args[i] instanceof Date) {
        ps.setTimestamp(i++, new Timestamp(((Date)args[i]).getTime()));
      } else if (args[i] instanceof Integer) {
        ps.setInt(i++, (Integer)args[i]);
      } else if (args[i] instanceof Long) {
        ps.setLong(i++, (Long)args[i]);
      } else if (args[i] instanceof Double) {
        ps.setDouble(i++, (Double)args[i]);
      } else if (args[i] instanceof Float) {
        ps.setFloat(i++, (Float)args[i]);
      } else {
        ps.setString(i++, (String)args[i]);
      }

    }
    System.out.println(update);
    ps.executeUpdate();
    this.update(args);
  }

  public abstract void update(Object... args);

}
