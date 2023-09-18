package services;

import domain.models.Student;
import domain.models.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> listar();

    Subject porId(Long id);

    void guardar(Subject t);

    void eliminar(Long id);
}
