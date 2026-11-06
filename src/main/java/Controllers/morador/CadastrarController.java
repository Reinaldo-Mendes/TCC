package Controllers.morador;

import Model.Morador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CadastrarController", value = "/CadastrarController")
public class CadastrarController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        int idApartamento;
        String nome, numeroTelefone, mensagem;
        Morador morador;

        if(request.getParameter("nomeMorador") != null && !request.getParameter("nomeMorador").isEmpty()
            && request.getParameter("numeroTelefone") != null && !request.getParameter("numeroTelefone").isEmpty()
            && request.getParameter("numeroApartamento") != null && !request.getParameter("numeroApartamento").isEmpty()){
            nome = request.getParameter("nomeMorador");
            numeroTelefone = request.getParameter("numeroTelefone");
            idApartamento = Integer.parseInt(request.getParameter("numeroApartamento"));

            morador = new Morador(nome, numeroTelefone,idApartamento);
            morador.cadastrar();
            mensagem = "Morador registrado com sucesso";
        }
        else{
            mensagem = "Preencha todos os campos!";
        }
        System.out.print(mensagem);
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("registrarMoradores.jsp");
        dispatcher.forward(request, response);
    }
}
