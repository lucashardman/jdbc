package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Produto;

public class ProdutoDAO {

	private Connection connection;
	
	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void salvarProduto(Produto produto) throws SQLException {
	
			this.connection.setAutoCommit(false);
			
			
			try (PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
				adicionaVariavel(produto.getNome(), produto.getDescricao(), stm);
					
				connection.commit();
					
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ROLLBACK");
				connection.rollback();
			}
		
	}
	public List<Produto> listar() throws SQLException{
		List<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";
		try (PreparedStatement stm = connection.prepareStatement(sql)){
			stm.execute();
			try(ResultSet rst = stm.getResultSet()){
				while(rst.next()) {
					Produto p = new Produto(rst.getString(2), rst.getString(3),rst.getInt(1));
					produtos.add(p);
				}
				
			}
			connection.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ROLLBACK");
			connection.rollback();
		}
		return produtos;
	}
	public List<Produto> buscar(Categoria ct) throws SQLException{
		List<Produto> produtos = new ArrayList<Produto>();
		
		String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO WHERE CATEGORIA_ID = ?";
		try (PreparedStatement stm = connection.prepareStatement(sql)){
			stm.setInt(1, ct.getId());
			stm.execute();
			
			try(ResultSet rst = stm.getResultSet()){
				while(rst.next()) {
					Produto p = new Produto(rst.getString(2), rst.getString(3),rst.getInt(1));
					produtos.add(p);
				}
				
			}
		}
		return produtos;
	}
	
	private static void adicionaVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		
		stm.setString(1, nome);
		stm.setString(2, descricao);
		stm.execute();
		
		ResultSet rst = stm.getGeneratedKeys();
		while(rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println(id+": id criado."+"\n\n");
		}
	}
}
