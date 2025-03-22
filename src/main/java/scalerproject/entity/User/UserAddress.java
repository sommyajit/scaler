package scalerproject.entity.User;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    private String city;
    private String street;
    private Integer number;
    private String zipcode;
}
