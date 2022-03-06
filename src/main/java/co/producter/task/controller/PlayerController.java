package co.producter.task.controller;

import co.producter.task.model.entity.Player;
import co.producter.task.model.input.PlayerInput;
import co.producter.task.service.PlayerService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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

  @MutationMapping
  public Player addPlayer(@Argument @Valid PlayerInput playerInput) {
    return playerService.addPlayer(playerInput);
  }
}
