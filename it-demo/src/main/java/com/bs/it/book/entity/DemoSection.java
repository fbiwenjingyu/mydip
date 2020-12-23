package com.bs.it.book.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bs.platform.core.support.BaseEntity;

/**
 * @description: 
 * @copyright: dip (c)2020
 * @createTime: 2020-11-03 17:54:44
 * @author: chh
 * @version: 1.0
 */
@TableName(value="T_DEMO_SECTION")
public class DemoSection extends BaseEntity<DemoSection> {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @TableField("NAME")
    private String name;
    /**
     * 部门id
     */
    @TableField("DEPARTMENT_ID")
    private String departmentId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}


}
