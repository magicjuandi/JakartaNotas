package services;

import domain.models.Student;
import domain.models.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> listar();

    Teacher porId(Long id);

    void guardar(Teacher t);

    void eliminar(Long id);
}
