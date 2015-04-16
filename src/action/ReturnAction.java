package action;

import model.CheckedOut;
import model.Book;
import model.Borrowers;
import java.sql.*;

import com.opensymphony.xwork2.Action;

public class ReturnAction implements Action{

	private Book book = new Book();
	private CheckedOut checkout = new CheckedOut();
	private Borrowers borrower = new Borrowers();
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/Library";
	static final String user = "root";
	static final String pass = "password";
	Connection conn = null;
	Statement stmt = null;
	
	@Override
	public String execute() throws Exception {
		// check book/cardno in database
		int num = 0;
		int isbn = 0;
		if(getISBN(isbn) && getCardNo(num)){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL, user, pass);
				stmt = conn.createStatement();
				String sql;
				sql = "Update books Set availability = availability +1 where isbn =" + "'" + book.getIsbn() + "'";
				stmt.executeQuery(sql);
				sql = "Delete from checkedout where isbn = '" + book.getIsbn() + "' and cardno = '" + borrower.getCardNumber() + "'";
				stmt.execute(sql);
			}catch(SQLException se){
			}catch(Exception e){
			}finally{
				      //finally block used to close resources
				      try{
				         if(stmt!=null)
				            conn.close();
				      }catch(SQLException se){
				      }// do nothing
				      try{
				         if(conn!=null)
				            conn.close();
				      }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
			}//end try
			
			return "SUCCESS";
		}
		else{
			return "FAILURE";
		}
	}
	
	public boolean getCardNo(int num){
		String name = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, user, pass);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT name FROM borrowers WHERE cardno = '" + num + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				name =rs.getString("name");
			}
			rs.close();
		}catch(SQLException se){
		}catch(Exception e){
		}finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
		}//end try
		if(name != null){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean getISBN(int isbn){
		String title = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, user, pass);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT title FROM books WHERE isbn = '" + isbn + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				title =rs.getString("name");
			}
			rs.close();
		}catch(SQLException se){
		}catch(Exception e){
		}finally{
			      //finally block used to close resources
			      try{
			         if(stmt!=null)
			            conn.close();
			      }catch(SQLException se){
			      }// do nothing
			      try{
			         if(conn!=null)
			            conn.close();
			      }catch(SQLException se){
			         se.printStackTrace();
			      }//end finally try
		}//end try
		if(title != null){
			return true;
		}
		else{
			return false;
		}
	}
	
}
