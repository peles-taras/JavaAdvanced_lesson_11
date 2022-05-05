package ua.lviv.lgs.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.UserDao;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.utils.ConnectionUtils;

public class UserDaoImpl implements UserDao {

	private Connection connection;
	private PreparedStatement prepStatement;

	public UserDaoImpl() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		connection = ConnectionUtils.connect();
	}

	Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

	String create = "INSERT INTO user(`email`,`first_name`,`last_name`,`role`,`password`) VALUES (?,?,?,?,?)";
	String read = "SELECT * FROM user WHERE id =?";
	String update = "UPDATE user SET email =?, first_name =?, last_name =?, role =?, password =? WHERE id=?";
	String delete = "DELETE FROM user WHERE id =?";
	String readAll = "SELECT * FROM user";
	String readByEmail = "SELECT * FROM user WHERE email =?";

	@Override
	public User create(User user) {
		try {
			prepStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, user.getEmail());
			prepStatement.setString(2, user.getFirst_name());
			prepStatement.setString(3, user.getLast_name());
			prepStatement.setString(4, user.getRole());
			prepStatement.setString(5, user.getPassword());
			prepStatement.executeUpdate();
			ResultSet rs = prepStatement.getGeneratedKeys();
			rs.next();
			user.setId(rs.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public User read(Integer id) {
		User user = null;

		try {
			prepStatement = connection.prepareStatement(read);
			prepStatement.setInt(1, id);
			ResultSet result = prepStatement.executeQuery();
			result.next();
			Integer user_id = result.getInt("id");
			String email = result.getString("email");
			String first_name = result.getString("first_name");
			String last_name = result.getString("last_name");
			String role = result.getString("role");
			String password = result.getString("password");
			user = new User(user_id, email, first_name, last_name, role, password);
			
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public User update(User user, Integer id) {
		try {
			prepStatement = connection.prepareStatement(update);
			prepStatement.setString(1, user.getEmail());
			prepStatement.setString(2, user.getFirst_name());
			prepStatement.setString(3, user.getLast_name());
			prepStatement.setString(4, user.getRole());
			prepStatement.setString(5, user.getPassword());
			prepStatement.setInt(6, id);
			prepStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}

	@Override
	public void delete(Integer id) {
		try {
			prepStatement = connection.prepareStatement(delete);
			prepStatement.setInt(1, id);
			prepStatement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.error(e);
		}
	}

	@Override
	public List<User> readAll() {
		List<User> userList = new ArrayList<>();

		try {
			prepStatement = connection.prepareStatement(readAll);
			ResultSet result = prepStatement.executeQuery();
			while (result.next()) {
				Integer user_id = result.getInt("id");
				String email = result.getString("email");
				String first_name = result.getString("first_name");
				String last_name = result.getString("last_name");
				String role = result.getString("role");
				String password = result.getString("password");
				userList.add(new User(user_id, email, first_name, last_name, role, password));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		userList.stream().forEach(System.out::println);
		return userList;

	}

	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			prepStatement = connection.prepareStatement(readByEmail);
			prepStatement.setString(1, email);
			ResultSet result = prepStatement.executeQuery();
			result.next();
			Integer user_id = result.getInt("id");
			String first_name = result.getString("first_name");
			String last_name = result.getString("last_name");
			String role = result.getString("role");
			String password = result.getString("password");
			user = new User(user_id, email, first_name, last_name, role, password);
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return user;
	}
}
