package co.producter.task.controller;

import static org.mockito.BDDMockito.given;

import co.producter.task.entity.Player;
import co.producter.task.enums.Position;
import co.producter.task.repository.PlayerRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

@SpringBootTest
@AutoConfigureGraphQlTester
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PlayerControllerTests {

  @Autowired
  private GraphQlTester graphQlTester;

  @MockBean
  private PlayerRepository playerRepository;

  private final Player player = new Player(1L, "John", "Doe", Position.C);
  private final List<Player> players = Collections.singletonList(player);

  @Test
  void when_no_players_exists_then_players_query_should_return_empty_list() {
    given(this.playerRepository.findAll()).willReturn(new ArrayList<>());

    this.graphQlTester.queryName("players")
        .execute()
        .path("players")
        .valueIsEmpty();
  }

  @Test
  void when_any_player_exists_then_players_query_should_return_players_list() {
    given(this.playerRepository.findAll()).willReturn(players);

    this.graphQlTester.queryName("players")
        .execute()
        .path("players").entityList(Player.class).hasSize(1)
        .path("players[0].id").entity(long.class).isEqualTo(1L)
        .path("players[0].name").entity(String.class).isEqualTo("John")
        .path("players[0].surname").entity(String.class).isEqualTo("Doe")
        .path("players[0].position").entity(Position.class).isEqualTo(Position.C);
  }
}
