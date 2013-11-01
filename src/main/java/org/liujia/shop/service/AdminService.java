package org.liujia.shop.service;


import org.liujia.shop.model.Admin;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface AdminService {
	public Admin save(Admin admin);
	public void update(Admin admin);
	public void delete(Admin admin);
	public void deleteById(Integer id);
	public Admin login(String name,String password);
	
}
