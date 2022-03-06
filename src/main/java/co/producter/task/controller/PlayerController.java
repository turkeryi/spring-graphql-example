package co.producter.task.controller;

import co.producter.task.entity.Player;
import co.producter.task.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerController {

  @Autowired
  private PlayerService playerService;

  @QueryMapping
  public Iterable<Player> players() {
    return playerService.getPlayers();
  }
}
