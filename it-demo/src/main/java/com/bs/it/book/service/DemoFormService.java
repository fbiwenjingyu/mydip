package com.bs.it.book.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.bs.it.book.dao.DemoFormMapper;
import com.bs.it.book.entity.DemoDepartment;
import com.bs.it.book.entity.DemoForm;
import com.bs.it.book.entity.DemoReason;
import com.bs.it.book.entity.DemoSection;
import com.bs.it.book.vo.DemoTree;
import com.bs.platform.bizform.entity.sys.Dict;
import com.bs.platform.core.support.ICrudService;
import com.bs.platform.core.vo.CommonResult;

public interface DemoFormService extends ICrudService<DemoFormMapper,DemoForm,Long>{

	List<DemoDepartment> listAllDepartMent();

	List<DemoSection> getSectionByDepartMentId(String departMentId);

	List<DemoReason> getReasonBySectionId(String sectionId);

	List<DemoTree> getDictTypeTree();

	List<Dict> getDictByTypeId(long dictTypeId);

	List<DemoTree> getDictTypeAll();

	CommonResult saveFormData(DemoForm demoForm);

	DemoForm getFormData(String caseNoValue);

	CommonResult updateFormDate(DemoForm demoForm);

	String getUserNameByUserId(String userId) throws Exception;

	void exportDictType(List<DemoForm> demoForm,
			HttpServletResponse response);

	void importDemoForm(MultipartFile file);

}
