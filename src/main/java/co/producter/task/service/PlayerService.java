package co.producter.task.service;

import co.producter.task.model.entity.Player;
import co.producter.task.model.input.PlayerInput;
import co.producter.task.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

  @Autowired
  private PlayerRepository playerRepository;

  public Iterable<Player> getPlayers() {
    return playerRepository.findAll();
  }

  public Player addPlayer(PlayerInput playerInput) {
    if (playerRepository.count() >= 12) {
      throw new RuntimeException();
    }
    Player player = new Player();
    player.setName(playerInput.getName());
    player.setSurname(playerInput.getSurname());
    player.setPosition(playerInput.getPosition());
    return playerRepository.save(player);
  }
}
