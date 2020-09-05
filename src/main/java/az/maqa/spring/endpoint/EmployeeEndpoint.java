package az.maqa.spring.endpoint;

import az.maqa.spring.entity.Employee;
import az.maqa.spring.gs_ws.EmployeeType;
import az.maqa.spring.gs_ws.GetEmployeeByIdRequest;
import az.maqa.spring.gs_ws.GetEmployeeByIdResponse;
import az.maqa.spring.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class EmployeeEndpoint {

    public static final String NAMESPACE_URI = "http://www.example.com/soap-ws";

    @Autowired
    private EmployeeService employeeService;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetEmployeeByIdRequest")
    @ResponsePayload
    public GetEmployeeByIdResponse findEmployeeById(@RequestPayload GetEmployeeByIdRequest employeeByIdRequest) {
        GetEmployeeByIdResponse response = new GetEmployeeByIdResponse();

        Employee employee = employeeService.getEmployeeById(employeeByIdRequest.getId());

        EmployeeType employeeType = new EmployeeType();

        BeanUtils.copyProperties(employee, employeeType);

        response.setEmployeeType(employeeType);

        return response;
    }

}

