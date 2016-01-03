package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.CourseDao;
import com.xingfugo.business.module.Course;

/**
 * @function 功能 课程管理Service层业务实现
 * @author 创建人 李良林
 * @date 创建日期 Sat Apr 04 15:02:05 CST 2015
 */

@Service
public class CourseService extends GenericService<Course,String>{

	private CourseDao courseDao;
	
	public CourseService() {}
	@Autowired
	public CourseService(CourseDao courseDao) {
		super(courseDao);
		this.courseDao = courseDao;
	}

}

