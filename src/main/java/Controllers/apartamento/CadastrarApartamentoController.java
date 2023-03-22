package Controllers.apartamento;

import Model.Apartamento;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CadastrarApartamentoController", value = "/CadastrarApartamentoController")
public class CadastrarApartamentoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String mensagem;


        if (request.getParameter("numeroApartamento") != null && !request.getParameter("numeroApartamento").isEmpty()) {
            Apartamento apartamento;
            String numeroApartamento = request.getParameter("numeroApartamento");
            apartamento = new Apartamento(numeroApartamento);
            apartamento.cadastrar();
            mensagem = "Apartamento cadastrado";
        } else {
            mensagem = "Preencha todos os campos!";
        }
        System.out.print(mensagem);
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("registrarApartamentos.jsp");
        dispatcher.forward(request, response);
    }
}
