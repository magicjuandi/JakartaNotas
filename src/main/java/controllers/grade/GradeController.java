package controllers.grade;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.Grade;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.GradeDto;
import mapping.dtos.SubjectDto;
import mapping.mappers.GradeMapper;
import mapping.mappers.StudentMapper;
import mapping.mappers.SubjectMapper;
import repository.impl.GradeRepositoryimpl;
import repository.impl.SubjectRepositoryimpl;
import services.GradeService;
import services.impl.GradeServiceimpl;
import services.impl.StudentServiceimpl;
import services.impl.SubjectServiceimpl;
import services.impl.TeacherServiceimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "gradeController", value = "/grade-form")
public class GradeController extends HttpServlet {
    private GradeRepositoryimpl gradeRepository;
    private GradeService service;
    private String message;
    public void init(){
        message = "Hello World!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Connection conn = (Connection) request.getAttribute("conn");
        gradeRepository = new GradeRepositoryimpl(conn);
        service = new GradeServiceimpl(conn);
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
        gradeRepository = new GradeRepositoryimpl(conn);
        service = new GradeServiceimpl(conn);
        StudentServiceimpl studentService = new StudentServiceimpl(conn);
        SubjectServiceimpl subjectService = new SubjectServiceimpl(conn);
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        GradeDto gradeDto = mapper.readValue(JsonStream, GradeDto.class);


        /*Long id_student = Long.valueOf(req.getParameter("id_student"));
        Long id_subject = Long.valueOf(req.getParameter("id_subject"));
        Double gradeD = Double.valueOf(req.getParameter("grade"));
        Grade grade = Grade.builder()
                .student(StudentMapper.mapFrom(studentService.byId(id_student)))
                .subject(SubjectMapper.mapFrom(subjectService.byId(id_subject)))
                .grade(gradeD).build();
        GradeDto gradeDto = GradeMapper.mapFrom(grade);*/
        service.save(gradeDto);
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
            out.println("            <li>Name: " + gradeDto.student().getId() + "</li>");
            out.println("            <li>Name: " + gradeDto.student().getName() + "</li>");
            out.println("            <li>Name: " + gradeDto.student().getEmail() + "</li>");
            out.println("            <li>Name: " + gradeDto.student().getSemester() + "</li>");
            out.println("            <li>Name: " + gradeDto.subject().getId() + "</li>");
            out.println("            <li>Name: " + gradeDto.subject().getName() + "</li>");
            out.println("            <li>Name: " + gradeDto.subject().getTeacher().getId() + "</li>");
            out.println("            <li>Name: " + gradeDto.subject().getTeacher().getName() + "</li>");
            out.println("            <li>Name: " + gradeDto.subject().getTeacher().getEmail() + "</li>");
            out.println("            <li>Name: " + gradeDto.grade() + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        gradeRepository = new GradeRepositoryimpl(conn);
        service = new GradeServiceimpl(conn);
        StudentServiceimpl studentService = new StudentServiceimpl(conn);
        SubjectServiceimpl subjectService = new SubjectServiceimpl(conn);
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        GradeDto gradeDto = mapper.readValue(JsonStream, GradeDto.class);


        /*Long id = Long.valueOf(req.getParameter("id"));
        Long id_student = Long.valueOf(req.getParameter("id_student"));
        Long id_subject = Long.valueOf(req.getParameter("id_subject"));
        Double gradeD = Double.valueOf(req.getParameter("grade"));
        Grade grade = Grade.builder()
                .id(id)
                .student(StudentMapper.mapFrom(studentService.byId(id_student)))
                .subject(SubjectMapper.mapFrom(subjectService.byId(id_subject)))
                .grade(gradeD).build();
        GradeDto gradeDto = GradeMapper.mapFrom(grade);*/
        service.save(gradeDto);
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
            out.println("            <li>Name: " + gradeDto.id() + "</li>");
            out.println("            <li>Name: " + gradeDto.student().getId() + "</li>");
            out.println("            <li>Name: " + gradeDto.student().getName() + "</li>");
            out.println("            <li>Name: " + gradeDto.student().getEmail() + "</li>");
            out.println("            <li>Name: " + gradeDto.student().getSemester() + "</li>");
            out.println("            <li>Name: " + gradeDto.subject().getId() + "</li>");
            out.println("            <li>Name: " + gradeDto.subject().getName() + "</li>");
            out.println("            <li>Name: " + gradeDto.subject().getTeacher().getId() + "</li>");
            out.println("            <li>Name: " + gradeDto.subject().getTeacher().getName() + "</li>");
            out.println("            <li>Name: " + gradeDto.subject().getTeacher().getEmail() + "</li>");
            out.println("            <li>Name: " + gradeDto.grade() + "</li>");
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
        service = new GradeServiceimpl(conn);
        ObjectMapper mapper = new ObjectMapper();
        GradeDto gradeDto = mapper.readValue(JsonStream, GradeDto.class);
        Long id = gradeDto.id();
        service.delete(id);

    }

}
