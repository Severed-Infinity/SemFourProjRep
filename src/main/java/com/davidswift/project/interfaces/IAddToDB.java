package com.davidswift.project.interfaces;

import java.sql.*;
import java.util.Date;

/**
 * Project SemFourProjRep
 *
 * This class is part of a project
 * that is aimed at improving ITT's
 * timetable system
 *
 * Created by david on 6/29/2014.
 */
public interface IAddToDB {

  //TODO add connection capabilities
  public default void addToDB(PreparedStatement ps, String table, Object... args) throws
      SQLException {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Insert into " + table + " values (");

    int i = 1;
    for (Object arg : args) {
      stringBuilder.append("?");
      if (i != args.length) {
        stringBuilder.append(",");
      }

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

    stringBuilder.append(");");
    System.out.println(stringBuilder.toString());

    ps = connection.prepareStatement(stringBuilder.toString());
    ps.executeUpdate();
  }
}
