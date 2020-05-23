package com.staff.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.staff.bean.Staff;
import com.staff.mapper.StaffMapper;

@Service
public class StaffServiceImpl implements StaffService{
	@Autowired
	private StaffMapper sm;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setSm(StaffMapper sm) {
		this.sm = sm;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Staff> selectStaff() {
		// TODO Auto-generated method stub
		List<Staff> list = this.sm.selectStaff();
		
		//清空
		while (redisTemplate.opsForList().size("staff")>0){
			redisTemplate.opsForList().leftPop("staff");
		}
		//向redis的某个key下面的list列表里面插入一个list列表，不会去重
		//redisTemplate.opsForList().range(key, start, end);
		redisTemplate.opsForList().rightPushAll("staff", list);
		redisTemplate.expire("staff",30 , TimeUnit.MINUTES);
		
		
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean insert(Staff record) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			if(sm.insert(record) == 1){
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		redisTemplate.expire("person",10 , TimeUnit.MILLISECONDS);
		return result;
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			if(sm.deleteByPrimaryKey(id) == 1){
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Staff selectByPrimaryKeySelective(Integer sid) {
	// TODO Auto-generated method stub
		
		return this.sm.selectByPrimaryKeySelective(sid);
	}

	@Override
	public boolean updateByPrimaryKey(Staff record) {
		// TODO Auto-generated method stub
		boolean result = false;
		try {
			if(sm.updateByPrimaryKey(record) == 1){
				result = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
