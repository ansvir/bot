package com.project.bot.model;

import java.util.Date;
import lombok.Data;

@Data
public class Topic {
  private Long id;
  private Date date;
  private Integer out;
  private Long user_id;
  private Integer read_state;
  private String title;
  private String body;
}
