package com.project.bot.command;

public enum CommandFlag {

  TODAY("today");

  private String name;

  CommandFlag(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
