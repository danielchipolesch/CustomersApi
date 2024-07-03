package br.com.cotiinformatica.controllers;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.cotiinformatica.dtos.CustomerRequest;
import br.com.cotiinformatica.dtos.CustomerResponse;
import br.com.cotiinformatica.services.CustomerService;
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired CustomerService customerService;

    @PostMapping("create")
    public ResponseEntity<CustomerResponse> post(@RequestBody CustomerRequest request) throws Exception {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(customerService.create(request));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CustomerResponse> put(@PathVariable Long id, @RequestBody CustomerRequest request) throws Exception {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.update(id, request));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<CustomerResponse> delete(@PathVariable Long id) throws Exception {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.delete(id));
    }

    @GetMapping("list")
    public ResponseEntity<List<CustomerResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) throws Exception {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.getAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerResponse> getById(@PathVariable Long id) throws Exception {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(customerService.getById(id));
    }
}