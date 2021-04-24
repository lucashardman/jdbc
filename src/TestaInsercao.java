import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercao {
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory createConnection = new ConnectionFactory();
		try (Connection connection = createConnection.recuperarConexao()){
			
			connection.setAutoCommit(false);
			
			
			try (PreparedStatement stm = connection.prepareStatement("INSERT INTO PRODUTO (nome, descricao) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)){
				adicionaVariavel("Apple TV 4k", "uma descricao legal", stm);
				adicionaVariavel("Radio", "outra descricao legal", stm);
					
				connection.commit();
					
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("ROLLBACK");
				connection.rollback();
			}
		}
		
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
