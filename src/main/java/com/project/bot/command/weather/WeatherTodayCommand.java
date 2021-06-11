package com.project.bot.command.weather;

import com.project.bot.command.Command;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class WeatherTodayCommand implements Command {

  @Override
  public String execute(Update update) {
    return "Today should be gooooooooood weather, dear " +
        update
            .getMessage()
            .getFrom()
            .getUserName() +
        "!";
  }
}
