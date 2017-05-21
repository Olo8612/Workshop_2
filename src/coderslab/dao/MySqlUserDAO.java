package coderslab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import coderslab.entity.User;

public class MySqlUserDAO implements UserDAO {

	private final String jdbcUrl;
	private final String password;
	private final String user;
	private static final String FIND_ALL_QUERIES = "SELECT * FROM users";
	private static final String INSERT_USER_QUERY = "INSERT INTO users(username, surename, email, password) VALUES(?,?,?,?)";
	private static final String UPDATE_ALL_IN_ID = "UPDATE users SET username=?, surename=?, email=?, password =? WHERE id=?;";
	private static final String GET_USER_FROM_ID = "SELECT * FROM users WHERE id=?";
	private static final String DELETE_USER_FROM_ID = "DELETE FROM users WHERE id=?";

	public MySqlUserDAO(String jdbcUrl, String password, String user) {
		super();
		this.jdbcUrl = jdbcUrl;
		this.password = password;
		this.user = user;
	}

	Connection createConnection() throws SQLException {
		return DriverManager.getConnection(jdbcUrl, user, password);
	}

	@Override
	public User insert(User user) {
		try (Connection connection = createConnection();
				PreparedStatement insertStm = connection.prepareStatement(INSERT_USER_QUERY,
						PreparedStatement.RETURN_GENERATED_KEYS);) {
			insertStm.setString(1, user.getName());
			insertStm.setString(2, user.getSurname());
			insertStm.setString(3, user.getEmail());
			insertStm.setString(4, user.getPassword());
			int result = insertStm.executeUpdate();
			if (result != 1) {
				throw new RuntimeException("Executed updated returned" + result);
			}
			try (ResultSet generatedKeys = insertStm.getGeneratedKeys()) {
				if (generatedKeys.first()) {
					user.setId(generatedKeys.getInt(1));
				}

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> userList = new ArrayList<>();
		try (Connection connection = createConnection();
				PreparedStatement findAllStm = connection.prepareStatement(FIND_ALL_QUERIES);
				ResultSet resultSet = findAllStm.executeQuery();) {
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String username = resultSet.getString(2);
				String surname = resultSet.getString(3);
				String email = resultSet.getString(4);
				String password = resultSet.getString(5);
				userList.add(new User(id, username, surname, email, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public User update(User user, int id) {
		try (Connection connection = createConnection();
				PreparedStatement updateStm = connection.prepareStatement(UPDATE_ALL_IN_ID);) {
			updateStm.setString(1, user.getName());
			updateStm.setString(2, user.getSurname());
			updateStm.setString(3, user.getEmail());
			updateStm.setString(4, user.getPassword());
			updateStm.setInt(5, id);
			int result = updateStm.executeUpdate();
			if (result != 1) {
				throw new RuntimeException("Executed updated returned" + result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User getUserFromId(int id) {
		try (Connection connection = createConnection();
				PreparedStatement getFromId = connection.prepareStatement(GET_USER_FROM_ID);) {
			getFromId.setInt(1, id);
			try (ResultSet resultSet = getFromId.executeQuery()) {
				resultSet.next();
				int idUser = resultSet.getInt(1);
				String username = resultSet.getString(2);
				String surname = resultSet.getString(3);
				String email = resultSet.getString(4);
				String password = resultSet.getString(5);
				System.out.println(String.format("%d, %s, %s, %s, %s", idUser, username, surname, email, password));
				return new User(idUser, username, surname, email, password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User delete(int id) {
		try (Connection connection = createConnection();
				PreparedStatement deleteStm = connection.prepareStatement(DELETE_USER_FROM_ID);) {
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
}
