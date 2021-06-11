package com.project.bot.command.service;

import com.project.bot.command.Command;
import com.project.bot.command.CommandFull;
import com.project.bot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UnrecognizedCommand implements Command {

  private BotConfig botConfig;

  public UnrecognizedCommand(BotConfig botConfig) {
    this.botConfig = botConfig;
  }

  @Override
  public String execute(Update update) {
    return "Command not recognized, retry typing or try '" + botConfig.getCommandDelimiter() + CommandFull.HELP.getName() + "'.";
  }
}
