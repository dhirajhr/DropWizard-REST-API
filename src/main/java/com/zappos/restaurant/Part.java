package com.zappos.restaurant;

import org.hibernate.validator.constraints.NotEmpty;

public class Part {
	 


 /* public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}*/

//	 @NotEmpty
//private String restaurant;
 // @NotEmpty
 // private String menu;
  //@NotEmpty
  private String hid;
  private String chunk;
  private String has_space;
 //private Integer id;
 
  public Part() {
    super();
  }


public String getHid() {
	return hid;
}


public void setRestaurant(String hid) {
	this.hid = hid;
}


public String getChunk() {
	return chunk;
}


public void setChunk(String chunk) {
	this.chunk = chunk;
}


public String getHas_space() {
	return has_space;
}


public void setHas_space(String has_space) {
	this.has_space = has_space;
}





public Part(String hid, String chunk, String has_space) {
	super();
	this.hid = hid;
	this.chunk = chunk;
	this.has_space = has_space;

}

 
}