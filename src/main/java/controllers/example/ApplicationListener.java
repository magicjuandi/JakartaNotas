package controllers.example;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionListener;
@WebListener
public class ApplicationListener implements ServletContextListener, ServletRequestListener, HttpSessionListener {
    private ServletContext servletContext;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().log("inicia la aplicación!");
        servletContext = sce.getServletContext();
        System.out.println("Hola desde context inicializer");
        servletContext.setAttribute("mensaje", "Hola a todos desde la application!");
    }
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        servletContext.log("inicializando el request!");
        sre.getServletRequest().setAttribute("mensaje", "guardando algún valor para el request");
        System.out.println("Hola desde request inicializer");
    }
    /*
    Investigación:
    1. El getAttribute() el cual solo esta habilitado para la solicitud en el momento,y no logra ser
    compartido entre las diferentes solicitudes o sesiones. Este es útil para transportar información
    específica de una solicitud a otra dentro del mismo flujo de solicitud respuesta.
    Por otro lado el getServletContext().getAttribute el cual solo esta habilitado para toda la
    aplicación y se comparte entre todas las solicitudes y sesiones. Es útil para el almacenamiento
    datos globales e informacion, que deben estar disponibles.

    2. La diferencia entre ellos es que cada vez que se inicia el servidor se da un context por lo tanto,
    cada vez que se genera una solicitud, ya sea un cambio de servlet, o simplo refresh de una pagina,
    se genera un request, por lo tanto un ejemplo puede ser la vitancia de una sesion, ya sea que cada
    que entra al servidor se inicia un timer, el cual cuando llega 0 el tiempo se cierra la sesion, ya
    sea por seguridad de la app, o peticion del usuario.
    */

}
