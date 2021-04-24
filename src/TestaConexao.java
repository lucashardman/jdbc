import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaConexao {
	public static void main( String[] args) throws SQLException {
		ConnectionFactory createConnection = new ConnectionFactory();
		
		try(Connection connection = createConnection.recuperarConexao()){
		
			String sql = "SELECT ID, NOME, DESCRICAO FROM PRODUTO";
			
			PreparedStatement stm = connection.prepareStatement(sql);
			stm.execute();
			ResultSet rst = stm.getResultSet();
			
			while(rst.next()) {
				Integer id = rst.getInt("ID");
				String nome = rst.getString("NOME");
				String descricao = rst.getString("DESCRICAO");
				System.out.println(id+": "+nome+"\nDescricao: "+descricao+"\n\n");
			}
		
		}
	}
}
