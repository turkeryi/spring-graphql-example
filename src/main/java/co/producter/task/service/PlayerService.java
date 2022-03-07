package co.producter.task.service;

import co.producter.task.exception.TeamSizeMaxedOutException;
import co.producter.task.model.entity.Player;
import co.producter.task.model.input.PlayerInput;
import co.producter.task.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PlayerRepository playerRepository;

  public Iterable<Player> getPlayers() {
    return playerRepository.findAll();
  }

  public Player addPlayer(PlayerInput playerInput) {
    if (playerRepository.count() >= 12) {
      logger.error("Team size is already maxed out. ");
      throw new TeamSizeMaxedOutException("Team size is already maxed out. ");
    }
    Player player = new Player();
    player.setName(playerInput.getName());
    player.setSurname(playerInput.getSurname());
    player.setPosition(playerInput.getPosition());
    return playerRepository.save(player);
  }

  public boolean deletePlayer(Long id) {
    playerRepository.deleteById(id);
    logger.info("Player " + id + " deleted successfully.");
    return true;
  }
}
