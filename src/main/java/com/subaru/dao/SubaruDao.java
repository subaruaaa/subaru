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
import com.subaru.models.Employee;

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

	public void updatePasswdTel(String employeeTelOld, String employeeTelNew) {
		String sql = "UPDATE passwd SET employeeTel = ? WHERE employeeTel = ?";
		simpleJdbcTemplate.update(sql, employeeTelNew, employeeTelOld);
	}

	public Map<String, Object> getCustomerByTel(String customerTel) {
		String sql = "SELECT * FROM customer where customerTel = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				customerTel);
		if (list.size() == 1) {
			return list.get(0);
		}
		return new HashMap<String, Object>();
	}

	public Map<String, Object> getCustomerById(Integer customerId) {
		String sql = "SELECT * FROM customer where customerId = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				customerId);
		if (list.size() == 1) {
			return list.get(0);
		}
		return new HashMap<String, Object>();
	}

	public void modifyCustomer(Integer customerId, String name,
			String customerTel, String occupation, String identityCard,
			String birthday, String email, String note,
			Integer introducerTypeId, String introducer) {
		String sql = "UPDATE customer SET customerTel=?, name=?, occupation=?, identityCard=?, birthday=?, email=?, introducerTypeId=?, introducer=?, note=? WHERE customerId = ?";
		simpleJdbcTemplate.update(sql, customerTel, name, occupation,
				identityCard, birthday, email, introducerTypeId, introducer,
				note, customerId);
	}

	public void createCustomer(String name, String customerTel,
			String occupation, String identityCard, String birthday,
			String email, String note, Integer introducerTypeId,
			String introducer) {
		String sql = "INSERT INTO customer (customerTel,name,occupation,identityCard,"
				+ "birthday,email,introducerTypeId,introducer,note)VALUES (?,?,?,?,?,?,?,?,?)";
		simpleJdbcTemplate.update(sql, customerTel, name, occupation,
				identityCard, birthday, email, introducerTypeId, introducer,
				note);
	}

	public void delCustomer(String customerTel) {
		String sql = "DELETE FROM customer WHERE `customerTel` =?";
		simpleJdbcTemplate.update(sql, customerTel);
	}

	public void delCustomer(Integer customerId) {
		String sql = "DELETE FROM customer WHERE `customerId` =?";
		simpleJdbcTemplate.update(sql, customerId);
	}

	public Map<String, Object> getEmployeeByTel(String employeeTel) {
		String sql = "SELECT * FROM employee where employeeTel = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				employeeTel);
		if (list.size() == 1) {
			return list.get(0);
		}
		return new HashMap<String, Object>();
	}

	public Map<String, Object> getEmployeeById(Integer employeeId) {
		String sql = "SELECT * FROM employee where employeeId = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				employeeId);
		if (list.size() == 1) {
			return list.get(0);
		}
		return new HashMap<String, Object>();
	}

	public void saveEmployee(Integer employeeId, String name,
			String employeeTel, String email, String identityCard,
			String birthday, Integer statusId, String add, Integer positionId,
			Integer storeId, String totalLose, String thisMonthLose) {
		String sql = "INSERT INTO employee (employeeId, name, employeeTel, email,identityCard, birthday,statusId,`add`, positionId,storeId,totalLose, thisMonthLose)"
				+ "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE name=?,"
				+ "email=?,identityCard=?,birthday=?,statusId=?,`add`=?,positionId=?,storeId=?,totalLose=?,thisMonthLose=?,employeeTel=?";
		simpleJdbcTemplate.update(sql, employeeId, name, employeeTel, email,
				identityCard, birthday, statusId, add, positionId, storeId,
				totalLose, thisMonthLose, name, email, identityCard, birthday,
				statusId, add, positionId, storeId, totalLose, thisMonthLose,
				employeeTel);
	}

	public void delEmployee(String employeeTel) {
		String sql = "DELETE FROM employee WHERE `employeeTel` =?";
		simpleJdbcTemplate.update(sql, employeeTel);
	}

	public List<Map<String, Object>> getVisitByCustomerId(Integer customerId) {
		String sql = "SELECT * FROM visit WHERE customerId = ? ORDER BY visitTime DESC";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				customerId);
		return list;
	}

	public List<Map<String, Object>> getVisitByEmployeeId(Integer employeeId) {
		String sql = "SELECT * FROM visit WHERE employeeId = ? ORDER BY visitTime DESC";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				employeeId);
		return list;
	}

	public List<Map<String, Object>> getOrdersByEmployeeId(Integer employeeId) {
		String sql = "SELECT * FROM `order` WHERE employeeId = ? ORDER BY orderDate DESC";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				employeeId);
		return list;
	}

	public List<Map<String, Object>> getVisitById(Integer visitId) {
		String sql = "SELECT * FROM visit WHERE visitId = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				visitId);
		return list;
	}

	// 0 全款，1按揭
	// 结单dao
	public List<Map<String, Object>> getOrdersByCustomerId(Integer customerId) {
		String sql = "SELECT * FROM `order` WHERE customerId = ? order by orderDate desc";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				customerId);
		return list;
	}

	public List<Map<String, Object>> getByOrderId(Integer orderId) {
		String sql = "SELECT * FROM `order` WHERE orderId = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				orderId);
		return list;
	}

	public List<Map<String, Object>> getOrdersByEmployee(Integer employeeId) {
		String sql = "SELECT * FROM `order` WHERE employeeId = ? ";
		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql,
				employeeId);
		return list;
	}

	public void delOrder(int orderId) {
		String sql = "DELETE FROM `order` WHERE orderId = ?";
		simpleJdbcTemplate.update(sql, orderId);
	}

	public void addOrder(String orderDate, Integer customerId,
			int vehicleStyleId, String vehicleIdentificationNumber,
			Float price, Float invoicePrice, Integer discountId, String quota,
			int purchaseQuantity, Integer employeeId, Integer paymentTypeId,
			Integer mortgageBankId, Float mortgageAmount) {
		String sql = "INSERT INTO `order` ("
				+ "orderDate,customerId,vehicleStyleId,vehicleIdentificationNumber,price,invoicePrice,"
				+ "purchaseQuantity,employeeId,paymentTypeId,mortgageBankId,mortgageAmount"
				+ ") values(?,?,?,?,?,?,?,?,?,?,?) ";
		simpleJdbcTemplate.update(sql, orderDate, customerId, vehicleStyleId,
				vehicleIdentificationNumber, price, invoicePrice,
				purchaseQuantity, employeeId, paymentTypeId, mortgageBankId,
				mortgageAmount);
	}

	// `orderId` int(10) NOT NULL AUTO_INCREMENT,
	// `orderDate` varchar(255) NOT NULL,
	// `customerId` varchar(255) NOT NULL,
	// `vehicleStyleId` int(10) NOT NULL,
	// `vehicleIdentificationNumber` varchar(255) NOT NULL,
	// `price` float NOT NULL,
	// `invoicePrice` float NOT NULL,
	// `payment` varchar(255) NOT NULL,
	// `discountId` varchar(255) NOT NULL DEFAULT '0',
	// `purchaseQuantity` int(10) NOT NULL,
	// `employeeId` varchar(255) NOT NULL,
	// `quota` varchar(255) NOT NULL DEFAULT '',
	// `paymentTypeId` int(10) NOT NULL DEFAULT '0',
	// `mortgageBankId` int(10) NOT NULL DEFAULT '0',
	// `mortgageAmount` varchar(255) NOT NULL DEFAULT '',

	public void modifyOrder(Integer orderId, Integer customerId,
			int vehicleStyleId, String vehicleIdentificationNumber,
			Float price, Float invoicePrice, Integer discountId, String quota,
			int purchaseQuantity, Integer employeeId, Integer paymentTypeId,
			Integer mortgageBankId, Float mortgageAmount) {

		String updateSql = "UPDATE `order` SET customerId=?,vehicleStyleId=?,"
				+ "vehicleIdentificationNumber=?,price=?,invoicePrice=?, discountId=?, quota=?,purchaseQuantity=?,"
				+ "employeeId=?,paymentTypeId=?,mortgageBankId=?,mortgageAmount=?  WHERE orderId = ?";

		simpleJdbcTemplate.update(updateSql, customerId, vehicleStyleId,
				vehicleIdentificationNumber, price, invoicePrice, discountId,
				quota, purchaseQuantity, employeeId, paymentTypeId,
				mortgageBankId, mortgageAmount, orderId);
	}

	public void addVisit(Integer intentionVehicleStyleId, Customer customer,
			Employee employee, Float price, String discountId, String quota,
			String expectedDisCountId, String expectedQuota, String note,
			String visitTime, Integer installationId) {
		String sql = "INSERT INTO `visit` (customerId,intentionVehicleStyleId,employeeId,price,discountId,quota,expectedDisCountId,expectedQuota,note,visitTime,installationId) values"
				+ " (?,?,?,?,?,?,?,?,?,?,?)";
		simpleJdbcTemplate.update(sql, customer.getCusomerId(),
				intentionVehicleStyleId, employee.getEmployeeId(), price,
				discountId, quota, expectedDisCountId, expectedQuota, note,
				visitTime, installationId);
	}

	public void modifyVisit(Integer visitId, Integer intentionVehicleStyleId,
			Customer customer, Employee employee, Float price,
			String discountId, String quota, String expectedDisCountId,
			String expectedQuota, String note, String visitTime,
			Integer installationId) {

		String sql = "UPDATE visit SET intentionVehicleStyleId=?, customerId=?, employeeId=?, price=?,discountId=?,quota=?,expectedDisCountId=?,expectedQuota=?,note=?, visitTIme=?, installationId=? WHERE visitId = ?";
		simpleJdbcTemplate.update(sql, intentionVehicleStyleId,
				customer.getCusomerId(), employee.getEmployeeId(), price,
				discountId, quota, expectedDisCountId, expectedQuota, note,
				visitTime, installationId, visitId);
	}

	public void delVisit(Integer visitId) {
		String sql = "DELETE FROM `visit` WHERE visitId = ? ";
		simpleJdbcTemplate.update(sql, visitId);
	}

	public List<Map<String, Object>> getAllStores() {
		String sql = "SELECT * FROM store";

		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql);
		return list;
	}

	public List<Map<String, Object>> getAllPosition() {
		String sql = "SELECT * FROM position";

		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql);
		return list;
	}

	public List<Map<String, Object>> getAllIntroducerType() {
		String sql = "SELECT * FROM introducerType";

		List<Map<String, Object>> list = simpleJdbcTemplate.queryForList(sql);
		return list;
	}

	public List<Map<String, Object>> selectAll(String tableName) {
		String sql = "SELECT * FROM " + tableName;
		return simpleJdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> searchCustomerByCustomerTelList(
			String customerTel) {
		String sql = "SELECT * FROM customer WHERE customerTel like ?";
		return simpleJdbcTemplate.queryForList(sql, customerTel + "%");
	}
}
