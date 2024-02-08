package com.customer.customerservice;

import com.customer.customerservice.repositorys.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "classpath:db/populateDB.sql")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = CustomerServiceApplication.class)
public abstract class BaseTest {

	@Autowired
	protected CustomerRepository customerRepository;

}
