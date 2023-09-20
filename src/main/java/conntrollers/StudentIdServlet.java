package conntrollers;

import domain.models.Student;
import exceptions.UniversityException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.mappers.StudentMapper;
import reposistories.impl.StudentRepositoryLogicImpl;
import repository.impl.StudentRepositoryimpl;
import services.StudentService;
import services.impl.StudentServiceimpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/studentId")
public class StudentIdServlet extends HttpServlet {
    private StudentRepositoryimpl repository;
    private StudentService service;
    private StudentMapper mapper;
    public StudentIdServlet(){
        repository = new StudentRepositoryimpl();
        service = new StudentServiceimpl(repository,mapper);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {

        Long id = Long.valueOf(req.getParameter("id"));

        try{
            Student student= service.byId(id);
            try (PrintWriter out = resp.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println(" <head>");
                out.println(" <meta charset=\"UTF-8\">");
                out.println(" <title>Login correcto</title>");
                out.println(" </head>");
                out.println(" <body>");
                out.println(" <h1>Login correcto!</h1>");
                out.println(" <h3>Hola "+ student.getName() + " has iniciado sesión con éxito!</h3>");
                out.println(" </body>");
                out.println("</html>");
            }

        }catch (UniversityException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Lo sentimos no esta autorizado para ingresar a esta página!");
        }

    }
}
