package controllers.subject;

import domain.models.Subject;
import domain.models.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reposistories.impl.SubjectRepositoryLogicimpl;
import repository.impl.StudentRepositoryimpl;
import repository.impl.SubjectRepositoryimpl;
import services.SubjectService;
import services.impl.StudentServiceimpl;
import services.impl.SubjectServiceimpl;

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

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection conn = (Connection) req.getAttribute("conn");
        subjectRepository = new SubjectRepositoryimpl(conn);
        service = new SubjectServiceimpl(conn);

        String name = req.getParameter("name");
        Subject subject = new Subject(4L, name, new Teacher(5L,"Aja", "Aja@mail.com"));
        service.save(subject);
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
            out.println("        </ul>");
            out.println("    </body>");
            out.println("</html>");
        }
    }*/

    public void destroy() {
    }
}
