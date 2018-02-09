package com.zappos.restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.zappos.restaurant.Part;

public class PartsMapper implements ResultSetMapper<Part> {
  private static final String ID = "id";
  private static final String NAME = "name";
  private static final String CODE = "code";

  public Part map(int i, ResultSet resultSet, StatementContext statementContext)
      throws SQLException {
    return new Part(resultSet.getInt(ID), resultSet.getString(NAME), resultSet.getString(CODE));
  }
}