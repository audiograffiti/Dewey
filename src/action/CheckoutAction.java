package action;

import model.CheckedOut;
import model.Book;
import model.Borrowers;
import java.sql.*;

import com.opensymphony.xwork2.Action;

public class CheckoutAction implements Action {
	
	private CheckedOut checkout = new CheckedOut();
	private Book book = new Book();
	private Borrowers borrower = new Borrowers();
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/Library";
	static final String user = "root";
	static final String pass = "password";
	Connection conn = null;
	Statement stmt = null;
	
	@Override
	public String execute() throws Exception {
		// check book availability/cardno in database
		int num = borrower.getCardNumber();
		if(getAvailability() >0 && getCardNo(num)){
			try{
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection(DB_URL, user, pass);
				stmt = conn.createStatement();
				String sql;
				sql = "Update books Set availability = availability -1 where isbn =" + "'" + book.getIsbn() + "'";
				stmt.executeQuery(sql);
				sql = "Insert into checkedout (isbn, cardno, date) Values ('" + book.getIsbn() + "','" + borrower.getCardNumber() + "','" + "'May 3 2015')";
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
	
	public int getAvailability(){
		int available = 100;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, user, pass);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT availability FROM books WHERE isbn = '8'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				available =rs.getInt("available");
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

		return available;
	}
	
	public boolean getCardNo(int cardno){
		String name = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, user, pass);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT name FROM borrowers WHERE cardno = '" + cardno + "'";
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
	
}
