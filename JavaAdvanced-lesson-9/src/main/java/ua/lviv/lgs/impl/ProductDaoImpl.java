package ua.lviv.lgs.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.ProductDao;
import ua.lviv.lgs.domain.Product;
import ua.lviv.lgs.utils.ConnectionUtils;

public class ProductDaoImpl implements ProductDao {

	private Connection connection;
	private PreparedStatement prepStatement;

	public ProductDaoImpl() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		connection = ConnectionUtils.connect();
	}

	Logger LOGGER = Logger.getLogger(ProductDaoImpl.class);
	
	String create = "INSERT INTO product(`name`,`description`,`price`) VALUES (?,?,?)";
	String read = "SELECT * FROM product WHERE id =?";
	String update = "UPDATE product SET name =?, description =?, price =? WHERE id=?";
	String delete = "DELETE FROM product WHERE id =?";
	String readAll = "SELECT * FROM product";

	@Override
	public Product create(Product product) {
		try {
			prepStatement = connection.prepareStatement(create, Statement.RETURN_GENERATED_KEYS);
			prepStatement.setString(1, product.getName());
			prepStatement.setString(2, product.getDescription());
			prepStatement.setDouble(3, product.getPrice());
			prepStatement.executeUpdate();
			ResultSet rs = prepStatement.getGeneratedKeys();
			rs.next();
			product.setId(rs.getInt(1));
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return product;
	}

	@Override
	public Product read(Integer id) {
		Product product = null;
		try {
			prepStatement = connection.prepareStatement(read);
			prepStatement.setInt(1, id);
			ResultSet result = prepStatement.executeQuery();
			result.next();
			Integer product_id = result.getInt("id");
			String name = result.getString("name");
			String description = result.getString("description");
			Double price = result.getDouble("price");
			product = new Product(product_id, name, description, price);

		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return product;
	}

	@Override
	public Product update(Product product, Integer id) {
		try {
			prepStatement = connection.prepareStatement(update);
			prepStatement.setString(1, product.getName());
			prepStatement.setString(2, product.getDescription());
			prepStatement.setDouble(3, product.getPrice());
			prepStatement.setInt(4, id);
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return product;
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
	public List<Product> readAll() {
		List<Product> productList = new ArrayList<>();
		try {
			prepStatement = connection.prepareStatement(readAll);
			ResultSet result = prepStatement.executeQuery();
			while (result.next()) {
				Integer product_id = result.getInt("id");
				String name = result.getString("name");
				String description = result.getString("description");
				Double price = result.getDouble("price");
				productList.add(new Product(product_id, name, description, price));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return productList;
	}

}
