package Controllers.encomenda;

import Model.Encomenda;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ListarController", value = "/ListarController")
public class ListarController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String descricao = request.getParameter("descricao");
        if (descricao != null && !descricao.isEmpty()) {
            ArrayList<Encomenda> encomendas = new Encomenda().buscarEncomendasPorDescricao(descricao);
            request.setAttribute("encomendas", encomendas);
        } else {
            System.out.println("O usuário precisa digitar a descrição no campo");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listarEncomendas.jsp");
        dispatcher.forward(request, response);
    }
}
