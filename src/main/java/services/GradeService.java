package services;

import mapping.dtos.GradeDto;

import java.util.List;

public interface GradeService {
    List<GradeDto> list();
    GradeDto byId(Long id);
    void save(GradeDto student);
    void delete(Long id);
}
