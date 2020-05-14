package om.mashibing.springboot.service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import om.mashibing.springboot.RespStat;
import om.mashibing.springboot.entity.Account;
import om.mashibing.springboot.repository.AccountRepository;


@Service
public class AccountService {

	@Autowired
	AccountRepository accRep;
	
	public RespStat save(Account account) {
		try {
			// 返回实体类，id也会自动回填。
			Account entity = accRep.save(account);
			System.out.println("[save]entity:" + ToStringBuilder.reflectionToString(entity));
		} catch (Exception e) {
			return new RespStat(500, "发生错误", e.getMessage());
		}
		return RespStat.build(200);
	}

	public List<Account> findAll() {
		// 接口自带方法, 查询所有数据
		List<Account> accountList = accRep.findAll();
		System.out.println("[findAll]1accountList:" + ToStringBuilder.reflectionToString(accountList));

		// 自定义方法
		accountList = accRep.findByIdBetween(1, 6);
		System.out.println("[findAll]2accountList:" + ToStringBuilder.reflectionToString(accountList));

		return accountList;
	}

	public Account findById(int id) {
		// 接口自带方法
		Optional<Account> accountOptional = accRep.findById(id);
		System.out.println("[findById]accountOptional:" + ToStringBuilder.reflectionToString(accountOptional));
		return accountOptional.get();
	}

	public Object findxxx() {
		Account account = accRep.findByLoginNameAndPassword("yimingge", "123");
		System.out.println("[findxxx]account:" + Objects.toString(account));

		List<Account> findbyxx = accRep.findbyxx2(1);
		System.out.println("[findxxx]findbyxx:" + ToStringBuilder.reflectionToString(findbyxx));

		return findbyxx;
	}

}
