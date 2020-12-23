package com.bs.it.book.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 供mq测试传输对象
 * @author chh
 *
 */
public class OrderExt implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4018484718309787479L;
	private String id;
    private Date createTime;
    private Long money;
    private String title;
    
    public OrderExt(String id, Date createTime, Long money, String title){
    	this.id = id;
    	this.createTime = createTime;
    	this.money = money;
    	this.title = title;
    }
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getMoney() {
		return money;
	}
	public void setMoney(Long money) {
		this.money = money;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    
    
    
}