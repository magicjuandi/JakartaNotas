package reposistories.impl;

import domain.models.Teacher;
import exceptions.UniversityException;
import reposistories.Repository;

import java.util.ArrayList;
import java.util.List;

public class TeacherRepositoryLogicimpl implements Repository<Teacher> {

    private List<Teacher> teachers;
    public TeacherRepositoryLogicimpl(){
        Teacher t1 = new Teacher(1L, "Gabriel", "Gabriel@mail.com");
        Teacher t2 = new Teacher(2L, "Paco", "Paco@mail.com");
        Teacher t3 = new Teacher(3L, "Franco", "Franco@mail.com");
        teachers = new ArrayList<>(List.of(t1,t2,t3));
    }
    @Override
    public List<Teacher> listar() {
        return teachers;
    }

    @Override
    public Teacher porId(Long id) {
        return teachers.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Teacher not found"));
    }

    @Override
    public void guardar(Teacher teacher) {
        teachers.add(teacher);

    }

    @Override
    public void eliminar(Long id) {
        teachers.removeIf(e->e.getId().equals(id));
    }
}