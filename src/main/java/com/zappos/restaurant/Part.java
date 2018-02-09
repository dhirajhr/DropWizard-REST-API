package com.zappos.restaurant;

import org.hibernate.validator.constraints.NotEmpty;

public class Part {
  private int id;
  @NotEmpty
  private String name;
  @NotEmpty
  private String code;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Part() {
    super();
  }

  public Part(int id, String name, String code) {
    super();
    this.id = id;
    this.name = name;
    this.code = code;
  }
}