package repository;

import mapping.dtos.GradeDto;
import mapping.dtos.StudentDto;

import java.util.List;

public interface GradeRepository {
    List<GradeDto> list();
    GradeDto byId(Long id);
    void save(GradeDto student);
    void delete(Long id);
}
