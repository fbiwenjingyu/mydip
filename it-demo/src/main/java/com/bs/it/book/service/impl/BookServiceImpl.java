package com.bs.it.book.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.bs.it.book.dao.BookMapper;
import com.bs.it.book.entity.Book;
import com.bs.it.book.service.BookService;
import com.bs.platform.core.annotations.Log;
import com.bs.platform.core.constant.LogOutType;
import com.bs.platform.core.support.CrudServiceImpl;
import com.bs.platform.core.vo.CommonResult;

/**
 * 国际化Service.
 * 
 * @author chh
 */
@Service
public class BookServiceImpl extends CrudServiceImpl<BookMapper, Book, Long> implements BookService {
	
    @Resource
    private BookMapper bookMapper;
	
	/**
     * @description: 测试多数据库查询
     * @return
     */
    @DS("slave")
    @Log(outType=LogOutType.FILE, value="测试多数据库查询")
    public CommonResult list4Salve(){
    	CommonResult result = new CommonResult();
    	List<Book> books = bookMapper.list4Salve();
    	result.setData(books);
    	return result;
    }
    
    public void saveThis(){
    	Book book = new Book();
    	book.setName("xxxx");
    	bookMapper.insert(book);
    	int i = 1/0;
    }

}
