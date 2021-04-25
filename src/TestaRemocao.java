import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestaRemocao {

	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory createConnection = new ConnectionFactory();
		try(Connection connection = createConnection.recuperarConexao()){
			
			String sql = "DELETE FROM PRODUTO WHERE ID > ?";
			PreparedStatement stm = connection.prepareStatement(sql);
			
			stm.setInt(1, 2);
			stm.execute();
			
			
			
			Integer linhas_modificadas = stm.getUpdateCount();
			System.out.println("Quantidade de linhas modificadas: "+linhas_modificadas+"\n\n");
			
		}

	}

}
