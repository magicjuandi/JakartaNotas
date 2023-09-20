package services;

import domain.models.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> list();

    Subject byId(Long id);

    void save(Subject t);

    void delete(Long id);
}
