package az.maqa.spring.service.impl;

import az.maqa.spring.entity.Employee;
import az.maqa.spring.repository.EmployeeRepository;
import az.maqa.spring.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }
}
