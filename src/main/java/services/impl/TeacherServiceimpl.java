package services.impl;

import domain.models.Teacher;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import mapping.dtos.TeacherDto;
import reposistories.impl.TeacherRepositoryLogicimpl;
import repository.Repository;
import repository.impl.TeacherRepositoryimpl;
import services.TeacherService;

import java.sql.Connection;
import java.util.List;

public class TeacherServiceimpl implements TeacherService {
    @Inject
    @Named("teacherRep")
    private Repository<TeacherDto> teacherRepository;

    @Override
    public List<TeacherDto> list() {
        return teacherRepository.list();
    }

    @Override
    public TeacherDto byId(Long id) {
        return teacherRepository.byId(id);
    }

    @Override
    public void save(TeacherDto t) {
        teacherRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        teacherRepository.delete(id);
    }
}
