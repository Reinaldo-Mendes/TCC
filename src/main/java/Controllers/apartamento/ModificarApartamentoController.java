package Controllers.apartamento;

import DAO.ApartamentoDAO;
import DAO.MoradorDAO;
import Model.Apartamento;
import Model.Morador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ModificarApartamentoController", value = "/ModificarApartamentoController")
public class ModificarApartamentoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("doPost modificar apartamento controller");

        String excluir = request.getParameter("botaoExcluir");
        String editar = request.getParameter("botaoEditar");
        String id = request.getParameter("idApartamento");

        if (excluir != null && id != null) {
            System.out.println("Excluindo o apartamento de id " + id);
            new Apartamento().excluirApartamento(Integer.valueOf(id));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listarApartamentos.jsp");
            dispatcher.forward(request, response);
        } else if (editar != null && id != null) {
            Morador morador = new Morador().buscarMoradorPorIdDoApartamento(Integer.valueOf(id));
            Apartamento apartamento = new Apartamento().buscarApartamentoPorId(Integer.valueOf(id));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/editarApartamentos.jsp");
            request.setAttribute("apartamentoParaEditar", apartamento);
            dispatcher.forward(request, response);
        }

        String botaoSalvar = request.getParameter("botaoSalvar");
        if (botaoSalvar != null) {
            if (request.getParameter("idApartamento") != null && !request.getParameter("idApartamento").isEmpty() &&
                    request.getParameter("numeroApartamento") != null && !request.getParameter("numeroApartamento").isEmpty()) {
                Apartamento apartamento = new Apartamento();
                apartamento.setId(Integer.valueOf(request.getParameter("idApartamento")));
                apartamento.setNumero(request.getParameter("numeroApartamento"));
                new ApartamentoDAO().alterarApartamento(apartamento);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listarApartamentos.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            System.out.println("Botao salvar é nulo");
        }
    }
}
