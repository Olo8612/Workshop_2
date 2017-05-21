package coderslab.dao;

import java.util.List;

import coderslab.entity.Solution;

public interface SolutionDao {
	Solution insert(Solution user);
	Solution update(Solution user, int id);
	Solution getSolutionFromId(int id);
	Solution delete(int id);
	List<Solution> findAll();
	
}
