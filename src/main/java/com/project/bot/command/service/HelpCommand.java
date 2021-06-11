package com.project.bot.command.service;

import com.project.bot.command.Command;
import com.project.bot.config.BotConfig;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class HelpCommand implements Command {

  private final BotConfig botConfig;

  public HelpCommand(BotConfig botConfig) {
    this.botConfig = botConfig;
  }

  @Override
  public String execute(Update update) {

    String userName = update
        .getMessage()
        .getFrom()
        .getUserName();

    return
        "Good " + detectPartOfADayString() + ", " + userName + "!\n" +
            "I can do everything, for example:\n" +
            " command:\n" +
            "  " +
            botConfig.getCommandDelimiter() +
            "weather\n" +
            " flags:\n" +
            "  today\n" +
            "That's all!";
  }

  private String detectPartOfADayString() {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
    LocalDateTime now = LocalDateTime.now();
    short hours = Short.parseShort(dtf.format(now));
    if (hours >= 0 && hours <= 7) {
      return "night";
    } else if (hours >= 8 && hours <= 12) {
      return "morning";
    } else if (hours >= 13 && hours <= 18) {
      return "day";
    } else {
      return "evening";
    }
  }
}
