package com.bs.it.book.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.bs.it.book.dao.DemoDepartmentMapper;
import com.bs.it.book.dao.DemoFormMapper;
import com.bs.it.book.dao.DemoReasonMapper;
import com.bs.it.book.dao.DemoSectionMapper;
import com.bs.it.book.entity.DemoDepartment;
import com.bs.it.book.entity.DemoForm;
import com.bs.it.book.entity.DemoReason;
import com.bs.it.book.entity.DemoSection;
import com.bs.it.book.service.DemoFormService;
import com.bs.it.book.vo.DemoTree;
import com.bs.platform.bizform.dao.sys.DictMapper;
import com.bs.platform.bizform.dao.sys.DictTypeMapper;
import com.bs.platform.bizform.entity.sys.Dict;
import com.bs.platform.bizform.entity.sys.DictType;
import com.bs.platform.bizform.webservice.business.OrgEmployeeService.stub.OrgEmployeeServiceServiceStub.Entry_type0;
import com.bs.platform.bizform.webservice.business.OrgEmployeeService.stub.OrgEmployeeServiceServiceStub.LoadEmployeeInfoById;
import com.bs.platform.bizform.webservice.business.OrgEmployeeService.stub.OrgEmployeeServiceServiceStub.LoadEmployeeInfoByIdResponse;
import com.bs.platform.bizform.webservice.business.OrgEmployeeService.stub.OrgEmployeeServiceServiceStub.String2StringMap;
import com.bs.platform.bizform.webservice.business.utils.WebServiceUtils;
import com.bs.platform.core.constant.ResultCode;
import com.bs.platform.core.support.CrudServiceImpl;
import com.bs.platform.core.utils.ExcelUtils;
import com.bs.platform.core.vo.CommonResult;
import com.bs.platform.core.vo.ExcelLog;
import com.bs.platform.core.vo.ExcelLogs;
import com.bs.platform.core.vo.SheetData;

@Service
public class DemoFormServiceImpl extends CrudServiceImpl<DemoFormMapper,DemoForm,Long> implements DemoFormService{
	@Resource
	DemoDepartmentMapper departmentMapper;
	
	@Resource
	DemoSectionMapper sectionMapper;
	
	@Resource
	DemoReasonMapper reasonMapper;
	
	@Resource
	DictMapper dictMapper;
	
	@Resource 
	DictTypeMapper dictTypeMapper;
	
	@Resource
	DemoFormMapper demoFormMapper;
	
	@Resource
	WebServiceUtils webServiceUtils;
	
	@Override
	public List<DemoDepartment> listAllDepartMent(){
		return departmentMapper.selectList(null);
	}
	
	@Override
	public List<DemoSection> getSectionByDepartMentId(String departMentId){
		Wrapper<DemoSection> wrapper = new EntityWrapper<>();
		wrapper.eq("DEPARTMENT_ID", departMentId);
		return sectionMapper.selectList(wrapper);
	}
	
	@Override
	public List<DemoReason> getReasonBySectionId(String sectionId){
		Wrapper<DemoReason> wrapper = new EntityWrapper<>();
		wrapper.eq("SECTION_ID", sectionId);
		return reasonMapper.selectList(wrapper);
	}
	
	@Override
	public List<DemoTree> getDictTypeTree(){
		List<DictType> list = dictTypeMapper.selectList(null);
		List<DictType> rootNodes = getRootNodes(list);
		List<DemoTree> treeList = buildTree(rootNodes,list);
		return treeList;
	}
	
	private List<DictType> getRootNodes(List<DictType> list) {
		return list.stream().filter(node ->  node.getParentId() == null).collect(Collectors.toList());
	}

	private  List<DemoTree> buildTree(List<DictType> rootNodes,List<DictType> allDictType) {
		List<DemoTree> treeList = new ArrayList<>();
		for(DictType rootNode : rootNodes){
			DemoTree tree = new DemoTree();
			tree.setValue(rootNode.getIdentity());
			tree.setLabel(rootNode.getName());
			tree.setId(rootNode.getId());
			long parentDictType = rootNode.getId();
			List<DictType> childList = selectByParentDictType(parentDictType,allDictType);
			if(childList != null && childList.size() > 0){
				List<DemoTree> childTreeList = buildTree(childList,allDictType);
				tree.setChildren(childTreeList);
			}
			treeList.add(tree);
		}
		return treeList;
	}

	private List<DictType> selectByParentDictType(long parentDictType,
			List<DictType> allDictType) {
		List<DictType> list = allDictType.stream().filter(item-> item.getParentId()!= null && parentDictType==item.getParentId()).collect(Collectors.toList());
		return list;
	}

	
	@Override
	public List<Dict> getDictByTypeId(long dictTypeId){
		Wrapper<Dict> wrapper = new EntityWrapper<>();
		wrapper.eq("DICT_TYPE_ID", dictTypeId);
		return dictMapper.selectList(wrapper);
	}

	@Override
	public List<DemoTree> getDictTypeAll() {
		List<DictType> selectList = dictTypeMapper.selectList(null);
		List<DemoTree> treeList = selectList.stream().map(item -> {
			DemoTree demo = new DemoTree();
			demo.setValue(item.getIdentity());
			demo.setLabel(item.getName());
			demo.setId(item.getId());
			return demo;
		}).collect(Collectors.toList());
		return treeList;
	}
	
	@Override
	public CommonResult  saveFormData(DemoForm demoForm){
		 CommonResult r = createBefore(demoForm);
		 if(r != null){
			 return r;
		 }
		 CommonResult result = new CommonResult();
		 boolean b = this.insert(demoForm);
		 result.setSuccess(b);
		 return result;
	}

	@Override
	public DemoForm getFormData(String caseNoValue) {
		Wrapper<DemoForm> wrapper = new EntityWrapper<>();
		wrapper.eq("caseNoValue", caseNoValue);
		List<DemoForm> list = demoFormMapper.selectList(wrapper );
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public CommonResult updateFormDate(DemoForm demoForm){
		Wrapper<DemoForm> wrapper = new EntityWrapper<>();
		wrapper.eq("caseNoValue", demoForm.getCaseNoValue());
		Integer r = demoFormMapper.update(demoForm, wrapper );
		return new CommonResult(r);
	}
	
	

	@Override
	protected CommonResult createBefore(DemoForm m) {
		CommonResult result = new CommonResult();
        EntityWrapper<DemoForm> ew = new EntityWrapper<>();
        ew.eq("CASENOVALUE", m.getCaseNoValue());
        if (selectCount(ew) > 0) {
        	result.setCode(ResultCode.EXIST.getCode());
        	result.setSuccess(false);
            result.setMessage("案例号重复，请重新输入");
            return result;
        }
        return null;
	}

	@Override
	public String getUserNameByUserId(String userId) throws Exception{
		LoadEmployeeInfoById loadEmployeeInfoById = new LoadEmployeeInfoById();
		loadEmployeeInfoById.setIn0(userId);
		LoadEmployeeInfoByIdResponse resp = webServiceUtils.loadEmployeeInfoById(loadEmployeeInfoById);
		String2StringMap map = resp.getOut1();
		Entry_type0[] entry = map.getEntry();
		for(Entry_type0 e : entry){
			if("EMPNAME".equals(e.getKey())){
				return e.getValue();
			}
		}
		return null;
	}

	@Override
	public void exportDictType(List<DemoForm> demoForm,
			HttpServletResponse response) {
		File model = null;
		try {
			model = ResourceUtils
					.getFile("classpath:com/bs/it/demo_form_template.xlsx");
		} catch (Exception e1) {
			logger.error(e1.toString());
		}
		if (model == null) {
			logger.info("model is not exists !");
			return;
		}
		SheetData sd = new SheetData("表单数据导出");
		for (DemoForm form : demoForm) {
			sd.addData(form);
		}
		try {
			OutputStream output = response.getOutputStream();
			response.reset();
			response.setContentType("application/vnd.ms-excel;charset=utf-8");
			response.setHeader("Content-Disposition", "attchement;filename=dictType.xlsx");
			ExcelUtils.writeData(model, output, sd);
		} catch (IOException e) {
			logger.error(e.toString());
		}
		
	}

	@Override
	public void importDemoForm(MultipartFile file) {
		InputStream inputStream= null;
		try {
			inputStream= file.getInputStream();
		} catch (Exception e1) {
			logger.error(e1.toString());
		}
	    ExcelLogs logs =new ExcelLogs();
	    Collection<DemoForm> importExcel = ExcelUtils.readExcel(DemoForm.class, inputStream, "yyyy-MM-dd", logs);
	    
	    // 如果读取有错误，显示出错误信息
	    if(logs.getHasError()){
	    	for(ExcelLog excelLog : logs.getErrorLogList()){
	    		logger.error(excelLog.getLog());
	    	}
	    }
		this.insertBatch((List<DemoForm>) importExcel);
	}
	
	
	
}
