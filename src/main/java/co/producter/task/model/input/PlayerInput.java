package co.producter.task.model.input;

import co.producter.task.enums.Position;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInput {

  @NotBlank(message = "Name could not be blank.")
  @Size(message = "Name could not be longer than 60 character.", max = 60)
  private String name;

  @NotBlank(message = "Surname could not be blank.")
  @Size(message = "Surname could not be longer than 60 character.", max = 60)
  private String surname;

  @NotNull(message = "Position could not be null.")
  @Enumerated(EnumType.STRING)
  private Position position;
}
