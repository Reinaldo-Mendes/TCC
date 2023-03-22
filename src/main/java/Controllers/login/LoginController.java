package Controllers.login;

import Model.Administrador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/LoginController")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String usuario,senha;

        System.out.println("Login controller");

        if (request.getParameter("campoUsuario") != null && !request.getParameter("campoUsuario").isEmpty() &&
                request.getParameter("campoSenha") != null && !request.getParameter("campoSenha").isEmpty()) {
            usuario = request.getParameter("campoUsuario");
            senha = request.getParameter("campoSenha");
            Administrador admin = new Administrador();
            admin.setNome(usuario);
            admin.setSenha(senha);

            Administrador validado;
            validado = admin.validarLogin(admin.getNome(), admin.getSenha());
            if(admin.validarLogin(admin.getNome(), admin.getSenha()) != null){
                System.out.println("Usuario "+admin.getNome()+" validado");
                HttpSession session = request.getSession();
                session.setAttribute("nome", admin.getNome());
                session.setAttribute("logado", "true");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listarEncomendas.jsp");
                dispatcher.forward(request,response);
            } else{
                System.out.println("Usuario "+admin.getNome()+" não encontrado");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
                dispatcher.forward(request,response);

            }
        }
    }
}
