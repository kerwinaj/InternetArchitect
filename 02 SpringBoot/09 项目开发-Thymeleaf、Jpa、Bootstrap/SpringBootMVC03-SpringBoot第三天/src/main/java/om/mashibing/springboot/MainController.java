package om.mashibing.springboot;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import om.mashibing.springboot.entity.Account;
import om.mashibing.springboot.service.AccountService;

@Controller
public class MainController {

	@Autowired
	AccountService accSrv;
	
	@RequestMapping("/list")
	@ResponseBody
	public Object list () {
		List<Account> list = accSrv.findAll();

		Object account = accSrv.findById(1);
		// HQL select account0_.id as id1_0_0_, account0_.age as age2_0_0_, account0_.location as location3_0_0_, account0_.login_name as login_na4_0_0_, account0_.nick_name as nick_nam5_0_0_, account0_.password as password6_0_0_ from account account0_ where account0_.id=?

		account = accSrv.findxxx();
		return account;
	}
	
	/**
	 * 区分 get 和post 请求
	 * 
	 * get : 展示页面
	 * post：收集数据
	 * @return
	 */
	@GetMapping("/register")
	public String register (Model map) {
		System.out.println("======get=====");
		return "register";
	}

	/**
	 * @RequestBody Account account : 是要求, 传输过来的是json串.
	 * Account account : 就普通传递过来的. formData: loginName=admin&password=admin&nickName=admin&age=100
	 *
	 * 这里还有个要注意是, 页面上需要4个字段都填上, 不然age没填的话会报错.
	 * @param request
	 * @param account
	 * @return
	 */
	@PostMapping("/register")
	public String registerP (HttpServletRequest request, Account account) {
		System.out.println("======post=====");
		// request.getAttribute是页面和页面之间跳转时使用
		String loginName = (String)request.getParameter("loginName");
		System.out.println("loginName:"+loginName);
		System.out.println("account:"+ToStringBuilder.reflectionToString(account));
		RespStat stat =  accSrv.save(account);
		request.setAttribute("stat", stat);
		return "register";
	}
	
	@RequestMapping("/login")
	public String login () {
		return "login";
	}
}
