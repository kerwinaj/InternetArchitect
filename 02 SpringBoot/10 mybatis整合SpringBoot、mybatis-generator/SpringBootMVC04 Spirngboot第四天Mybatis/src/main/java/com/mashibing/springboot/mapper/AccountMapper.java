package com.mashibing.springboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

// 这里不用加这个注解, 因为 SpringBootMvc04Application 启动类里已经加上了 MapperScan.
//@Mapper
public interface AccountMapper {

	@Select("select * from account")
	List<Account> findAll();

	List<Account> findAll2();

	void add(Account account);
}
