import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import model.Categoria;
import model.Produto;

public class TestaListagemDeCategoras {

	public static void main(String[] args) throws SQLException {
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()){
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<Categoria> listaDeCategorias = categoriaDAO.listar();
			
			listaDeCategorias.stream().forEach(ct -> {
				System.out.println(ct.getNome()); 
				try {
					for (Produto produto: new ProdutoDAO(connection).buscar(ct)) {
						System.out.println(produto.getNome());
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});

		}
	}

}
