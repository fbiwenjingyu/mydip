package com.bs.it.book.service.impl;


import org.springframework.stereotype.Service;

import com.bs.it.book.dao.DemoFormDetailMapper;
import com.bs.it.book.entity.DemoFormDetail;
import com.bs.it.book.service.IDemoFormDetailService;
import com.bs.platform.core.support.CrudServiceImpl;

/**
 * @description: 服务实现类
 * @copyright: dip (c)2020
 * @createTime: 2020-11-17 16:08:39
 * @author: chh
 * @version: 1.0
 */
@Service
public class DemoFormDetailServiceImpl extends CrudServiceImpl<DemoFormDetailMapper,DemoFormDetail,Long> implements IDemoFormDetailService {
	
}
