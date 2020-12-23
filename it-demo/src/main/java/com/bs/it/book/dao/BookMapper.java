package com.bs.it.book.dao;

import java.util.List;

import com.bs.it.book.entity.Book;
import com.bs.platform.core.support.CrudMapper;

/**
 * 国际化DAO.
 * 
 * @author Bruce
 */

public interface BookMapper extends CrudMapper<Book> {

	/**
	 * 测试多数据源
	 * @return
	 */
	List<Book> list4Salve();

}
