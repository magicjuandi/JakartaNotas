package services;

import domain.models.Student;
import mapping.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> list();

    StudentDto byId(Long id);

    void save(StudentDto t);

    void delete(Long id);
}
