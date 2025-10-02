package ru.romanov.marketplace.productservice.rest.out;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.romanov.marketplace.productservice.service.EmployeeService;
import ru.romanov.marketplace.productservice.dto.response.EmployeeResponse;
import ru.romanov.marketplace.productservice.dto.request.EmployeeUpdateRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindResponse;
import ru.romanov.marketplace.productservice.dto.request.EmployeeCreateRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindByIdRequest;
import ru.romanov.marketplace.productservice.dto.request.EmployeeFindByFilterRequest;
import ru.romanov.marketplace.productservice.dto.response.EmployeeFindByFilterResponse;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeRestController {

    private final EmployeeService employeeService;

    @PostMapping("/create")
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeCreateRequest createRequest) {
        EmployeeResponse createResponse = employeeService.createEmployee(createRequest);
        return new ResponseEntity<>(createResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find")
    public ResponseEntity<EmployeeFindResponse> find(@RequestBody EmployeeFindByIdRequest findRequest) {
        EmployeeFindResponse findResponse = employeeService.findEmployeeById(findRequest);
        return new ResponseEntity<>(findResponse, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<EmployeeFindByFilterResponse> findByFilter(
            @RequestBody EmployeeFindByFilterRequest findByFilterRequest)
    {
        EmployeeFindByFilterResponse findByFilterResponse = employeeService.findByFilter(findByFilterRequest);
        return new ResponseEntity<>(findByFilterResponse, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<EmployeeResponse> update(@RequestBody EmployeeUpdateRequest updateRequest) {
        EmployeeResponse updateResponse = employeeService.updateEmployee(updateRequest);
        return new ResponseEntity<>(updateResponse, HttpStatus.OK);
    }
}
