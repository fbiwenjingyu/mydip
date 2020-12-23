package com.bs.it.book.entity;

import com.baomidou.mybatisplus.annotations.KeySequence;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bs.platform.core.support.BaseEntity;

/**
 * @description: 
 * @copyright: dip (c)2020
 * @createTime: 2020-11-17 16:08:39
 * @author: chh
 * @version: 1.0
 */
@TableName(value="T_DEMO_FORM_DETAIL")
@KeySequence(value = "SEQ_T_DEMO_FORM_DETAIL", clazz = Long.class)
public class DemoFormDetail extends BaseEntity<DemoFormDetail> {

    private static final long serialVersionUID = 1L;
    @TableField("CASENOVALUE")
    private String casenovalue;
    @TableField("GOODS_NAME")
    private String goodsName;
    @TableField("GOODS_CODE")
    private String goodsCode;
    @TableField("CREATOR_ID")
    private Long creatorId;
    @TableField("CREATE_TIME")
    private String createTime;
    @TableField("LAST_MODIFIER_ID")
    private Long lastModifierId;
    @TableField("LAST_MODIFY_TIME")
    private String lastModifyTime;
    @TableField("ARCHIVE")
    private Integer archive;
    @TableField("QUANTITY")
    private String quantity;

	public String getCasenovalue() {
		return casenovalue;
	}

	public void setCasenovalue(String casenovalue) {
		this.casenovalue = casenovalue;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsCode() {
		return goodsCode;
	}

	public void setGoodsCode(String goodsCode) {
		this.goodsCode = goodsCode;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}


}
