package com.project.bot.impl;

import com.project.bot.command.Command;
import com.project.bot.command.CommandDefiner;
import com.project.bot.command.CommandFlag;
import com.project.bot.command.CommandType;
import com.project.bot.config.BotConfig;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {

  private final BotConfig botConfig;

  @Getter
  private static Map<String, Set<String>> commands;

  @Autowired
  private CommandDefiner commandDefiner;

  public Bot(BotConfig botConfig) {
    this.botConfig = botConfig;
    initializeCommands();
  }

  private static void initializeCommands() {
    commands = new HashMap<>();
    Set<String> commandWeatherFlags = new HashSet<>();
    commandWeatherFlags.add(CommandFlag.TODAY.getName());
    commands.put(CommandType.WEATHER.getName(), commandWeatherFlags);
    commands.put(CommandType.HELP.getName(), null);
  }

  public void onUpdateReceived(Update update) {
    update.getUpdateId();
    SendMessage.SendMessageBuilder messageBuilder = SendMessage.builder();
    String messageText;
    String chatId;

    if (update.getMessage() != null) {
      chatId = update.getMessage().getChatId().toString();
      messageBuilder.chatId(chatId);
      messageText = update.getMessage().getText();
      messageText = messageText.strip();
      if (!messageText.startsWith(botConfig.getCommandDelimiter())) {
        return;
      }
      Command command = commandDefiner.defineCommand(messageText);
      messageBuilder.text(command.execute(update));
      try {
        execute(messageBuilder.build());
      } catch (TelegramApiException e) {
        e.printStackTrace();
      }
    }
  }

  public String getBotUsername() {
    return botConfig.getBotUserName();
  }

  public String getBotToken() {
    return botConfig.getToken();
  }
}