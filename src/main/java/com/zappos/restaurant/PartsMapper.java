package com.zappos.restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.zappos.restaurant.Part;

public class PartsMapper implements ResultSetMapper<Part> {
  private static final String HID = "hid";
  private static final String CHUNK = "chunk";
  private static final String HAS_SPACE = "has_space";

  public Part map(int i, ResultSet resultSet, StatementContext statementContext)
      throws SQLException {
    return new Part(resultSet.getString(HID), resultSet.getString(CHUNK), resultSet.getString(HAS_SPACE));
  }
}