package Controllers.encomenda;

import Model.DataUtil;
import Model.Encomenda;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EncomendaController", value = "/EncomendaController")
public class CadastrarController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String nomeFornecedor, dataRecebimento, horarioRecebimento, descricao, status = "Aguardando retirada",  mensagem;
        int idApartamento;
        Encomenda encomenda;
        if(request.getParameter("nomeFornecedor") != null && !request.getParameter("nomeFornecedor").isEmpty()
                && request.getParameter("descricao") != null && !request.getParameter("descricao").isEmpty()
                && request.getParameter("numeroApartamento") != null && !request.getParameter("numeroApartamento").isEmpty()) {

            nomeFornecedor = request.getParameter("nomeFornecedor");
            descricao = request.getParameter("descricao");

            DataUtil datautil = new DataUtil();
            dataRecebimento = datautil.getDataAtual();
            horarioRecebimento = datautil.getHorarioAtual();



            idApartamento = Integer.parseInt(request.getParameter("numeroApartamento"));
            encomenda = new Encomenda(nomeFornecedor, status, dataRecebimento, horarioRecebimento,
                    descricao, idApartamento);
            encomenda.setDataRetirada("A retirar");
            encomenda.setHorarioRetirada("A retirar");
            mensagem = "Encomenda registrada com sucesso";
            System.out.println(encomenda.getNomeFornecedor()+"\t"+encomenda.getDescricao()+"\t"+
                    encomenda.getDataRecebimento()+"\t"+encomenda.getHorarioRecebimento()+"\t"+
                    encomenda.getIdApartamento()+"\t"+encomenda.getStatus()+"\t"+encomenda.getDataRetirada()+
                    "\t"+encomenda.getHorarioRetirada());
            encomenda.cadastrar();

        }
        else{
            mensagem = "Preencha todos os campos!";
        }
        System.out.print(mensagem);
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("registrarEncomendas.jsp");
        dispatcher.forward(request, response);
    }

}
