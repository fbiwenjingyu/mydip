package com.bs.it.book.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bs.platform.core.support.BaseEntity;

/**
 * @description: 
 * @copyright: dip (c)2020
 * @createTime: 2020-11-03 17:47:22
 * @author: chh
 * @version: 1.0
 */
@TableName(value="T_DEMO_DEPARTMENT")
public class DemoDepartment extends BaseEntity<DemoDepartment> {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    @TableField("NAME")
    private String name;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
