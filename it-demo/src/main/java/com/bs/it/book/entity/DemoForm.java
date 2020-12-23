package com.bs.it.book.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bs.platform.bizform.entity.sys.Dict;
import com.bs.platform.bizform.entity.sys.DictType;
import com.bs.platform.core.annotations.ExcelCell;
import com.bs.platform.core.support.BaseEntity;

/**
 * @description: 
 * @copyright: dip (c)2020
 * @createTime: 2020-11-09 19:28:30
 * @author: chh
 * @version: 1.0
 */
@TableName(value="T_DEMO_FORM")
@KeySequence(value = "SEQ_T_DEMO_FORM", clazz = Long.class)
public class DemoForm extends BaseEntity<DemoForm> {

    private static final long serialVersionUID = 1L;
    @ExcelCell(index = 0)
    @TableField("DICITEMSVALUE")
    private String dicItemsValue;
    @ExcelCell(index = 1)
    @TableField("ORGMANYTREEVALUE")
    private String orgManyTreeValue;
    @ExcelCell(index = 2)
    @TableField("ORGMANYTABLEVALUE")
    private String orgManyTableValue;
    @ExcelCell(index = 3)
    @TableField("FILEIDLIST")
    private String fileIdList;
    @TableField("NOTE1")
    private String note1;
    @TableField("NOTE2")
    private String note2;
    @TableField("NOTE3")
    private String note3;
    @TableField("NOTE4")
    private String note4;
    @TableField("NOTE5")
    private String note5;
    @ExcelCell(index = 4)
    @TableField("STARTTIME")
    private String starttime;
    @ExcelCell(index = 5)
    @TableField("ENDTIME")
    private String endtime;
    @ExcelCell(index = 6)
    @TableField("CASENOVALUE")
    private String caseNoValue;
    @ExcelCell(index = 7)
    @TableField("CASEDATE")
    private String caseDate;
    @ExcelCell(index = 8)
    @TableField("STARTTIMEENDTIME")
    private String startTimeEndTime;
    @ExcelCell(index = 9)
    @TableField("TEXTAREA")
    private String textarea;
    @ExcelCell(index = 10)
    @TableField("AUTOCOMPLETE")
    private String autoComplete;
    @ExcelCell(index = 11)
    @TableField("MENTIONSTEXT")
    private String mentionsText;
    @ExcelCell(index = 12)
    @TableField("RADIOVLAUE")
    private String radioVlaue;
    @ExcelCell(index = 13)
    @TableField("RATEVLAUE")
    private String rateVlaue;
    @ExcelCell(index = 14)
    @TableField("MULTISELECT")
    private String multiSelect;
    @ExcelCell(index = 15)
    @TableField("SLIDERVALUE")
    private String sliderValue;
    @ExcelCell(index = 16)
    @TableField("TIMEPICKERVALUE")
    private String timePickerValue;
    @ExcelCell(index = 17)
    @TableField("TRANSFERTARGETKEYS")
    private String transferTargetKeys;
    @ExcelCell(index = 18)
    @TableField("INPUTNUMBER")
    private Long inputNumber;
    @ExcelCell(index = 19)
    @TableField("DICTYPEVALUE")
    private String dicTypeValue;
    @ExcelCell(index = 20)
    @TableField("ORANGEINPUTBOXTEXT")
    private String orangeInputBoxText;
    @TableField("USERID")
    private String userId;
    @TableField("USERNAME")
    private String userName;
    @TableField("ORGID")
    private String orgId;
    @TableField("ORGNAME")
    private String orgName;
    @TableField("PERSONMANYTABLEVLAUE")
    private String personManyTableVlaue;
    @TableField("DICTID")
    private String dictId;
    
    @TableField(exist = false)
    private DictType dictType;
    @TableField(exist = false)
    private Dict dict;
    
    @TableField(exist = false)
    private Dict dictById;
    
    
	public String getDicItemsValue() {
		return dicItemsValue;
	}
	public void setDicItemsValue(String dicItemsValue) {
		this.dicItemsValue = dicItemsValue;
	}
	public String getOrgManyTreeValue() {
		return orgManyTreeValue;
	}
	public void setOrgManyTreeValue(String orgManyTreeValue) {
		this.orgManyTreeValue = orgManyTreeValue;
	}
	public String getOrgManyTableValue() {
		return orgManyTableValue;
	}
	public void setOrgManyTableValue(String orgManyTableValue) {
		this.orgManyTableValue = orgManyTableValue;
	}
	public String getFileIdList() {
		return fileIdList;
	}
	public void setFileIdList(String fileIdList) {
		this.fileIdList = fileIdList;
	}
	public String getNote1() {
		return note1;
	}
	public void setNote1(String note1) {
		this.note1 = note1;
	}
	public String getNote2() {
		return note2;
	}
	public void setNote2(String note2) {
		this.note2 = note2;
	}
	public String getNote3() {
		return note3;
	}
	public void setNote3(String note3) {
		this.note3 = note3;
	}
	public String getNote4() {
		return note4;
	}
	public void setNote4(String note4) {
		this.note4 = note4;
	}
	public String getNote5() {
		return note5;
	}
	public void setNote5(String note5) {
		this.note5 = note5;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getCaseNoValue() {
		return caseNoValue;
	}
	public void setCaseNoValue(String caseNoValue) {
		this.caseNoValue = caseNoValue;
	}
	public String getCaseDate() {
		return caseDate;
	}
	public void setCaseDate(String caseDate) {
		this.caseDate = caseDate;
	}
	public String getStartTimeEndTime() {
		return startTimeEndTime;
	}
	public void setStartTimeEndTime(String startTimeEndTime) {
		this.startTimeEndTime = startTimeEndTime;
	}
	public String getTextarea() {
		return textarea;
	}
	public void setTextarea(String textarea) {
		this.textarea = textarea;
	}
	public String getAutoComplete() {
		return autoComplete;
	}
	public void setAutoComplete(String autoComplete) {
		this.autoComplete = autoComplete;
	}
	public String getMentionsText() {
		return mentionsText;
	}
	public void setMentionsText(String mentionsText) {
		this.mentionsText = mentionsText;
	}
	public String getRadioVlaue() {
		return radioVlaue;
	}
	public void setRadioVlaue(String radioVlaue) {
		this.radioVlaue = radioVlaue;
	}
	public String getRateVlaue() {
		return rateVlaue;
	}
	public void setRateVlaue(String rateVlaue) {
		this.rateVlaue = rateVlaue;
	}
	public String getMultiSelect() {
		return multiSelect;
	}
	public void setMultiSelect(String multiSelect) {
		this.multiSelect = multiSelect;
	}
	public String getSliderValue() {
		return sliderValue;
	}
	public void setSliderValue(String sliderValue) {
		this.sliderValue = sliderValue;
	}
	public String getTimePickerValue() {
		return timePickerValue;
	}
	public void setTimePickerValue(String timePickerValue) {
		this.timePickerValue = timePickerValue;
	}
	public String getTransferTargetKeys() {
		return transferTargetKeys;
	}
	public void setTransferTargetKeys(String transferTargetKeys) {
		this.transferTargetKeys = transferTargetKeys;
	}
	public Long getInputNumber() {
		return inputNumber;
	}
	public void setInputNumber(Long inputNumber) {
		this.inputNumber = inputNumber;
	}
	public String getDicTypeValue() {
		return dicTypeValue;
	}
	public void setDicTypeValue(String dicTypeValue) {
		this.dicTypeValue = dicTypeValue;
	}
	
	public String getOrangeInputBoxText() {
		return orangeInputBoxText;
	}
	public void setOrangeInputBoxText(String orangeInputBoxText) {
		this.orangeInputBoxText = orangeInputBoxText;
	}	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	
	
	public String getPersonManyTableVlaue() {
		return personManyTableVlaue;
	}
	public void setPersonManyTableVlaue(String personManyTableVlaue) {
		this.personManyTableVlaue = personManyTableVlaue;
	}
	
	
	public DictType getDictType() {
		return dictType;
	}
	public void setDictType(DictType dictType) {
		this.dictType = dictType;
	}
	public Dict getDict() {
		return dict;
	}
	public void setDict(Dict dict) {
		this.dict = dict;
	}
	
	public String getDictId() {
		return dictId;
	}
	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
	public Dict getDictById() {
		return dictById;
	}
	public void setDictById(Dict dictById) {
		this.dictById = dictById;
	}
	@Override
	public String toString() {
		return "DemoForm [dicItemsValue=" + dicItemsValue
				+ ", orgManyTreeValue=" + orgManyTreeValue
				+ ", orgManyTableValue=" + orgManyTableValue + ", fileIdList="
				+ fileIdList + ", note1=" + note1 + ", note2=" + note2
				+ ", note3=" + note3 + ", note4=" + note4 + ", note5=" + note5
				+ ", starttime=" + starttime + ", endtime=" + endtime
				+ ", caseNoValue=" + caseNoValue + ", caseDate=" + caseDate
				+ ", startTimeEndTime=" + startTimeEndTime + ", textarea="
				+ textarea + ", autoComplete=" + autoComplete
				+ ", mentionsText=" + mentionsText + ", radioVlaue="
				+ radioVlaue + ", rateVlaue=" + rateVlaue + ", multiSelect="
				+ multiSelect + ", sliderValue=" + sliderValue
				+ ", timePickerValue=" + timePickerValue
				+ ", transferTargetKeys=" + transferTargetKeys
				+ ", inputNumber=" + inputNumber + ", dicTypeValue="
				+ dicTypeValue + ", orangeInputBoxText=" + orangeInputBoxText
				+ ", userId=" + userId + ", userName=" + userName + ", orgId="
				+ orgId + ", orgName=" + orgName + ", personManyTableVlaue="
				+ personManyTableVlaue + "]";
	}
	
	
	
	

	


}
