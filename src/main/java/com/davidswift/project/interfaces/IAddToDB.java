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
public interface IAddToDB {
  public default void addToDB(String table, Object... args) throws
      SQLException {
    int i = 1;
    StringBuilder stringBuilder = new StringBuilder();
    //initial start of insert statement
    stringBuilder.append("Insert into ").append(table).append("table values (");
    //dynamic adding of arguments
    for (int j = 1; j < args.length; j++) {
      stringBuilder.append("?,");
    }
    stringBuilder.append("?)"); // end of insert statement
    System.out.println(stringBuilder.toString());//testing the output of the insert statement
    final PreparedStatement ps = DatabaseConnection.getInstance().getConnection().prepareStatement(
        stringBuilder.toString()); //gathering the string to be executed
    //running through the passed in arguments and finding there types
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
    ps.executeQuery();//executing the insert
  }
}
