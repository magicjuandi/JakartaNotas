package controllers.teacher;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.TeacherDto;
import repository.Repository;
import repository.impl.TeacherRepositoryimpl;
import services.TeacherService;
import services.impl.TeacherServiceimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
@WebServlet(name= "teacherId", value="/teacher-formId")
public class TeacherId extends HttpServlet {
    @Inject
    @Named("teacherRep")
    private Repository<TeacherDto> teacherRepository;
    @Inject
    private TeacherService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        TeacherDto teacherDto = mapper.readValue(JsonStream, TeacherDto.class);
        Long id = teacherDto.id();
        service.byId(id);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Teachers</h1>");
        out.println(service.byId(id));
        out.println("</body></html>");

    }
}
