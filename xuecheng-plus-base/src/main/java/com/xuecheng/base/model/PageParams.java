package com.xuecheng.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import lombok.extern.java.Log;

/**
 * @description 分页查询通用参数
 * @author Mr.M
 * @date
 * @version 1.0
 */
@Data
@ToString
public class PageParams {

    //当前页码
    @ApiModelProperty("Page number")
    private Long pageNo = 1L;

    //每页记录数默认值
    @ApiModelProperty("size in every page")
    private Long pageSize =10L;

    public PageParams(){

    }

    public PageParams(long pageNo,long pageSize){
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }



}