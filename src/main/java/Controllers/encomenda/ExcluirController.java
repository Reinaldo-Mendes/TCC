package Controllers.encomenda;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ExcluirController", value = "/ExcluirController")
public class ExcluirController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String excluir = request.getParameter("botaoExcluir");
        String id = request.getParameter("teste");

        System.out.println("Excluir controller id é: "+id +"\t botaoExcluir: "+excluir);
    }
}
