package controllers.student;

import domain.models.Student;
import exceptions.UniversityException;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.StudentDto;
import mapping.mappers.StudentMapper;
import repository.Repository;
import repository.impl.StudentRepositoryimpl;
import services.StudentService;
import services.impl.StudentServiceimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet("/studentId")
public class StudentIdServlet extends HttpServlet {
    @Inject
    @Named("studentRep")
    private Repository<StudentDto> repository;
    @Inject

    private StudentService service;
    private StudentMapper mapper;
    private Connection conn;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Long id = Long.valueOf(req.getParameter("id"));

        try{
            StudentDto student= service.byId(id);
            Student studentA = StudentMapper.mapFrom(student);
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Login correcto</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Login correcto!</h1>");
                out.println(" <h3>Hola "+  studentA.getName() + " has iniciado sesión con éxito!</h3>");
                out.println(" </body>");
                out.println("</html>");
            }

        }catch (UniversityException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }

    }
}
