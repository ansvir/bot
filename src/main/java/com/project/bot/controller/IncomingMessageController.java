package com.project.bot.controller;

import com.project.bot.model.IncomingMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bot")
public class IncomingMessageController {

  @PostMapping
  public String post(@RequestBody IncomingMessage incomingMessage) {
    return "d1fb9708";
  }
}