package Controllers.morador;


import DAO.MoradorDAO;
import Model.Morador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ModificarMoradorController", value = "/ModificarMoradorController")
public class ModificarMoradorController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        System.out.println("doPost modificar morador controller");

        String excluir = request.getParameter("botaoExcluir");
        String editar = request.getParameter("botaoEditar");
        String id = request.getParameter("idMorador");

        if (excluir != null && id != null) {
            System.out.println("Excluindo o morador de id " + id);
            new Morador().excluirMorador(Integer.valueOf(id));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listarMoradores.jsp");
            dispatcher.forward(request, response);
        } else if (editar != null && id != null) {
            Morador morador = new Morador().buscarMoradorPorIdDoApartamento(Integer.valueOf(id));
            System.out.println(morador.getIdMorador());
            System.out.println(morador.getNome());
            System.out.println(morador.getTelefone());
            System.out.println(morador.getNumeroApartamento());
            System.out.println(morador.getIdApartamento());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/editarMoradores.jsp");
            request.setAttribute("moradorParaEditar", morador);
            dispatcher.forward(request, response);
        }

        String botaoSalvar = request.getParameter("botaoSalvar");
        if (botaoSalvar != null) {
            if (request.getParameter("idMorador") != null && !request.getParameter("idMorador").isEmpty() &&
                    request.getParameter("nomeMorador") != null && !request.getParameter("nomeMorador").isEmpty() &&
                    request.getParameter("telefoneMorador") != null && !request.getParameter("telefoneMorador").isEmpty() &&
                    request.getParameter("apartamentos") != null && !request.getParameter("apartamentos").isEmpty()) {


                Morador morador = new Morador();
                morador.setIdMorador(Integer.valueOf(request.getParameter("idMorador")));
                morador.setNome(request.getParameter("nomeMorador"));
                morador.setTelefone(request.getParameter("telefoneMorador"));
                morador.setIdApartamento(Integer.valueOf(request.getParameter("apartamentos")));
                new MoradorDAO().alterarMorador(morador);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listarMoradores.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            System.out.println("Botao salvar é nulo");
        }
    }
}
