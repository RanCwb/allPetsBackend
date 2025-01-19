package com.pets.all_pets.services;
import com.pets.all_pets.DTO.CustomerDTO;
import com.pets.all_pets.Mapper.ProjectMAPPER;
import com.pets.all_pets.config.JwtUtil;
import com.pets.all_pets.exceptions.CustomerValidationException;
import com.pets.all_pets.models.CustomerModel;
import com.pets.all_pets.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CustomerService  {

    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);


    @Autowired
    private CustomerRepository customerRepository;

    public void  createCustomer(CustomerDTO customerDTO) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (customerDTO.getName() == null || customerDTO.getName().isEmpty() ) {
            throw new CustomerValidationException("Name is required");
        }

        if (!isEmailAlreadyRegistered(customerDTO.getEmail())) {
            CustomerModel customer = new CustomerModel();
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            customer.setPassword(passwordEncoder.encode(customerDTO.getPassword()));
            customer.setPhone(customerDTO.getPhone());

            String token = JwtUtil.generateToken(customer.getEmail());

            customer.setToken(token);
            customerDTO.setToken(token);
            customerRepository.save(customer);
            customerDTO.setIdCustomer(customer.getIdCustomer());
            LOG.info("Customer created successfully");
        }else {
            LOG.error("Email already registered");
            throw new CustomerValidationException("Email already registered");
        }

    }

    public CustomerDTO loginCustomer(String email, String password) {
        CustomerModel customer = customerRepository.findByEmail(email);
        if (customer != null) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(password, customer.getPassword())) {
                return ProjectMAPPER.parseObject(customer, CustomerDTO.class);
            } else {
                throw new CustomerValidationException("Invalid password");
            }
        } else {
            throw new CustomerValidationException("Invalid email");
        }
    }


    public Page<CustomerDTO> getAllCustomers(int page, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0.");
        }
        Pageable pageable = PageRequest.of(page, size);
        Page<CustomerModel> customerModels = customerRepository.findAll(pageable);

        if (page >= customerModels.getTotalPages()) {
            throw new IllegalArgumentException("Page number exceeds total pages available.");
        }
        return customerModels.map(customer -> ProjectMAPPER.parseObject(customer, CustomerDTO.class));
    }


    public CustomerDTO updateCustomer(Integer id, CustomerDTO customer) {
    Optional<CustomerModel> customerModel = customerRepository.findById(id);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (customerModel.isPresent()) {
            CustomerModel updatedCustomer = customerModel.get();
            updatedCustomer.setName(customer.getName());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setPassword(passwordEncoder.encode(customer.getPassword()));
            updatedCustomer.setPhone(customer.getPhone());
            customerRepository.save(updatedCustomer);
            return ProjectMAPPER.parseObject(updatedCustomer, CustomerDTO.class);
        } else {
            throw new CustomerValidationException("Customer not found for update");
        }
    }

    public CustomerDTO getCustomerById(Integer id, CustomerModel customer) {
       Optional<CustomerModel> customerModel = customerRepository.findById(id);

       if (customerModel.isPresent()) {
           return ProjectMAPPER.parseObject(customerModel.get(), CustomerDTO.class);
       } else {
           throw new CustomerValidationException("Customer not found");
       }

   }

    public CustomerDTO deleteCustomerById(Integer id, CustomerModel customer) {
        Optional<CustomerModel> customerModel = customerRepository.findById(id);
        if (customerModel.isPresent()) {
            customerRepository.delete(customerModel.get());
            LOG.info("Customer deleted successfully");
            return ProjectMAPPER.parseObject(customerModel.get(), CustomerDTO.class);
        } else {
            throw new CustomerValidationException("Customer not found for deletion");
        }
    }

    public boolean isEmailAlreadyRegistered(String email) {
        return customerRepository.existsByEmail(email);
    }




}
