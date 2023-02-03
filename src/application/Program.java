package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
					+ "ID = ?");
			// st.setInt(1,5); Sem referencia na tabela vendedor
			st.setInt(1,2);  // Com referência na tabela vendedor
			int rowsAffected = st.executeUpdate();
			
			System.out.println("Feito! Total de linhas afetadas = "+ rowsAffected);
		}
		catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}
}
