package com.namphuong.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.namphuong.dao.*;
import com.namphuong.model.Book;


@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BookController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		// Get param
		String id = request.getParameter("id"); 
		String type   = request.getParameter("type");
		
		// Call BookLogic ==> Obtain bean
		BookDao dao = BookDaoDbImpl.getInstance();
		
		List<Book> books = null;
		try {
			books = dao.getAllBook();//getAll 1 lần duy nhất
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		//store into request
		request.setAttribute("books", books);
		
		//forward sang view
		String address = null;
		if(id==null && type == null) {
			address = "/WEB-INF/BookList.jsp"; //default view
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
