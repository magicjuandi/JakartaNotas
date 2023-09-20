package services.impl;

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
    public List<Teacher> list() {
        return repository.list();
    }

    @Override
    public Teacher byId(Long id) {
        return repository.byId(id);
    }

    @Override
    public void save(Teacher t) {
        repository.save(t);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }
}
