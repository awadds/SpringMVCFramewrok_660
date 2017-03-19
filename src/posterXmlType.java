import java.sql.* ;  // for standard JDBC programs
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ets.gti660.dao.*;

public class posterXmlType {
	
	
	// TODO Auto-generated constructor stub
		// JDBC driver name and database URL
		//static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
		static final String DB_URL = "jdbc:oracle:thin:@big-data-2.logti.etsmtl.ca:1521/GTI660";
		
		// Database credentials
		static final String USER = "Equipe5";
		static final String PASS = "sM75iSUK";
		
		Connection conn = null;
		Statement stmt = null ;
		PreparedStatement pstmt = null;
		
		public posterXmlType() {
			System.out.println("test");
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = conn.createStatement();
				System.out.println("Connected database successfully...");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
//		private DataSource dataSource;
//		private JdbcTemplate jdbcTemplateObject;
//		
//		@Override
//		public void setDataSource(DataSource dataSource) {
//			this.dataSource = dataSource;
//			this.jdbcTemplateObject = new JdbcTemplate(dataSource);
//		}
		
	 public static void main(String[] args) {
	        System.out.println("Hello World!"); // Display the string.
	        posterXmlType test = new posterXmlType();
	        
	 }

	
}
