package com.xuecheng.content;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CourseBaseMapperTests {

    @Autowired
    CourseBaseMapper courseBaseMapper;

    @Test
    public void testCourseBaseMapper(){

        //test for split pages

        CourseBase courseBase = courseBaseMapper.selectById(18);
        Assertions.assertNotNull(courseBase);

        //search condition
        QueryCourseParamsDto queryCourseParamsDto = new QueryCourseParamsDto();
        queryCourseParamsDto.setCourseName("java");


        //filter conditions
        LambdaQueryWrapper<CourseBase> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.like(StringUtils.isNotEmpty(queryCourseParamsDto.getCourseName()), CourseBase::getName, queryCourseParamsDto.getCourseName());
        //base on name


        //status

        queryWrapper.eq(StringUtils.isNotEmpty(queryCourseParamsDto.getAuditStatus()), CourseBase::getAuditStatus, queryCourseParamsDto.getAuditStatus());

        //create page split object

        PageParams pageParams = new PageParams();
        pageParams.setPageNo(1L);
        pageParams.setPageSize(2L);

        //current page split, page, and page record
        Page<CourseBase> page =  new Page<CourseBase>(pageParams.getPageNo(), pageParams.getPageSize());

        Page<CourseBase> pageResult = courseBaseMapper.selectPage(page, queryWrapper);
        List<CourseBase> items =  pageResult.getRecords();
        long total = pageResult.getTotal();

        PageResult<CourseBase> courseBasePageResult = new PageResult<>(items,total, pageParams.getPageNo(), pageParams.getPageSize());

        System.out.println(courseBasePageResult);



    }
}
