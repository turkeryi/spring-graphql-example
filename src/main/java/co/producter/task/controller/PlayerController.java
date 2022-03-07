package co.producter.task.controller;

import co.producter.task.model.entity.Player;
import co.producter.task.model.input.PlayerInput;
import co.producter.task.service.PlayerService;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PlayerController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PlayerService playerService;

  @QueryMapping
  public Iterable<Player> players() {
    logger.info("User tyring to get all players.");
    return playerService.getPlayers();
  }

  @MutationMapping
  public Player addPlayer(@Argument @Valid PlayerInput playerInput) {
    logger.info("User tyring to add new player.");
    return playerService.addPlayer(playerInput);
  }

  @MutationMapping
  public boolean deletePlayer(@Argument Long id) {
    logger.info("User tyring to delete Player " + id + ".");
    return playerService.deletePlayer(id);
  }
}
