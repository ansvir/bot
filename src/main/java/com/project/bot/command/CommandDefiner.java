package com.project.bot.command;

import com.project.bot.config.BotConfig;
import com.project.bot.impl.Bot;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class CommandDefiner {

  private BotConfig botConfig;

  public CommandDefiner(BotConfig botConfig) {
    this.botConfig = botConfig;
  }

  private final String COMMAND_PATTERN = "^![a-z]+(\\s[a-z]+)*$";

  public Command defineCommand(String message) {
    String command = null;
    if (message.matches(COMMAND_PATTERN)) {
      Map<String, Set<String>> commands = Bot.getCommands();
      int indexOfFirstSpace = message.indexOf(" ");
      if (indexOfFirstSpace < 0) {
        command = message.substring(botConfig.getCommandDelimiter().length());
      } else {
        String userCommandString = message.substring(botConfig.getCommandDelimiter().length(), indexOfFirstSpace);
        String flagsString = message.substring(indexOfFirstSpace + 1);
        Set<String> flagsSet = new HashSet<>();
        String newFlagsString = flagsString;
        if (!newFlagsString.contains(" ")) {
          flagsSet.add(newFlagsString);
        } else {
          for (int indexOfNextSpace = flagsString.indexOf(" "); indexOfNextSpace >= 0;
              indexOfNextSpace = flagsString.indexOf(" ")) {
            flagsSet.add(newFlagsString.substring(0, indexOfNextSpace));
            newFlagsString = flagsString.substring(indexOfNextSpace + 1);
          }
        }
        for (Map.Entry<String, Set<String>> fullCommandLine : commands.entrySet()) {
          if (userCommandString.equals(fullCommandLine.getKey()) && flagsSet.equals(fullCommandLine.getValue())) {
            StringBuilder fullBuiltCommand = new StringBuilder();
            fullBuiltCommand.append(fullCommandLine.getKey());
            for (String flag : fullCommandLine.getValue()) {
              fullBuiltCommand.append(" ");
              fullBuiltCommand.append(flag);
            }
            command = fullBuiltCommand
                .toString();
          }
        }
      }
    }

    if (command != null) {
      for (CommandFull commandFull : CommandFull.values()) {
        if (commandFull.name().equals(
            command
                .toUpperCase()
                .replace(" ", "_"))
        ) {
          return commandFull.getCommand();
        }
      }
    }
    return CommandFull
        .valueOf(CommandType
            .UNRECOGNIZED
            .getName()
            .toUpperCase())
        .getCommand();
  }
}
