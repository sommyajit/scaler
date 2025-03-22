package scalerproject.entity.User;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserName {
    private String firstname;
    private String lastname;
}
