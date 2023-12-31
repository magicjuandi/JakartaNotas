package controllers.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.sun.jdi.LongValue;
import domain.models.Student;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "studentController", value = "/student-form")
public class StudentController extends HttpServlet {
    @Inject
    @Named("studentRep")
    private Repository<StudentDto> studentRepository;
    @Inject
    private StudentService service;
    private String message;

    public void init() {
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
        Connection conn = (Connection) req.getAttribute("conn");
        /*ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        StudentDto student = mapper.readValue(JsonStream, StudentDto.class);*/

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String semester = req.getParameter("semester");
        Student student = Student.builder()
                .name(name)
                .email(email)
                .semester(semester).build();
        StudentDto studentDto = StudentMapper.mapFrom(student);


        Map<String,String> errorsmap= getErrors2(name,semester,email);
        if(errorsmap.isEmpty()){
            service.save(studentDto);
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
                out.println("            <li>Name: " + student.getName() + "</li>");
                out.println("            <li>Email: " + student.getEmail() + "</li>");
                out.println("            <li>Semester: " + student.getSemester() + "</li>");
                out.println("        </ul>");
                out.println("    </body>");
                out.println("</html>");
            }
        }else {
            req.setAttribute("errorsmap", errorsmap);
            getServletContext().getRequestDispatcher("/student.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        ServletInputStream JsonStream = req.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        StudentDto student = mapper.readValue(JsonStream, StudentDto.class);
            /* Long id = Long.valueOf(req.getParameter("id"));
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String semester = req.getParameter("semester");


               Student student = Student.builder()
                        .id(id)
                        .name(name)
                        .email(email)
                        .semester(semester)
                        .build();
                StudentDto studentDto = StudentMapper.mapFrom(student)<*/;
        service.save(student);
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
            out.println("            <li>Id: " + student.id() + "</li>");
            out.println("            <li>Name: " + student.name() + "</li>");
            out.println("            <li>Email: " + student.email() + "</li>");
            out.println("            <li>Semester: " + student.semester() + "</li>");
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

        StudentDto studentDto = mapper.readValue(JsonStream, StudentDto.class);
        Long id = studentDto.id();

        service.delete(id);
    }
    private Map<String,String> getErrors2(String name, String semester, String
            email) {
        Map<String,String> errors = new HashMap<>();
        if(name==null ||name.isBlank()){
            errors.put("name","El nombre es requerido");
        }
        if(email==null ||email.isBlank()){
            errors.put("email","El email es requerido");
        }
        if(semester==null ||semester.isBlank()){
            errors.put("semester","El semester es requerido");
        }
        return errors;
    }

    public void destroy() {
    }
}