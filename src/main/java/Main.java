import domain.models.Teacher;
import repository.impl.StudentRepositoryimpl;
import repository.impl.TeacherRepositoryimpl;
import singledomain.ConexionDB;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        StudentRepositoryimpl stRep = new StudentRepositoryimpl();
        System.out.println(stRep.byId(1L));
    }
}
