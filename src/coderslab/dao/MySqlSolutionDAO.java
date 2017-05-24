package coderslab.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coderslab.entity.Solution;

public class MySqlSolutionDAO implements SolutionDao {

	private final String jdbcUrl;
	private final String password;
	private final String user;
	private static final String SELECT_ALL = "SELECT * FROM solution";
	private static final String SELECT_FROM_ID = "SELECT * FROM solution WHERE id=?";
	private static final String INSERT_INTO = "INSERT INTO solution(created, description, excersise_id, users_id) VALUES(NOW(),?,?,?)";
	private static final String UPDATE_WITH_ID = "UPDATE solution SET description=? updated=NOW() WHERE id=?";
	private static final String DELETE_WITH_ID = "DELETE FROM solution WHERE id=?";

	public MySqlSolutionDAO(String jdbcUrl, String password, String user) {
		super();
		this.jdbcUrl = jdbcUrl;
		this.password = password;
		this.user = user;
	}

	Connection createConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, user, password);
	}

	@Override
	public Solution insert(Solution solution) {
		try (Connection connection = createConnection();
				PreparedStatement insertStm = connection.prepareStatement(INSERT_INTO,
						PreparedStatement.RETURN_GENERATED_KEYS);) {
			insertStm.setString(1, solution.getDescription());
			insertStm.setInt(2, solution.getExcersiseId());
			insertStm.setInt(3, solution.getUserId());
			int result = insertStm.executeUpdate();
			if (result != 1) {
				throw new RuntimeException("Executed updated returned" + result);
			}
			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					solution.setId(generatedKeys.getInt(1));
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public Solution update(Solution solution, int id) {
		try (Connection connection = createConnection();
				PreparedStatement insertStm = connection.prepareStatement(UPDATE_WITH_ID,
						PreparedStatement.RETURN_GENERATED_KEYS);) {
			insertStm.setString(1, solution.getDescription());
			insertStm.setInt(2, solution.getExcersiseId());
			insertStm.setInt(3, solution.getUserId());
			int result = insertStm.executeUpdate();
			if (result != 1) {
				throw new RuntimeException("Executed updated returned" + result);
			}
			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					solution.setUpdatedDate(generatedKeys.getDate(3));
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Solution getSolutionFromId(int id) {
		try (Connection connection = createConnection();
				PreparedStatement getSolution = connection.prepareStatement(SELECT_FROM_ID);) {
			getSolution.setInt(1, id);
			try (ResultSet resultSet = getSolution.executeQuery();) {
				resultSet.next();
				Date createdDate = resultSet.getDate(2);
				Date updatedDate = resultSet.getDate(3);
				String description = resultSet.getString(4);
				int excersiseId = resultSet.getInt(5);
				int usersId = resultSet.getInt(6);
				return new Solution(id, createdDate, updatedDate, description, excersiseId, usersId);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Solution delete(int id) {
		try (Connection connection = createConnection();
				PreparedStatement deleteStm = connection.prepareStatement(DELETE_WITH_ID);) {
			deleteStm.setInt(1, id);
			int result = deleteStm.executeUpdate();
			if (result != 1) {
				throw new RuntimeException("Executed updated returned" + result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Solution> findAll() {
		List<Solution> solutionList = new ArrayList<>();
		try (Connection connection = createConnection();
				PreparedStatement findAllStm = connection.prepareStatement(SELECT_ALL);
				ResultSet resultSet = findAllStm.executeQuery();) {
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				Date createdDate = resultSet.getDate(2);
				Date updatedDate = resultSet.getDate(3);
				String description = resultSet.getString(4);
				int excersiseId = resultSet.getInt(5);
				int usersId = resultSet.getInt(6);
				solutionList.add(new Solution(id, createdDate, updatedDate, description, excersiseId, usersId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return solutionList;
	}

}
