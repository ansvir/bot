package com.project.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public interface Command {
  String execute(Update update);
}
