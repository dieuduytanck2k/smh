package com.shopme.shipping;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.shopme.common.entity.Country;
import com.shopme.common.entity.Customer;
import com.shopme.common.entity.ShippingRate;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class ShippingRateRepositoryTests {
	@Autowired private ShippingRateRepository repo;
	
	@Autowired private TestEntityManager entityManager;
	
	@Test
	public void test() {
		Country country = new Country(234);
		ShippingRate shippingRate = repo.findByCountryAndState(country, "New York");
		System.out.println(shippingRate);
	}
	
	@Test
	public void get() {
		Customer customer = entityManager.find(Customer.class, 41);
		System.out.println(customer);
		ShippingRate shippingRate = repo.findByCountryAndState(customer.getCountry(), customer.getState());
		System.out.println(shippingRate);
	}
}
