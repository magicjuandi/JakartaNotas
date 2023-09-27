package controllers.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.StudentDto;
import mapping.dtos.TeacherDto;
import mapping.mappers.TeacherMapper;
import reposistories.impl.TeacherRepositoryLogicimpl;
import repository.impl.StudentRepositoryimpl;
import repository.impl.TeacherRepositoryimpl;
import services.TeacherService;
import services.impl.StudentServiceimpl;
import services.impl.TeacherServiceimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "teacherController", value = "/teacher-form")
public class TeacherController extends HttpServlet {
    private TeacherRepositoryimpl teacherRepository;
    private TeacherService service;
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        teacherRepository = new TeacherRepositoryimpl(conn);
        service = new TeacherServiceimpl(conn);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Teachers</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        teacherRepository = new TeacherRepositoryimpl(conn);
        service = new TeacherServiceimpl(conn);
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        TeacherDto teacher = mapper.readValue(JsonStream, TeacherDto.class);
        /*String name = req.getParameter("name");
        String email = req.getParameter("email");
        Teacher teacher = Teacher.builder()
                        .name(name)
                        .email(email).build();
        TeacherDto teacherDto = TeacherMapper.mapFrom(teacher);*/
        service.save(teacher);
        System.out.println(service.list());

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Resultado form</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Resultado form!</h1>");

            out.println("        <ul>");
            out.println("            <li>Name: " + teacher.name() + "</li>");
            out.println("            <li>Email: " + teacher.email() + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        teacherRepository = new TeacherRepositoryimpl(conn);
        service = new TeacherServiceimpl(conn);
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        TeacherDto teacher = mapper.readValue(JsonStream, TeacherDto.class);
        /*Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        Teacher teacher = Teacher.builder()
                        .id(id)
                        .name(name)
                        .email(email).build();
        TeacherDto teacherDto = TeacherMapper.mapFrom(teacher);*/
        service.save(teacher);
        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("    <head>");
            out.println("        <meta charset=\"UTF-8\">");
            out.println("        <title>Resultado form</title>");
            out.println("    </head>");
            out.println("    <body>");
            out.println("        <h1>Resultado form!</h1>");

            out.println("        <ul>");
            out.println("            <li>Id: " + teacher.id() + "</li>");
            out.println("            <li>Name: " + teacher.name() + "</li>");
            out.println("            <li>Email: " + teacher.email() + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        ServletInputStream JsonStream = req.getInputStream();

        ObjectMapper mapper = new ObjectMapper();
        service = new TeacherServiceimpl(conn);
        TeacherDto teacherDto = mapper.readValue(JsonStream, TeacherDto.class);
        Long id = teacherDto.id();
        service.delete(id);
    }

    public void destroy() {
    }
}
