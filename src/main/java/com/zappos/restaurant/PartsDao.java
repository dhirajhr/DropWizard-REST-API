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

  @SqlQuery("select * from startupcommercial;")
  public List<Part> getPart();

  @SqlQuery("select * from startupcommercial where hid = :hid")
  public Part getPart(@Bind("hid") final String hid);
  
  /*@SqlQuery("select /** from food where id = :id")
  public Part getPart(@Bind("id") final int id);*/

  @SqlUpdate("insert into startupcommercial(hid, chunk, has_space) values(:hid, :chunk, :has_space)")
  void createPart(@BindBean final Part part);

 /* @SqlUpdate("update food set name = coalesce(:name, name), code = coalesce(:code, code) where id = :id")
  void editPart(@BindBean final Part part);*/


  /*@SqlQuery("select last_insert_Restaurant();")
  public String lastInsertRestaurant();*/
}