package blog;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

public class EmployeePreparedStatementSetter implements ItemPreparedStatementSetter<Employee> {

		@Override
	public void setValues(Employee item, PreparedStatement ps) throws SQLException {
		ps.setInt(1, item.getId());
		ps.setString(2, item.getLastName());
		ps.setString(3, item.getFirstName());	
	}
}
