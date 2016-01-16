package blog;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class EmployeeFieldSetMapper implements FieldSetMapper<Employee>{

	@Override
	public Employee mapFieldSet(FieldSet set) throws BindException {
		Employee employee = new Employee();
		employee.setId(set.readInt("id"));
		employee.setFirstName(set.readString("firstName"));
		employee.setLastName(set.readString("lastName"));
		return employee;
	}

}
