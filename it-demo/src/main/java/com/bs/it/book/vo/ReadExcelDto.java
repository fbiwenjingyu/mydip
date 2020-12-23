/**
 * @author SargerasWang
 */
package com.bs.it.book.vo;

import java.util.Date;

import com.bs.platform.core.annotations.ExcelCell;

/**
 * The <code>ReadExcelDto</code>
 * 对应excel模板的各个列
 */
public class ReadExcelDto {
    @ExcelCell(index = 0)
    private String name;
    @ExcelCell(index = 1, valid=@ExcelCell.Valid( gt = 50 ))
    private Integer age;
    @ExcelCell(index = 2)
    private String sex;
    @ExcelCell(index = 3)
    private Date birthDay;
    
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

    
}
