package domain.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Student {
    private Long id;
    private String name;
    private String email;
    private String semester;
}
