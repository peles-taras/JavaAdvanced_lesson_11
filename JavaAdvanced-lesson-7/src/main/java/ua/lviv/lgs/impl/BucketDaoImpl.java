package ua.lviv.lgs.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.lviv.lgs.dao.BucketDao;
import ua.lviv.lgs.domain.Bucket;
import ua.lviv.lgs.utils.ConnectionUtils;

public class BucketDaoImpl implements BucketDao {

	private Connection connection;
	private PreparedStatement prepStatement;

	public BucketDaoImpl() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		connection = ConnectionUtils.connect();
	}

	Logger LOGGER = Logger.getLogger(BucketDaoImpl.class);

	String create = "INSERT INTO bucket(`user_id`,`product_id`,`purchase_date`) VALUES (?,?,?)";
	String read = "SELECT * FROM bucket WHERE id =?";
	String delete = "DELETE FROM bucket WHERE id =?";
	String readAll = "SELECT * FROM bucket";

	@Override
	public Bucket create(Bucket bucket) {
		try {
			prepStatement = connection.prepareStatement(create);
			prepStatement.setInt(1, bucket.getUser_id());
			prepStatement.setInt(2, bucket.getProduct_id());
			prepStatement.setDate(3, new Date(bucket.getPurchase_date().getTime()));
			prepStatement.executeUpdate();
			throw new SQLException();
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return null;
	}

	@Override
	public Bucket read(Integer id) {
		Bucket bucket = null;

		try {
			prepStatement = connection.prepareStatement(read);
			prepStatement.setInt(1, id);
			ResultSet result = prepStatement.executeQuery();
			result.next();
			Integer bucket_id = result.getInt("id");
			Integer user_id = result.getInt("user_id");
			Integer product_id = result.getInt("product_id");
			Date purchase_date = result.getDate("purchase_date");
			bucket = new Bucket(bucket_id, user_id, product_id, purchase_date);

		} catch (SQLException e) {
			LOGGER.error(e);
		}
		return bucket;
	}

	@Override
	public Bucket update(Bucket bucket, Integer id) {
		throw new IllegalStateException("update method for Bucket is disabled");
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
	public List<Bucket> readAll() {
		List<Bucket> bucketList = new ArrayList<>();
		try {
			prepStatement = connection.prepareStatement(readAll);
			ResultSet result = prepStatement.executeQuery();
			while (result.next()) {
				Integer bucket_id = result.getInt("id");
				Integer user_id = result.getInt("user_id");
				Integer product_id = result.getInt("product_id");
				Date purchase_date = result.getDate("purchase_date");
				bucketList.add(new Bucket(bucket_id, user_id, product_id, purchase_date));
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}
		bucketList.stream().forEach(System.out::println);
		return bucketList;
	}

}
