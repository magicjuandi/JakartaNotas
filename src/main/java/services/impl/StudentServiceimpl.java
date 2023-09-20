package services.impl;


import domain.models.Student;
import mapping.dtos.StudentDto;
import mapping.mappers.StudentMapper;
import reposistories.impl.StudentRepositoryLogicImpl;
import repository.impl.StudentRepositoryimpl;
import services.StudentService;

import java.util.List;

public class StudentServiceimpl implements StudentService {
    private final StudentRepositoryimpl repository;
    private final StudentMapper mapper;
    public StudentServiceimpl(StudentRepositoryimpl repository,StudentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public List<Student> list() {
        return mapper.mapFromDto((repository.list()));
    }

    @Override
    public Student byId(Long id) {
        return mapper.mapFrom(repository.byId(id));
    }

    @Override
    public void save(Student t) {
        StudentDto h = mapper.mapFrom(t);
        repository.save(h);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
