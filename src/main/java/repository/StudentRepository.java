package repository;

import mapping.dtos.StudentDto;

import java.util.List;

public interface StudentRepository {
    List<StudentDto> list();
    StudentDto byId(Long id);
    void save(StudentDto student);
    void delete(Long id);
}
