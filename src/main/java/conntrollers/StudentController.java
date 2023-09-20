package conntrollers;

import domain.models.Student;
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

@WebServlet(name = "studentController", value = "/student-form")
public class StudentController extends HttpServlet {

    private StudentRepositoryimpl studentRepository;
    private StudentMapper mapper;
    private StudentService service;

    public StudentController() {
        studentRepository = new StudentRepositoryimpl();
        service = new StudentServiceimpl(studentRepository, mapper);
    }

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

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String semester = req.getParameter("semester");
        Student student = new Student(4L, name, email, semester);
        service.save(student);
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
            out.println("            <li>Name: " + name + "</li>");
            out.println("            <li>Email: " + email + "</li>");
            out.println("            <li>Semester: " + semester + "</li>");
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    public void destroy() {
    }
}