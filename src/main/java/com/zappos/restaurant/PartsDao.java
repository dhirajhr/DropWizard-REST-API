package com.zappos.restaurant;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.zappos.restaurant.PartsMapper;
import com.zappos.restaurant.Part;

@RegisterMapper(PartsMapper.class)
public interface PartsDao {

  @SqlQuery("select * from parts;")
  public List<Part> getParts();

  @SqlQuery("select * from parts where id = :id")
  public Part getPart(@Bind("id") final int id);

  @SqlUpdate("insert into parts(name, code) values(:name, :code)")
  void createPart(@BindBean final Part part);

  @SqlUpdate("update parts set name = coalesce(:name, name), code = coalesce(:code, code) where id = :id")
  void editPart(@BindBean final Part part);

  @SqlUpdate("delete from parts where id = :id")
  int deletePart(@Bind("id") final int id);

  @SqlQuery("select last_insert_id();")
  public int lastInsertId();
}