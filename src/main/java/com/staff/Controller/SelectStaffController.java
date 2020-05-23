package com.staff.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.staff.bean.Staff;
import com.staff.bean.StaffDepa;
import com.staff.service.StaffService;


@Controller
public class SelectStaffController {
	@Autowired
	private StaffService sse;
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;
	@Autowired
	public SolrTemplate solrTemplate;

	@Autowired
	public SolrClient solrClient;

	
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void setSse(StaffService sse) {
		this.sse = sse;
	}
	
	@RequestMapping("/selectTy.win")
	public String TestTy(){
		
		return "index";
	}
	
	
	
	//查询用户信息
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectStaff.win")
	public String selectStaff(Model model){
		//List<Staff> list = sse.selectStaff();
		//model.addAttribute("list",list);
		
		//从redis中取出某一个key下面的list列表，0表示从列表的第0个元素开始取，-1表示直取到倒数第一个元素
		List<Staff> list = redisTemplate.opsForList().range("staff", 0, -1);
		if(list.size()==0){//如果缓存中的数据为空的时候进行重新获取数据
			list=sse.selectStaff();
			model.addAttribute("list", list);
		}else{
			System.out.println("----------------------从缓存中取数据");
			model.addAttribute("list", list);
		}
		
		return "index";
	}
	//使用Solr查询员工信息
	@RequestMapping("/selectSolr.win")
	public String mian(String tname,Map<String, Object> map) {
		Query query=new SimpleQuery("*:*");
		if(tname != null){
			Criteria criteria = new Criteria("item_keywords").contains(tname);
			query.addCriteria(criteria);
		}
		ScoredPage<StaffDepa> page = solrTemplate.queryForPage("staff",query,StaffDepa.class);
		System.out.println("总记录数："+page.getTotalElements());
		List<StaffDepa> list = page.getContent();
		map.put("list", list);
		
		return "index";
	}
	 
	//查询一条用户信息
	@RequestMapping("/selectStaffKey.win")
	public String add(Integer pk,Model model){
		Staff sff = sse.selectByPrimaryKeySelective(pk);
		model.addAttribute("sff", sff);
		return "forward:/selectUpdateDepartment.win";
	}
	
	//添加用户信息
	@SuppressWarnings("unchecked")
	@RequestMapping("/insertStaff.win")
	public String add(Staff sff,Model model){
		if(sse.insert(sff)){
			redisTemplate.expire("person",10 , TimeUnit.MILLISECONDS);
			return "redirect:/selectStaff.win";
		}
		return "redirect:/selectDepartment.win";
	}
	//删除用户信息
	@ResponseBody
	@RequestMapping("/removeStaff.win")
	public Map<String,Object> remove(Integer sid,Model model){
		Map<String,Object> map = new HashMap<String, Object>();
		
		if(sse.deleteByPrimaryKey(sid)){
			map.put("bool", "删除成功！");
		}
		return map;
	}
	//更新用户信息
	@RequestMapping("/updateStaff.win")
	public String update(Staff sff,Model model){
		
		if(sse.updateByPrimaryKey(sff)){
			
			return "redirect:/selectStaff.win";
		}
		return "update";
	}
}
