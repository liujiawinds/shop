package org.liujia.shop.service;


import java.util.List;

import org.liujia.shop.dto.PurchaseRecord;
import org.liujia.shop.model.User;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public interface UserService {
	boolean valid(String name);
	public User save(User user);
	public void update(User user);
	public void delete(User user);
	public void delete(Integer id);
	
	public User findById(Integer userId);
	public User findUserByEmailAndPassword(String email,String password);
	public List<PurchaseRecord> getPurchaseRecord(User user);
}
