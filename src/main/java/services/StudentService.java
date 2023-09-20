package services;

import domain.models.Student;
import mapping.dtos.StudentDto;

import java.util.List;

public interface StudentService {
    List<Student> list();

    Student byId(Long id);

    void save(Student t);

    void delete(Long id);
}
