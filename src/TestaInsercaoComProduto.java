import java.sql.Connection;
import java.sql.SQLException;

import dao.ProdutoDAO;
import model.Produto;

public class TestaInsercaoComProduto {
	public static void main(String[] args) throws SQLException {
		
		Produto comida = new Produto("arroz", "branco e doce");
		ConnectionFactory createConnection = new ConnectionFactory();
		try (Connection connection = createConnection.recuperarConexao()){
			new ProdutoDAO(connection).salvarProduto(comida);
		}

		
	}

}
