package com.bs.it.book.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bs.platform.core.support.BaseEntity;

/**
 * @description: 
 * @copyright: dip (c)2020
 * @createTime: 2020-11-03 18:02:36
 * @author: chh
 * @version: 1.0
 */
@TableName(value="T_DEMO_REASON")
public class DemoReason extends BaseEntity<DemoReason> {

    private static final long serialVersionUID = 1L;
   
    /**
     * 名称
     */
    @TableField("NAME")
    private String name;
    /**
     * SECTION的ID
     */
    @TableField("SECTION_ID")
    private String sectionId;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}


}
