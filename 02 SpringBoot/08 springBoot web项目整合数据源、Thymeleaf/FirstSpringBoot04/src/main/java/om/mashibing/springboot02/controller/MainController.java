package om.mashibing.springboot02.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.midi.Soundbank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import om.mashibing.springboot02.domain.City;
import om.mashibing.springboot02.service.CityService;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 在我们访问  http://主机名：端口号/context-path/Controller的URI/方法的URI
 * http://localhost:80/boot/user/list
 * @author Administrator
 * @Controller 加入Spring容器管理,单例
 */
@Controller
public class MainController {

	
	/**
	 * String 类型的返回值，会找模板文件
	 * 
	 *   context/ + /user +  /list 
	 *   context/ + list
	 * @return
	 */
	
	@Autowired
	CityService citySrv;

	/**
	 * 参数这里 Model 和 ModelMap 的差别并不大, 只是 ModelMap, 提供了一些 map 的方法(比如get, put), 方便操作
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<City> list = citySrv.findAll();

		model.addAttribute("list", list);
		return "list";
	}

	@RequestMapping("/add2")
	public String add(@RequestParam("id") Integer id, @RequestParam("name") String name, Model model) {
		System.out.println("add2, id:"+id + ",name:"+name);
		String success =citySrv.add(id, name);
		System.out.println("add2, success:"+success);
		model.addAttribute("success", success);
		return "add";
	}

	@RequestMapping("/add")
	public String add(@ModelAttribute City city, Model model) {
		System.out.println(city);
		String success =citySrv.add(city);
		model.addAttribute("success", success);
		return "add";
	}
	
	@RequestMapping("/addPage")
	public String addPage() {
		return "add";
	}
}
