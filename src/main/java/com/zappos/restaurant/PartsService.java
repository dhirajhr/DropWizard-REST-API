package com.zappos.restaurant;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.zappos.restaurant.PartsDao;
import com.zappos.restaurant.Part;

import java.util.List;
import java.util.Objects;





public abstract class PartsService {
  private static final String PART_NOT_FOUND = "Part id %s not found.";
  private static final String DATABASE_REACH_ERROR =
      "Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: ";
  private static final String DATABASE_CONNECTION_ERROR =
      "Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: ";
  private static final String DATABASE_UNEXPECTED_ERROR =
      "Unexpected error occurred while attempting to reach the database. Details: ";
  private static final String SUCCESS = "Success...";
  private static final String UNEXPECTED_ERROR = "An unexpected error occurred while deleting part.";

  @CreateSqlObject
  abstract PartsDao partsDao();

  public List<Part> getParts() {
    return partsDao().getParts();
  }

  public Part getPart(int id) {
    Part part = partsDao().getPart(id);
    if (Objects.isNull(part)) {
      throw new WebApplicationException(String.format(PART_NOT_FOUND, id), Status.NOT_FOUND);
    }
    return part;
  }

  public Part createPart(Part part) {
    partsDao().createPart(part);
    return partsDao().getPart(partsDao().lastInsertId());
  }

  public Part editPart(Part part) {
    if (Objects.isNull(partsDao().getPart(part.getId()))) {
      throw new WebApplicationException(String.format(PART_NOT_FOUND, part.getId()),
          Status.NOT_FOUND);
    }
    partsDao().editPart(part);
    return partsDao().getPart(part.getId());
  }

  public String deletePart(final int id) {
    int result = partsDao().deletePart(id);
    switch (result) {
      case 1:
        return SUCCESS;
      case 0:
        throw new WebApplicationException(String.format(PART_NOT_FOUND, id), Status.NOT_FOUND);
      default:
        throw new WebApplicationException(UNEXPECTED_ERROR, Status.INTERNAL_SERVER_ERROR);
    }
  }

  public String performHealthCheck() {
    try {
      partsDao().getParts();
    } catch (UnableToObtainConnectionException ex) {
      return checkUnableToObtainConnectionException(ex);
    } catch (UnableToExecuteStatementException ex) {
      return checkUnableToExecuteStatementException(ex);
    } catch (Exception ex) {
      return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
    }
    return null;
  }

  private String checkUnableToObtainConnectionException(UnableToObtainConnectionException ex) {
    if (ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
      return DATABASE_REACH_ERROR + ex.getCause().getLocalizedMessage();
    } else if (ex.getCause() instanceof java.sql.SQLException) {
      return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
    } else {
      return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
    }
  }

  private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
    if (ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
      return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
    } else {
      return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
    }
  }
}