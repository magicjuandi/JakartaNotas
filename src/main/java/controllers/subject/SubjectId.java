package controllers.subject;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.SubjectDto;
import mapping.dtos.TeacherDto;
import repository.impl.SubjectRepositoryimpl;
import repository.impl.TeacherRepositoryimpl;
import services.SubjectService;
import services.TeacherService;
import services.impl.SubjectServiceimpl;
import services.impl.TeacherServiceimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
@WebServlet(name= "subjectId", value="/subject-formId")
public class SubjectId extends HttpServlet {
    private SubjectRepositoryimpl subjectRepository;
    private SubjectService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        subjectRepository = new SubjectRepositoryimpl(conn);
        service = new SubjectServiceimpl(conn);
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        SubjectDto subjectDto = mapper.readValue(JsonStream, SubjectDto.class);
        Long id = subjectDto.id();
        service.byId(id);
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Subjects</h1>");
        out.println(service.byId(id));
        out.println("</body></html>");

    }
}
