package services.impl;


import exceptions.ServiceJdbcException;
import mapping.dtos.StudentDto;
import repository.Repository;
import repository.impl.StudentRepositoryimpl;
import services.StudentService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StudentServiceimpl implements StudentService {
    private Repository<StudentDto> studentRepository;

    public StudentServiceimpl(Connection connection) {
        this.studentRepository = new StudentRepositoryimpl(connection);
    }

    @Override
    public List<StudentDto> list() {
        return studentRepository.list();
    }

    @Override
    public StudentDto byId(Long id) {
            return studentRepository.byId(id);
    }

    @Override
    public void save(StudentDto t) {
        studentRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        studentRepository.delete(id);
    }
}
