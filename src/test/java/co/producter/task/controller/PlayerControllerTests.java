package co.producter.task.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import co.producter.task.enums.Position;
import co.producter.task.model.entity.Player;
import co.producter.task.model.input.PlayerInput;
import co.producter.task.repository.PlayerRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerControllerTests {

  @Autowired
  private GraphQlTester graphQlTester;

  @MockBean
  private PlayerRepository playerRepository;

  private PlayerInput playerInput;

  private final Player player = new Player(1L, "John", "Doe", Position.C);
  private final List<Player> players = Collections.singletonList(player);
  private final TypeReference<Map<String, Object>> mapTypeReference = new TypeReference<>() {};

  @Test
  void when_no_players_exists_then_players_query_should_return_empty_list() {
    when(playerRepository.findAll()).thenReturn(new ArrayList<>());

    this.graphQlTester.queryName("players")
        .execute()
        .path("players")
        .valueIsEmpty();
  }

  @Test
  void when_any_player_exists_then_players_query_should_return_players_list() {
    when(playerRepository.findAll()).thenReturn(players);

    this.graphQlTester.queryName("players")
        .execute()
        .path("players")
        .entityList(Player.class)
        .hasSize(1);
  }

  @Test
  void when_any_player_exists_then_players_query_should_return_player_in_list() {
    when(playerRepository.findAll()).thenReturn(players);

    this.graphQlTester.queryName("players")
        .execute()
        .path("players[0].id").entity(long.class).isEqualTo(1L)
        .path("players[0].name").entity(String.class).isEqualTo("John")
        .path("players[0].surname").entity(String.class).isEqualTo("Doe")
        .path("players[0].position").entity(Position.class).isEqualTo(Position.C);
  }

  @Test
  void given_playerInput_then_addPlayer_mutation_should_return_success() {
    playerInput = new PlayerInput("John", "Doe", Position.C);
    when(playerRepository.save(any(Player.class))).thenReturn(player);
    Map<String, Object> inputMap = new ObjectMapper().convertValue(playerInput, mapTypeReference);

    this.graphQlTester.queryName("addPlayer")
        .variable("playerInput", inputMap)
        .executeAndVerify();
  }

  @Test
  void given_playerInput_when_name_null_then_addPlayer_mutation_should_return_error() {
    playerInput = new PlayerInput(null, "Doe", Position.C);
    when(playerRepository.save(any(Player.class))).thenReturn(player);
    Map<String, Object> inputMap = new ObjectMapper().convertValue(playerInput, mapTypeReference);

    this.graphQlTester.queryName("addPlayer")
        .variable("playerInput", inputMap)
        .execute()
        .errors()
        .expect((error) -> error.getErrorType().toString().equals("ValidationError"))
        .verify();
  }

  @Test
  void given_playerInput_when_player_count_gte_twenty_then_addPlayer_mutation_should_return_error() {
    playerInput = new PlayerInput("John", "Doe", Position.C);
    when(playerRepository.save(any(Player.class))).thenReturn(player);
    when(playerRepository.count()).thenReturn(12L);
    Map<String, Object> inputMap = new ObjectMapper().convertValue(playerInput, mapTypeReference);

    this.graphQlTester.queryName("addPlayer")
        .variable("playerInput", inputMap)
        .execute()
        .errors()
        .expect((error) -> error.getErrorType().equals(ErrorType.INTERNAL_ERROR))
        .verify();
  }
}
