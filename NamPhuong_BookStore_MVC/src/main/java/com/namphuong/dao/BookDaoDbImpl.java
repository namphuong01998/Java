package com.namphuong.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import com.namphuong.model.Book;


public class BookDaoDbImpl implements BookDao {
	
	private DataSource datasource; //Connection poo (datasource) phải là biến toàn cục. Vì có hàm của class dùng biến này. 
	
	// ___________________________________________DAO(Singleton)______________________________________________________
	// 1. Tạo đối tượng kiểu chính nó
	private static BookDaoDbImpl instance; 	
	
	//2. Constructor là private ==> Constructor này chỉ gọi 1 lần duy nhất (cơ chế Singleton)
	private BookDaoDbImpl() {
		//CONNECTION POO 	
		//Con1.Set up các thông số (Bước lấy dữ liệu từ DB)
			PoolProperties p = new PoolProperties();
			p.setUrl("jdbc:mysql://localhost:3306/mysql");
			p.setDriverClassName("com.mysql.jdbc.Driver");// DOWNLOAD: mysql jdbc driver. Bản chất mysql, thì jdk và project ko có sẵn driver ==> Phải add driver vào tomcat server (thư mục lib).
			p.setUsername("root");                        
			p.setPassword("password"); 		
		//Con2.Tạo connection (DataSource)
			datasource = new DataSource(); // Do contructor chỉ tạo 1 lần ==> connection poo (datasource) này cũng chỉ tạo 1 lần.
			datasource.setPoolProperties(p);
		
	}
	//3. Hàm getInstance() ==> Trong bài này trả về kiểu của lớp cha 
	public static BookDao getInstance() {
		if(instance == null) {				   // Kiểm tra đối tượng: Nếu đối tượng chưa tồn tại, chưa new bao giờ
			instance = new BookDaoDbImpl();// ==> Tạo mới
		}		                               
		return instance;                       
	}	
	//__________________________________Install tất cả các hàm trong interface_________________________________________
	@Override
	public List<Book> getAllBook() throws Exception{  // Nhớ: có throws Exception
		List<Book> books = new ArrayList<>();	
	    
	    //Con3.Get connection
	    Connection con = null;
	    try {
	    	con = datasource.getConnection(); 
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery("select * from BookList");
	        int cnt = 1;
	        while (rs.next()) {// Ở mỗi vòng lặp, sẽ có 1 cuốn sách (~ mỗi 1 hàng có 1 cuốn sách, đưa vào cái List)
	            Book book = new Book();  
	            
	            book.setId(rs.getString("ID"));
	            book.setName(rs.getString("Nam"));
	            book.setDescribe(rs.getString("Describe"));
	            book.setPrice(rs.getFloat("price"));
	        	       
	        }
	        rs.close();
	        st.close();
	    }finally {
	          if (con!=null) try {con.close();}catch (Exception ignore) {}
	    }
	      
	    return books;	
	}
}
