package reposistories.impl;

import domain.models.Student;
import exceptions.UniversityException;
import reposistories.Repository;

import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryLogicImpl implements Repository<Student> {
    private List<Student> students;

    public StudentRepositoryLogicImpl() {
        Student s1 = new Student(1L,"Monica", "Monica@mail.com", "2");
        Student s2 = new Student(2L,"Pepe", "Pepe@mail.com", "5");;
        Student s3 = new Student(3L,"Juan", "Juan@mail.com", "9");
        students = new ArrayList<>(List.of(s1, s2, s3));
    }

    @Override
    public List<Student> listar() {
        return students;
    }

    @Override
    public Student porId(Long id) {
        return students.stream()
                .filter(e->id.equals(e.getId()))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Student not found"));
    }

    @Override
    public void guardar(Student student) {
        students.add(student);
    }

    @Override
    public void eliminar(Long id) {
        students.removeIf(e->e.getId().equals(id));
    }
}
