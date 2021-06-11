package com.project.bot.command;

public enum CommandType {
  HELP("help"), WEATHER("weather"), UNRECOGNIZED("unrecognized");

  private String name;

  CommandType(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
