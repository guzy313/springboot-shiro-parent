package com.my.shiro.vo;


import com.my.shiro.pojo.ShUsers;

/**
 * @author Gzy
 * @version 1.0
 * @Description 用户视图对象
 */
public class UserVo extends ShUsers {
    private Integer rowNo;


    public Integer getRowNo() {
        return rowNo;
    }

    public void setRowNo(Integer rowNo) {
        this.rowNo = rowNo;
    }
}
