package reposistories.impl;

import domain.models.Subject;
import domain.models.Teacher;
import exceptions.UniversityException;
import reposistories.Repository;

import java.util.ArrayList;
import java.util.List;

public class SubjectRepositoryLogicimpl implements Repository<Subject> {
    private List<Subject> subjects;
    TeacherRepositoryLogicimpl teacherLog = new TeacherRepositoryLogicimpl();
    Teacher teacherb = teacherLog.getTeachers().get(0);

    public SubjectRepositoryLogicimpl(){
        Subject s1 = new Subject(1L, "Maths", teacherb);
        Subject s2 = new Subject(1L, "Science", new Teacher(3L, "Franco", "Franco@mail.com"));
        Subject s3 = new Subject(1L, "English", new Teacher(2L, "Paco", "Paco@mail.com"));
        subjects = new ArrayList<>(List.of(s1,s2,s3));
    }

    @Override
    public List<Subject> list() {
        return subjects;
    }

    @Override
    public Subject byId(Long id) {
        return subjects.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Subject not found"));
    }

    @Override
    public void save(Subject subject) {
        subjects.add(subject);
    }

    @Override
    public void delete(Long id) {
        subjects.removeIf(e->e.getId().equals(id));
    }
}
