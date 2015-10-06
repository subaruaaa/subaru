package com.subaru.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.subaru.models.Customer;
import com.subaru.models.Discount;
import com.subaru.models.Payment;

@Component
public class SubaruDao {
	private SimpleJdbcTemplate simpleJdbcTemplate;

	@Autowired
	public SubaruDao(@Qualifier("subaruDataSource") DataSource dataSource) {
		simpleJdbcTemplate = new SimpleJdbcTemplate(dataSource);
	}

	public List<Map<String, Object>> getMd5Passwd(String employeeTel,
			String md5Passwd) {
		String sql = "SELECT * FROM passwd where employeeTel = ? AND md5Passwd = ?";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				employeeTel, md5Passwd);
		return list;
	}

	public void saveMd5Passwd(String employeeTel, String md5Passwd) {
		String sql = "INSERT INTO passwd values(?,?) ON DUPLICATE KEY UPDATE md5Passwd=?";
		simpleJdbcTemplate.update(sql, employeeTel, md5Passwd, md5Passwd);
	}

	public Map<String, Object> getCustomerByTel(String tel) {
		String sql = "SELECT * FROM customer where tel = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				tel);
		if (list.size() == 1) {
			return list.get(0);
		}
		return new HashMap<String, Object>();
	}

	public void saveCustomer(String name, String tel, String occupation,
			String identityCard, String birthday, String email, String note,
			String introducerType, String introducer) {
		String sql = "INSERT INTO customer VALUES (?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE name=?,occupation=?,"
				+ "identityCard=?,birthday=?,email=?,note=?,introducerType=?,introducer=?";
		simpleJdbcTemplate.update(sql, tel, name, occupation, identityCard,
				birthday, email, introducerType, introducer, note, name,
				occupation, identityCard, birthday, email, note,
				introducerType, introducer);
	}

	public void delCustomer(String tel) {
		String sql = "DELETE FROM customer WHERE `tel` =?";
		simpleJdbcTemplate.update(sql, tel);
	}

	public Map<String, Object> getEmployeeByTel(String tel) {
		String sql = "SELECT * FROM employee where tel = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				tel);
		if (list.size() == 1) {
			return list.get(0);
		}
		return new HashMap<String, Object>();
	}

	public void saveEmployee(String name, String tel, String email,
			String identificationCard, String birthday, String status,
			String add, String position, String store, String totalLose,
			String thisMonthLose) {
		String sql = "INSERT INTO employee VALUES (?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE name=?,"
				+ "email=?,identificationCard=?,birthday=?,status=?,`add`=?,position=?,store=?,totalLose=?,thisMonthLose=?";
		simpleJdbcTemplate.update(sql, name, tel, email, identificationCard,
				birthday, status, add, position, store, totalLose,
				thisMonthLose, name, email, identificationCard, birthday,
				status, add, position, store, totalLose, thisMonthLose);
	}

	public void delEmployee(String tel) {
		String sql = "DELETE FROM employee WHERE `tel` =?";
		simpleJdbcTemplate.update(sql, tel);
	}

	public List<Map<String, Object>> getVisitByCustomerTel(String tel) {
		String sql = "SELECT * FROM visit WHERE employeeTel = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				tel);
		return list;
	}

	// 0 全款，1按揭
	// 结单dao
	public List<Map<String, Object>> getOrder(String customerTel) {
		String sql = "SELECT * FROM `order` WHERE customerTel = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				customerTel);
		return list;
	}

	public void delOrder(int id) {
		String sql = "DELETE FROM `order` WHERE id = ?";
		simpleJdbcTemplate.update(sql, id);
	}

	public void createOrder(String orderDate, String customerTel,
			int vehicleStyleId, String vehicleIdentificationNumber,
			Float price, Float invoicePrice, Payment payment,
			Discount discount, int purchaseQuantity, String employeeTel) {
		String sql = "INSERT INTO `order` ("
				+ "orderDate,customerTel,vehicleStyleId,vehicleIdentificationNumber,price,invoicePrice,payment,discount,purchaseQuantity,employeeTel"
				+ ") values(?,?,?,?,?,?,?,?,?,?) ";
		simpleJdbcTemplate.update(sql, orderDate, customerTel, vehicleStyleId,
				vehicleIdentificationNumber, price, invoicePrice,
				payment.toString(), discount.toString(), purchaseQuantity,
				employeeTel);
	}

	public void modifyOrder(Integer id, String orderDate, String customerTel,
			int vehicleStyleId, String vehicleIdentificationNumber,
			Float price, Float invoicePrice, Payment payment,
			Discount discount, int purchaseQuantity, String employeeTel) {

		String updateSql = "UPDATE `order` SET orderDate= ?, customerTel=?,vehicleStyleId=?,"
				+ "vehicleIdentificationNumber=?,price=?,invoicePrice=?,payment=?,discount=?,purchaseQuantity=?,employeeTel=? WHERE id = ?";

		simpleJdbcTemplate.update(updateSql, orderDate, customerTel,
				vehicleStyleId, vehicleIdentificationNumber, price,
				invoicePrice, payment.toString(), discount.toString(),
				purchaseQuantity, employeeTel, id);
	}

}
