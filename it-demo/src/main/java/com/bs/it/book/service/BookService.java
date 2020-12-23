package com.bs.it.book.service;


import org.springframework.transaction.annotation.Transactional;

import com.bs.it.book.dao.BookMapper;
import com.bs.it.book.entity.Book;
import com.bs.platform.core.support.ICrudService;
import com.bs.platform.core.vo.CommonResult;

/**
 * 国际化Service.
 * 
 * @author Bruce
 */
@Transactional()
public interface BookService extends ICrudService<BookMapper,Book,Long> {
	
    /**
     * @description: 测试多数据库查询
     * @return
     */
    CommonResult list4Salve();
    
    void saveThis();

}