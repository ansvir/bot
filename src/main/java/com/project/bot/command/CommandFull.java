package com.project.bot.command;

import com.project.bot.command.service.HelpCommand;
import com.project.bot.command.service.UnrecognizedCommand;
import com.project.bot.command.weather.WeatherTodayCommand;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public enum CommandFull {

  WEATHER_TODAY(CommandType.WEATHER.getName() + " " + CommandFlag.TODAY.getName()),
  HELP(CommandType.HELP.getName()),
  UNRECOGNIZED(CommandType.UNRECOGNIZED.getName());

  @Component
  public static class CommandFullInjector {

    @Autowired
    HelpCommand helpCommand;

    @Autowired
    WeatherTodayCommand weatherTodayCommand;

    @Autowired
    UnrecognizedCommand unrecognizedCommand;

    @PostConstruct
    public void postConstruct() {
      CommandFull.HELP.setCommand(helpCommand);
      CommandFull.WEATHER_TODAY.setCommand(weatherTodayCommand);
      CommandFull.UNRECOGNIZED.setCommand(unrecognizedCommand);;
    }
  }

  private String name;
  private Command command;

  CommandFull(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public Command getCommand() {
    return command;
  }

  public void setCommand(Command command) {
    this.command = command;
  }
}
