package com.shopme.shoppingcart;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.CartItem;
import com.shopme.common.entity.Customer;
import com.shopme.common.entity.product.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class CartItemRepositoryTests {
	
	@Autowired private CartItemRepository repo;
	@Autowired private TestEntityManager entityManager;
	
	@Test
	public void testSaveItem() {
		Integer customerId = 1;
		Integer productId = 1;
		
		Customer customer = entityManager.find(Customer.class, customerId);
		Product product = entityManager.find(Product.class, productId);
		
		CartItem newItem = new CartItem();
		newItem.setCustomer(customer);
		newItem.setProduct(product);
		newItem.setQuantity(1);
		
		CartItem savedItem = repo.save(newItem);
		
		System.out.println(savedItem);
		
	}
	
	@Test
	public void testSave2Item() {
		Integer customerId = 10;
		Integer productId = 10;
		
		Customer customer = entityManager.find(Customer.class, customerId);
		Product product = entityManager.find(Product.class, productId);
		
		CartItem newItem1 = new CartItem();
		newItem1.setCustomer(customer);
		newItem1.setProduct(product);
		newItem1.setQuantity(2);
		
		CartItem newItem2 = new CartItem();
		newItem2.setCustomer(new Customer(customerId));
		newItem2.setProduct(new Product(8));
		newItem2.setQuantity(3);
		
		Iterable<CartItem> saveAll = repo.saveAll(List.of(newItem1, newItem2));
		
		saveAll.forEach(item -> System.out.println(item));
		
	}
	
	@Test
	public void findByCustomer() {
		List<CartItem> customers = repo.findByCustomer(new Customer(10));
		customers.forEach(customer -> System.out.println(customer));
	}
	
	@Test
	public void findByCustomerAndProduct() {
		CartItem item = repo.findByCustomerAndProduct(new Customer(1), new Product(1));
		System.out.println(item);
	}
	
	@Test
	public void update() {
		repo.updateQuantity(4, 1, 1);
	}
	
	@Test
	public void delete() {
		repo.deleteByCustomerAndProduct(10, 8);
	}
}
