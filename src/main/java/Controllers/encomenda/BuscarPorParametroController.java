package Controllers.encomenda;

import Model.Encomenda;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "BuscarPorParametroController", value = "/BuscarPorParametroController")
public class BuscarPorParametroController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String descricao = request.getParameter("descricao");

        ArrayList<Encomenda> encomendas = new Encomenda().buscarEncomendasPorDescricao(descricao);
        for(Encomenda encomenda: encomendas){
            System.out.println("Descriçao: "+encomenda.getDescricao());
        }
    }
}
