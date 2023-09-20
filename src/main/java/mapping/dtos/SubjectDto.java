package mapping.dtos;

import domain.models.Teacher;

public record SubjectDto(Long id,
                         String name,
                         Teacher teacher) {
}
