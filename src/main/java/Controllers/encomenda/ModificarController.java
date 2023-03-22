package Controllers.encomenda;

import DAO.EncomendaDAO;
import Model.Encomenda;
import Model.Morador;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.spec.ECField;

import static java.lang.System.out;

@WebServlet(name = "ModificarController", value = "/ModificarController")
public class ModificarController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        out.println("doPost modificar encomenda controller");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String excluir = request.getParameter("botaoExcluir");
        String editar = request.getParameter("botaoEditar");
        String id = request.getParameter("idEncomenda");
        String notificar = request.getParameter("botaoNotificar");

        if (excluir != null && id != null) {
            out.println("Excluindo o id " + id);
            new Encomenda().excluirEncomenda(Integer.valueOf(id));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listarEncomendas.jsp");
            dispatcher.forward(request, response);
        } else if (editar != null && id != null) {
            Encomenda encomenda = new Encomenda().buscarEncomendaPorId(Integer.valueOf(id));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/editarEncomendas.jsp");
            request.setAttribute("encomendaParaEditar", encomenda);
            out.println("Apartamento da encomenda: "+encomenda.getNumeroApartamento());
            dispatcher.forward(request, response);
        } else if(notificar != null && id != null){
            out.println("Vamos notificar!");
            Morador morador = new Morador().buscarMoradorPorIdDaEncomenda(Integer.valueOf(id));
            Encomenda encomenda = new Encomenda().buscarEncomendaPorId(Integer.valueOf(id));
            String telefone = morador.getTelefone();
            //String mensagem = "Caro senhor (a) "+morador.getNome()+",estamos enviando esta mensagem para avisar que a encomenda do fornecedor "+encomenda.getNomeFornecedor()+"e de descriçao "+encomenda.getDescricao()+
                   // "que chegou na data "+encomenda.getDataRecebimento() + " "+encomenda.getHorarioRecebimento()+"se encontra na portaria para retirada!";

            StringBuilder sb = new StringBuilder();
            sb.append("Olá ");
            sb.append(morador.getNome());
            sb.append(",estamos enviando esta mensagem para avisar que a encomenda do fornecedor ");
            sb.append(encomenda.getNomeFornecedor());
            String mensagem = sb.toString();
            mensagem.replaceAll("\\n", " ");
            /*String[] command = { "C:\\Users\\reina\\AppData\\Local\\Programs\\Python\\Python311\\python.exe",
                    "C:\\Users\\reina\\Documents\\Reinaldo\\Faculdade\\TCC\\Código\\Projeto JSP/ModuloPython/whatsapp.py", "--phone_no", telefone, "--message", mensagem };*/

            String[] command = { "C:\\Users\\reina\\AppData\\Local\\Programs\\Python\\Python311\\python.exe",
                    "C:\\Users\\reina\\Documents\\Reinaldo\\Faculdade\\TCC\\Código\\Projeto JSP/ModuloPython/whatsapp.py", telefone, mensagem };

            out.println("Telefone: "+morador.getTelefone()+"\nMensagem: "+mensagem);
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listarEncomendas.jsp");
            dispatcher.forward(request, response);

        }

        String botaoSalvar = request.getParameter("botaoSalvar");
        if (botaoSalvar != null) {
            out.print("Salvando as ediçoes");
            if (request.getParameter("idEncomenda") != null && !request.getParameter("idEncomenda").isEmpty() &&
                    request.getParameter("descricao") != null && !request.getParameter("descricao").isEmpty() &&
                    request.getParameter("nomeFornecedor") != null && !request.getParameter("nomeFornecedor").isEmpty() &&
                    request.getParameter("status") != null && !request.getParameter("status").isEmpty() &&
                    request.getParameter("dataRecebimento") != null && !request.getParameter("dataRecebimento").isEmpty() &&
                    request.getParameter("horarioRecebimento") != null && !request.getParameter("horarioRecebimento").isEmpty() &&
                    request.getParameter("dataRetirada") != null && !request.getParameter("dataRetirada").isEmpty() &&
                    request.getParameter("horarioRetirada") != null && !request.getParameter("horarioRetirada").isEmpty() &&
                    request.getParameter("apartamentos") != null && !request.getParameter("apartamentos").isEmpty()) {
                Encomenda encomenda = new Encomenda();
                encomenda.setIdEncomenda(Integer.valueOf(request.getParameter("idEncomenda")));
                encomenda.setDescricao(request.getParameter("descricao"));
                encomenda.setNomeFornecedor(request.getParameter("nomeFornecedor"));
                encomenda.setStatus(request.getParameter("status"));
                encomenda.setDataRecebimento(request.getParameter("dataRecebimento"));
                encomenda.setHorarioRecebimento(request.getParameter("horarioRecebimento"));
                encomenda.setDataRetirada(request.getParameter("dataRetirada"));
                encomenda.setHorarioRetirada(request.getParameter("horarioRetirada"));
                encomenda.setIdApartamento(Integer.valueOf(request.getParameter("apartamentos")));
                new EncomendaDAO().alterarEncomenda(encomenda);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/listarEncomendas.jsp");
                dispatcher.forward(request, response);
            }


        } else {
            out.println("Botao salvar é nulo");
        }

    }
}
