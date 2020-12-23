package com.bs.it.book.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bs.platform.core.support.BaseEntity;

/**
 * 国际化实体类，用于存储语言-键-值.
 * 
 * @author chh
 */
@TableName(value = "BOOK")
@KeySequence(value = "SEQ_BOOK", clazz = Long.class)
public class Book extends BaseEntity<Book> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2884332738423850973L;
	@TableField("NAME")
	private String name;

	@TableField("REMARK")
	private String remark;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
