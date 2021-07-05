package com.namphuong.dao;

import java.util.List;
import com.namphuong.model.*;

public interface BookDao {
	public List<Book>  getAllBook() throws Exception; // Nhớ: có throws Exception
	
}
