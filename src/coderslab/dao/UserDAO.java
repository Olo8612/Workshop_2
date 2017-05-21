package coderslab.dao;

import java.util.List;

import coderslab.entity.User;

public interface UserDAO {
	User insert(User user);
	User update(User user, int id);
	User getUserFromId(int id);
	List<User> findAll();
}
