package com.example.famerhelper;

public class User {
  public String username;
  public String password;
  public String email;
  public boolean isOwner;
  public String firstName;
  public String familyName;

  public User(String username, String password, String email, boolean isOwner,
              String firstName, String familyName) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.isOwner = isOwner;
    this.firstName = firstName;
    this.familyName = familyName;
  }
}
