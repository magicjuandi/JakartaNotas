package conntrollers;

import domain.models.Student;
import domain.models.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reposistories.impl.StudentRepositoryLogicImpl;
import reposistories.impl.TeacherRepositoryLogicimpl;
import services.StudentService;
import services.TeacherService;
import services.impl.StudentServiceimpl;
import services.impl.TeacherServiceimpl;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "teacherController", value = "/teacher-form")
public class TeacherController extends HttpServlet {
    private TeacherRepositoryLogicimpl teacherRepository;
    private TeacherService service;

    public TeacherController() {
        teacherRepository = new TeacherRepositoryLogicimpl();
        service = new TeacherServiceimpl(teacherRepository);
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
        out.println("<h1>Teachers</h1>");
        out.println(service.listar());
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        Teacher teacher = new Teacher(4L, name, email);
        service.guardar(teacher);
        System.out.println(service.listar());

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
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }

    public void destroy() {
    }
}
