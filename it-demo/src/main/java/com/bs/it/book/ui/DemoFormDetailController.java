package com.bs.it.book.ui;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bs.it.book.entity.DemoFormDetail;
import com.bs.it.book.service.IDemoFormDetailService;
import com.bs.platform.core.support.BaseCrudController;
import com.bs.platform.core.vo.CommonResult;

/**
 * @description: 数据接口
 * @copyright: dip (c)2020
 * @createTime: 2020-11-17 16:08:39
 * @author: chh
 * @version: 1.0
 */
@Controller
@RequestMapping("/api/demoFormDetail")
public class DemoFormDetailController extends BaseCrudController<DemoFormDetail,Long> {

    @Resource
    private IDemoFormDetailService service;

    @Override
    protected IDemoFormDetailService getService() {
        return this.service;
    }
    
    @RequestMapping(value="/getFormDetailsByCaseNoValue",method=RequestMethod.GET)
    @ResponseBody
    public CommonResult getFormDetailsByCaseNoValue(String casenovalue){
    	CommonResult result = new CommonResult();
    	Wrapper<DemoFormDetail> wrapper = new EntityWrapper<>();
    	wrapper.eq("CASENOVALUE", casenovalue);
		List<DemoFormDetail> list = service.selectList(wrapper );
		result.setData(list);
		return result;
    }


	
}
