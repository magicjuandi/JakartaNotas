package services;

import domain.models.Teacher;
import mapping.dtos.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> list();

    TeacherDto byId(Long id);

    void save(TeacherDto t);

    void delete(Long id);
}
