package com.xingfugo.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xingfugo.business.dao.Course_videoDao;
import com.xingfugo.business.module.Course_video;

/**
 * @function 功能 课程视频管理Service层业务实现
 * @author 创建人 李良林
 * @date 创建日期 Sat Apr 04 16:10:38 CST 2015
 */

@Service
public class Course_videoService extends GenericService<Course_video,String>{

	private Course_videoDao course_videoDao;
	
	public Course_videoService() {}
	@Autowired
	public Course_videoService(Course_videoDao course_videoDao) {
		super(course_videoDao);
		this.course_videoDao = course_videoDao;
	}

}

