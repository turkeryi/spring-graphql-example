package co.producter.task.repository;

import co.producter.task.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface PlayerRepository extends JpaRepository<Player, Long> {
}
