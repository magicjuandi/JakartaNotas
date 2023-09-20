package services;

import domain.models.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> list();

    Teacher byId(Long id);

    void save(Teacher t);

    void delete(Long id);
}
