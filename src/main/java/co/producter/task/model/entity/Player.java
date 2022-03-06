package co.producter.task.model.entity;

import co.producter.task.enums.Position;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "player")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;

  @NotBlank(message = "Name could not be blank.")
  @Length(message = "Name could not be longer than 60 character.", max = 60)
  @Column(name = "name")
  private String name;

  @NotBlank(message = "Surname could not be blank.")
  @Length(message = "Surname could not be longer than 60 character.", max = 60)
  @Column(name = "surname")
  private String surname;

  @NotNull(message = "Position could not be null.")
  @Enumerated(EnumType.STRING)
  @Column(name = "position")
  private Position position;
}
