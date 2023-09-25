package services.impl;

import domain.models.Teacher;
import mapping.dtos.TeacherDto;
import reposistories.impl.TeacherRepositoryLogicimpl;
import repository.Repository;
import repository.impl.TeacherRepositoryimpl;
import services.TeacherService;

import java.sql.Connection;
import java.util.List;

public class TeacherServiceimpl implements TeacherService {

    private Repository<TeacherDto> teacherRepository;
    public TeacherServiceimpl(Connection connection) {
        this.teacherRepository = new TeacherRepositoryimpl(connection);
    }
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
