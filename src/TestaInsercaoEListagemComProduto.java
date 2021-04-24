import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.ProdutoDAO;
import model.Produto;

public class TestaInsercaoEListagemComProduto {
	public static void main(String[] args) throws SQLException {
		
		Produto comida = new Produto("arroz", "branco e doce");
		ConnectionFactory createConnection = new ConnectionFactory();
		try (Connection connection = createConnection.recuperarConexao()){
			ProdutoDAO produtoDao = new ProdutoDAO(connection);
			produtoDao.salvarProduto(comida);
			List<Produto> listaDeProduto = produtoDao.listar();
			listaDeProduto.stream().forEach(lp->System.out.println(lp.getNome()));
		}

		
	}

}
