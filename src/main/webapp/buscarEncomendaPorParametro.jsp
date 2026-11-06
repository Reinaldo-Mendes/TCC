<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Informatização de encomendas</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">

</head>
<body>
<%
    if(session.getAttribute("logado") == null){
        response.sendRedirect("login.jsp");
    }
%>
<div class="bs-component">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <a class="navbar-brand" href="landing.jsp">Informatização de encomendas</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01"
                    aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarColor02">
                <ul class="navbar-nav me-auto">

                    <li class="nav-item">
                        <a class="nav-link" href="cadastroEncomenda.jsp">Registrar encomenda</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="">Listar encomendas</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="">Pesquisar encomendas</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="">Cadastrar morador</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="">Listar moradores</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="">Pesquisar moradores</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="">Relatórios</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="">Pesquisar</a>
                    </li>

                </ul>
            </div>
        </div>
    </nav>
</div>

<div class="container">
    <form action ="buscarEncomendaPorParametro" method="post">

        <label class="form-label mt-4">Insira a descricao</label>
        <input type="text" class="form-control" name="descricao" aria-describedby="descricao"
               placeholder="Digite a descricao da encomenda">
        <small id="nomeFornecedor" class="form-text text-muted"></small>

        <button type="submit" class="btn btn-info" action="">Pesquisar</button>
        <div>
            <p>
                <%
                    String mensagem = (String) request.getAttribute("mensagem");
                    if(mensagem != null){
                        System.out.println(mensagem);
                    } else{
                        System.out.println("mensagem é nula");
                    }
                %>
            </p>
        </div>
    </form>
</div>
</body>
</html>