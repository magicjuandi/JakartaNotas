package controllers.grade;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.models.Grade;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mapping.dtos.GradeDto;
import mapping.dtos.StudentDto;
import mapping.dtos.SubjectDto;
import mapping.mappers.GradeMapper;
import mapping.mappers.StudentMapper;
import mapping.mappers.SubjectMapper;
import repository.Repository;
import repository.impl.GradeRepositoryimpl;
import repository.impl.SubjectRepositoryimpl;
import services.GradeService;
import services.StudentService;
import services.SubjectService;
import services.impl.GradeServiceimpl;
import services.impl.StudentServiceimpl;
import services.impl.SubjectServiceimpl;
import services.impl.TeacherServiceimpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "gradeController", value = "/grade-form")
public class GradeController extends HttpServlet {
    @Inject
    @Named("gradeRep")
    private Repository<GradeDto> gradeRepository;
    @Inject

    private GradeService service;

    private String message;
    @Inject

    private SubjectService subjectService;
    @Inject

    private StudentService studentService;
    public void init(){
        message = "Hello World!";
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
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
        /*ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        GradeDto gradeDto = mapper.readValue(JsonStream, GradeDto.class);*/

        String studentName = req.getParameter("students");
        String subjectName = req.getParameter("subjects");
        String gradeS = req.getParameter("grade");
        Map<String, String> errorsmap = getErrors2(gradeS, studentName,subjectName);
        if (errorsmap.isEmpty()) {
            StudentDto studentDto = getStudentByName(studentName);
            SubjectDto subjectDto = getSubjectByName(subjectName);

            Double gradeD = Double.valueOf(gradeS);
            Grade grade = Grade.builder()
                    .student(StudentMapper.mapFrom(studentDto))
                    .subject(SubjectMapper.mapFrom(subjectDto))
                    .grade(gradeD).build();
            GradeDto gradeDto = GradeMapper.mapFrom(grade);

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
        }else {
            req.setAttribute("errorsmap", errorsmap);
            getServletContext().getRequestDispatcher("/grade.jsp").forward(req, resp);
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
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
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        GradeDto gradeDto = mapper.readValue(JsonStream, GradeDto.class);
        Long id = gradeDto.id();
        service.delete(id);

    }
    private SubjectDto getSubjectByName(String name) {
        List<SubjectDto> subjects = subjectService.list();
        return subjects.stream()
                .filter(e->e.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(null);
    }
    private StudentDto getStudentByName(String name) {
        List<StudentDto> students = studentService.list();
        return students.stream()
                .filter(e->e.name().equalsIgnoreCase(name))
                .findFirst()
                .orElseGet(null);
    }
    private Map<String, String> getErrors2(String grade, String student, String subject) {
        Map<String,String> errors = new HashMap<>();
        if(grade==null ||grade.isBlank()){
            errors.put("grade","La nota es requerida");
        }
        if(student==null || student.isBlank() || student == "-- selecionar --"){
            errors.put("student","El estudiante es requerido");
        }
        if(subject==null || subject.isBlank() || subject == "-- selecionar --"){
            errors.put("subject","La materia es requerida");
        }
        return errors;
    }
}
