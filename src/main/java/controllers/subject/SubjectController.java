package controllers.subject;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.Subject;
import domain.models.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.StudentDto;
import mapping.dtos.SubjectDto;
import mapping.mappers.SubjectMapper;
import mapping.mappers.TeacherMapper;
import reposistories.impl.SubjectRepositoryLogicimpl;
import repository.impl.StudentRepositoryimpl;
import repository.impl.SubjectRepositoryimpl;
import services.SubjectService;
import services.impl.StudentServiceimpl;
import services.impl.SubjectServiceimpl;
import services.impl.TeacherServiceimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "subjectController", value = "/subject-form")
public class SubjectController extends HttpServlet {
    private SubjectRepositoryimpl subjectRepository;
    private SubjectService service;
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        subjectRepository = new SubjectRepositoryimpl(conn);
        service = new SubjectServiceimpl(conn);
        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Students</h1>");
        out.println(service.list());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        subjectRepository = new SubjectRepositoryimpl(conn);
        service = new SubjectServiceimpl(conn);
        TeacherServiceimpl teacherService = new TeacherServiceimpl(conn);
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        SubjectDto subjectDto = mapper.readValue(JsonStream, SubjectDto.class);

        /*String name = req.getParameter("name");
        Long id = Long.valueOf(req.getParameter("id_teacher"));
        Subject subject = Subject.builder()
                        .name(name)
                        .teacher(TeacherMapper.mapFrom(teacherService.byId(id))).build();
        SubjectDto subjectDto = SubjectMapper.mapFrom(subject);*/
        service.save(subjectDto);
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
            out.println("            <li>Name: " + subjectDto.name() + "</li>");
            out.println("            <li>Teacher Id: " + subjectDto.teacher().getId() + "</li>");
            out.println("            <li>Teacher Name: " + subjectDto.teacher().getName() + "</li>");
            out.println("            <li>Teacher Email: " + subjectDto.teacher().getEmail() + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        subjectRepository = new SubjectRepositoryimpl(conn);
        service = new SubjectServiceimpl(conn);
        TeacherServiceimpl teacherService = new TeacherServiceimpl(conn);
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        SubjectDto subjectDto = mapper.readValue(JsonStream, SubjectDto.class);

        /* Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        Long id_teacher = Long.valueOf(req.getParameter("id_teacher"));
        Subject subject = Subject.builder()
                        .id(id)
                        .name(name)
                        .teacher(TeacherMapper.mapFrom(teacherService.byId(id_teacher))).build();
        SubjectDto subjectDto = SubjectMapper.mapFrom(subject);*/
        service.save(subjectDto);
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
            out.println("            <li>Id: " + subjectDto.id() + "</li>");
            out.println("            <li>Name: " + subjectDto.name() + "</li>");
            out.println("            <li>Teacher Id: " + subjectDto.teacher().getId() + "</li>");
            out.println("            <li>Teacher Name: " + subjectDto.teacher().getName() + "</li>");
            out.println("            <li>Teacher Email: " + subjectDto.teacher().getEmail() + "</li>");
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
        service = new SubjectServiceimpl(conn);
        ObjectMapper mapper = new ObjectMapper();
        SubjectDto subjectDto = mapper.readValue(JsonStream, SubjectDto.class);
        Long id = subjectDto.id();
        service.delete(id);

    }

    public void destroy() {
    }
}
