package com.project.bot.model;

import lombok.Data;

@Data
public class IncomingMessage {
  private String type;
  private Topic object;
  private Long group_id;
  private String secret;
}
