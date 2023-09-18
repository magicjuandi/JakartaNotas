package domain.models;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Subject {
    private Long id;
    private String name;
    private Teacher teacher;

}
