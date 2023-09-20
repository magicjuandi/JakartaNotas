package repository.impl;

import domain.models.Student;
import exceptions.UniversityException;
import mapping.dtos.StudentDto;
import mapping.mappers.StudentMapper;
import repository.StudentRepository;
import singledomain.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryimpl implements StudentRepository {
    private List<Student> students;

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
    public StudentRepositoryimpl(){
        Student s1 = new Student(1L,"Monica", "Monica@mail.com", "2");
        Student s2 = new Student(2L,"Pepe", "Pepe@mail.com", "5");;
        Student s3 = new Student(3L,"Juan", "Juan@mail.com", "9");
        students = new ArrayList<Student>();
        students.add(s1);
        students.add(s2);
        students.add(s3);

    }
    @Override
    public List<StudentDto> list() {
        return StudentMapper.mapFrom(students);
    }
    @Override
    public StudentDto byId(Long id) {
        return list().stream()
                .filter(student -> student.id().equals(id))
                .findFirst()
                .orElseThrow(()-> new UniversityException("Student not found"));
    }
    @Override
    public void save(StudentDto student) {
        list().add(student);
    }

    @Override
    public void delete(Long id) {
        list().remove(id);
    }
}
