package com.bs.it.book.ui;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.bs.it.book.entity.DemoDepartment;
import com.bs.it.book.entity.DemoForm;
import com.bs.it.book.entity.DemoFormDetail;
import com.bs.it.book.entity.DemoReason;
import com.bs.it.book.entity.DemoSection;
import com.bs.it.book.service.DemoFormService;
import com.bs.it.book.service.IDemoFormDetailService;
import com.bs.it.book.vo.DemoTree;
import com.bs.platform.bizform.dao.sys.AttachMapper;
import com.bs.platform.bizform.dao.sys.DictMapper;
import com.bs.platform.bizform.dao.sys.DictTypeMapper;
import com.bs.platform.bizform.entity.sys.Attach;
import com.bs.platform.bizform.entity.sys.Dict;
import com.bs.platform.bizform.entity.sys.DictType;
import com.bs.platform.core.annotations.WhiteList;
import com.bs.platform.core.auth.OperatorBase;
import com.bs.platform.core.auth.OperatorUtils;
import com.bs.platform.core.vo.CommonResult;

@RestController
@RequestMapping("/api/demo")
@Api(tags = "demo表单管理")
public class DemoFormController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Value("${sysconfig.appcode}")
    private String appcode;
	
	@Resource
	DemoFormService formService;
	
	@Resource
	IDemoFormDetailService formDetailService;
	
	@Resource
	AttachMapper attachMapper;
	
	@Resource
	DictTypeMapper dictTypeMapper;
	
	@Resource
	DictMapper dictMapper;
	
	@Resource
	OperatorUtils operatorUtils;
	
	
	
	
	/**
     * 获取当前用户信息
     * @return
     */
    @ResponseBody
    @WhiteList
    @RequestMapping(value = "/getUserInfo")
    public CommonResult getUserInfo() {
    	OperatorBase operatorBase = operatorUtils.getUser();
        return new CommonResult(operatorBase);
    }
	
    @WhiteList
	@RequestMapping("/listAllDepartMent")
	public CommonResult listAllDepartMent(){
		CommonResult result = new CommonResult();
		List<DemoDepartment> allDepartMent = formService.listAllDepartMent();
		result.setData(allDepartMent);
		return result;
	}
	
	@RequestMapping("/getSectionByDepartMentId")
	public CommonResult getSectionByDepartMentId(String departMentId){
		CommonResult result = new CommonResult();
		List<DemoSection> sectionList = formService.getSectionByDepartMentId(departMentId);
		result.setData(sectionList);
		return result;
	}
	
	@RequestMapping("/getReasonBySectionId")
	public CommonResult getReasonBySectionId(String sectionId){
		CommonResult result = new CommonResult();
		List<DemoReason> reasonList = formService.getReasonBySectionId(sectionId);
		result.setData(reasonList);
		return result;
	}
	
	@RequestMapping("/getDictTypeTree")
	public CommonResult getDictTypeTree(){
		CommonResult result = new CommonResult();
		List<DemoTree> dictTypeTree = formService.getDictTypeTree();
		result.setData(dictTypeTree);
		return result;
	}
	
	@RequestMapping("/getDictTypeAll")
	public CommonResult getDictTypeAll(){
		CommonResult result = new CommonResult();
		List<DemoTree> dictTypeTree = formService.getDictTypeAll();
		result.setData(dictTypeTree);
		return result;
	}
	
	@RequestMapping(value="/getDictTypeAllDemo",method=RequestMethod.GET)
	public CommonResult getDictTypeAllDemo(){
		CommonResult result = new CommonResult();
		List<DictType> list = dictTypeMapper.selectList(null);
		result.setData(list);
		return result;
	}
	
	
	@RequestMapping("/getDictByTypeId")
	public CommonResult getDictByTypeId(long dictTypeId){
		CommonResult result = new CommonResult();
		List<Dict> list = formService.getDictByTypeId(dictTypeId);
		List<DemoTree> treeList = list.stream().map(item -> {
			DemoTree demoTree = new DemoTree();
			demoTree.setValue(item.getCode());
			demoTree.setLabel(item.getName());
			demoTree.setId(item.getId());
			return demoTree;
		}).collect(Collectors.toList());
		result.setData(treeList);
		return result;
	}
	
	@RequestMapping("/generateCaseNo")
	public String generateCaseNo(){
		return UUID.randomUUID().toString();
	}
	
	@RequestMapping(value="/getUploadFileById",method=RequestMethod.GET)
	public CommonResult getUploadFileById(Long[] ids){
		CommonResult result = new CommonResult();
		List<Attach> list = attachMapper.selectBatchIds(Arrays.asList(ids));
		result.setData(list);
		return result;
	}
	
	@RequestMapping("/test")
	public String test(Long[] ids){
		return "tttesss";
	}
	
	@RequestMapping(value="/saveFormData",method=RequestMethod.POST)
	public CommonResult saveFormData(@RequestBody Map<String,Object> params){
		Map map = (Map) params.get("demoForm");
		DemoForm demoForm = new DemoForm();
		try {
			BeanUtils.populate(demoForm, map);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		List<DemoFormDetail> goodsList = new ArrayList<DemoFormDetail>();
		List<Map> detailList = (List<Map>) params.get("goodsList");
		for(Map m : detailList){
			DemoFormDetail detail = new DemoFormDetail();
			detail.setCasenovalue(demoForm.getCaseNoValue());
			try {
				BeanUtils.populate(detail, m);
				goodsList.add(detail);
			}catch (Exception e) {
				logger.error(e.toString());
			}
		}
		if(goodsList != null && goodsList.size() > 0){
			formDetailService.insertBatch(goodsList);
		}
		logger.info(demoForm.toString());
		return formService.saveFormData(demoForm);
	}
	
	@RequestMapping(value="/getFormData",method=RequestMethod.GET)
	public CommonResult getFormData(String caseNoValue){
		CommonResult result = new CommonResult();
		DemoForm demoForm = formService.getFormData(caseNoValue);
		if(demoForm != null){
			String dicTypeValue = demoForm.getDicTypeValue();
			if(StringUtils.isNotEmpty(dicTypeValue)){
				DictType dictType = dictTypeMapper.selectById(Long.valueOf(dicTypeValue));
				demoForm.setDictType(dictType);
			}
			String dictValue = demoForm.getDicItemsValue();
			if(StringUtils.isNotEmpty(dictValue)){
				Dict dict = dictMapper.selectById(Long.valueOf(dictValue));
				demoForm.setDict(dict);
			}	
			String dictId = demoForm.getDictId();
			if(StringUtils.isNotEmpty(dictId)){
				Dict dictById = dictMapper.selectById(Long.valueOf(dictId));
				demoForm.setDictById(dictById);
			}
		}
		result.setData(demoForm);
		return result;
	}
	
	@Transactional
	@RequestMapping("/updateFormData")
	public CommonResult updateFormDate(@RequestBody Map<String,Object> params){
		Map demoFormMap = (Map) params.get("demoForm");
		List<Map> DemoFormDetailMap = (List<Map>) params.get("goodsList");
		List<Long> deleteIds = (List<Long>) params.get("deleteIds");
		DemoForm demoForm = new DemoForm();
		try {
			BeanUtils.populate(demoForm, demoFormMap);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		List<DemoFormDetail> goodsList = new ArrayList<DemoFormDetail>();
		for(Map m : DemoFormDetailMap){
			DemoFormDetail detail = new DemoFormDetail();
			detail.setCasenovalue(demoForm.getCaseNoValue());
			try {
				BeanUtils.populate(detail, m);
				goodsList.add(detail);
			}catch (Exception e) {
				logger.error(e.toString());
			}
		}
		
		if(goodsList != null && goodsList.size() > 0){
			formDetailService.insertOrUpdateBatch(goodsList);
		}
		if(deleteIds != null && deleteIds.size() > 0){
			formDetailService.deleteBatchIds(deleteIds);
		}
		
		return formService.updateFormDate(demoForm);
	}
	
	@RequestMapping("/getAllFormData")
	public CommonResult getAllFormData(){
		CommonResult result = new CommonResult();
		List<DemoForm> list = formService.selectList(null);
		result.setData(list);
		return result;
	}
	
	@RequestMapping(value="/getFormDataByCondition",method=RequestMethod.POST)
	public CommonResult getFormDataByCondition(@RequestBody DemoForm m,int page,int pageSize){
		CommonResult result = new CommonResult();
		Page<DemoForm> demoPage = new Page<>();
		demoPage.setCurrent(page);
		demoPage.setSize(pageSize);
		Wrapper<DemoForm> wrapper = new EntityWrapper<>();
		if(m != null){
			if(StringUtils.isNotEmpty(m.getCaseDate())){
				wrapper.like("CASEDATE", m.getCaseDate());
			}
			if(StringUtils.isNotEmpty(m.getCaseNoValue())){
				wrapper.like("CASENOVALUE", m.getCaseNoValue());
			}
			if(StringUtils.isNotEmpty(m.getTextarea())){
				wrapper.like("TEXTAREA", m.getTextarea());
			}
		}
		Page<DemoForm> selectPage = formService.selectPage(demoPage, wrapper);
		Map<String,Object> map = new HashMap<>();
        map.put("list", selectPage.getRecords());
        map.put("size", selectPage.getTotal());
		result.setData(map);
		return result;
	}
	
	
	@GetMapping("/getAppCode")
	public String getAppCode() {
		return appcode;
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public CommonResult login(String userId){
		CommonResult result = new CommonResult();
		try {
			JSONObject obj = new JSONObject();
			obj.put("name", formService.getUserNameByUserId(userId));
			obj.put("avatar", "https://gw.alipayobjects.com/zos/rmsportal/ubnKSIfAJTxIgXOKlciN.png");
			obj.put("userid", userId);
			obj.put("appcode", appcode);
			result.setCode(0L);
			result.setMessage("中午好，欢迎回来");
			JSONObject user = new JSONObject();
			user.put("user", obj);
			result.setData(user);
		} catch (Exception e) {
			logger.error(e.toString());
		}
		return result;
		
	}
	
	
	@ApiOperation(value = "根据模板导出表单数据到excel文件", notes = "")
	@RequestMapping(value = "/exportDemoForm", method = RequestMethod.GET)
    public void exportDictType(Long[] ids,HttpServletResponse response){
    	List<Long> idList = Arrays.asList(ids);
		List<DemoForm> demoFormList = formService.selectBatchIds(idList);
		formService.exportDictType(demoFormList,response);
    }
	
	@ApiOperation(value = "根据模板导入excel文件到数据库", notes = "")
    @ResponseBody
    @RequestMapping(value = "/importDemoForm", method = RequestMethod.POST)
    public CommonResult importDemoForm(@RequestParam(value="uploadFile", required = false) MultipartFile file){
		formService.importDemoForm(file);
    	return new CommonResult();
    }
	
}
