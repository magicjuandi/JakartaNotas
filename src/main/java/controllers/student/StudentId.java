package controllers.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.StudentDto;
import mapping.dtos.SubjectDto;
import repository.impl.StudentRepositoryimpl;
import repository.impl.SubjectRepositoryimpl;
import services.StudentService;
import services.SubjectService;
import services.impl.StudentServiceimpl;
import services.impl.SubjectServiceimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
@WebServlet(name= "studentId", value="/student-formId")
public class StudentId extends HttpServlet {
    private StudentRepositoryimpl studentRepository;
    private StudentService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        studentRepository = new StudentRepositoryimpl(conn);
        service = new StudentServiceimpl(conn);
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        StudentDto studentDto = mapper.readValue(JsonStream, StudentDto.class);
        Long id = studentDto.id();
        service.byId(id);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.byId(id));
        out.println("</body></html>");

    }
}
