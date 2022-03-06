package co.producter.task.service;

import co.producter.task.entity.Player;
import co.producter.task.repository.PlayerRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class PlayerService {

  @Autowired
  private PlayerRepository playerRepository;

  public Iterable<Player> getPlayers() {
    return playerRepository.findAll();
  }
}
