package om.mashibing.springboot02.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 在我们访问  http://主机名：端口号/context-path/Controller的URI/方法的URI
 * http://localhost:80/boot/user/list
 * @author Administrator
 * @Controller 加入到Spring容器管理,单例
 */
@Controller
@RequestMapping("/user") // 这一行没有的话, 就是说明: Controller的URI为空
public class MainController {

	
	/**
	 * String 类型的返回值，会找模板文件
	 * 
	 *   context/ + /user +  /list 
	 *   context/ + list
	 * @return
	 */
	@RequestMapping("/list")
	public String list(ModelMap map) {
		map.put("name", "testFromYuk");
		return "list";
	}

	/**
	 * 接受请求后, 返回文字
	 * @return
	 */
	@RequestMapping("/list2")
	@ResponseBody
	public String list2() {
		return "list2";
	}

	@RequestMapping("/list3")
	@ResponseBody
	public Map<String, String> list3() {
		Map<String, String> map = new HashMap();
		map.put("v1-key", "v1-value");
		map.put("v2-key", "v2-value");
		map.put("v3-key", "v3-value");
		System.out.println(map);
		return map;
	}
}
