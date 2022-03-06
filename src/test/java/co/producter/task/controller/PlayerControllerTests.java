package co.producter.task.controller;


import static org.mockito.Mockito.when;

import co.producter.task.enums.Position;
import co.producter.task.exception.TeamSizeMaxedOutException;
import co.producter.task.model.input.PlayerInput;
import co.producter.task.repository.PlayerRepository;
import co.producter.task.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerControllerTests {

  @InjectMocks
  private PlayerService playerService;

  @Mock
  private PlayerRepository playerRepository;

  private final PlayerInput input = new PlayerInput("John", "Doe", Position.C);

  @Test
  public void when_team_size_maxed_out_then_addPlayer_mutation_should_throw_exception() {
    when(playerRepository.count()).thenReturn(13L);

    Assertions.assertThrows(TeamSizeMaxedOutException.class, () -> playerService.addPlayer(input));
  }
}
