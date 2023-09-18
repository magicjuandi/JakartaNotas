package services.impl;


import domain.models.Student;
import reposistories.impl.StudentRepositoryLogicImpl;
import services.StudentService;

import java.util.List;

public class StudentServiceimpl implements StudentService {
    private final StudentRepositoryLogicImpl repository;

    public StudentServiceimpl(StudentRepositoryLogicImpl repository) {
        this.repository = repository;
    }
    @Override
    public List<Student> listar() {
        return repository.listar();
    }

    @Override
    public Student porId(Long id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Student t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
