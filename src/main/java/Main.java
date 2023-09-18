import domain.models.Teacher;
import repository.impl.TeacherRepositoryimpl;
import singledomain.ConexionDB;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try (Connection conn = ConexionDB.getInstance()) {
            TeacherRepositoryimpl teacherRepo = new TeacherRepositoryimpl();
            System.out.println("Add teacher");
            System.out.println("Name");
            String nameS = scan.next();
            System.out.println("Email");
            String emailS = scan.next();

            Teacher teacher = Teacher.builder()
                    .name(nameS)
                    .email(emailS).build();
            teacherRepo.save(teacher);
        } catch (Exception e) {

        }
    }
}
