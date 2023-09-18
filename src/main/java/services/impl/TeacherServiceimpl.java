package services.impl;

import domain.models.Student;
import domain.models.Teacher;
import reposistories.impl.TeacherRepositoryLogicimpl;
import services.TeacherService;

import java.util.List;

public class TeacherServiceimpl implements TeacherService {

    private final TeacherRepositoryLogicimpl repository;
    public TeacherServiceimpl(TeacherRepositoryLogicimpl repository) {
        this.repository = repository;
    }
    @Override
    public List<Teacher> listar() {
        return repository.listar();
    }

    @Override
    public Teacher porId(Long id) {
        return repository.porId(id);
    }

    @Override
    public void guardar(Teacher t) {
        repository.guardar(t);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
