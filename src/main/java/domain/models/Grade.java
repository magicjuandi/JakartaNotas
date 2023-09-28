package domain.models;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Grade {
    private Long id;
    private Student student;
    private Subject subject;
    private Double grade;
}
