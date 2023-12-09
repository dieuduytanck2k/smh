package com.shopme.checkout.paypal;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class PayPalApiTests {
	private static final String BASE_URL = "https://api.sandbox.paypal.com";
	private static final String GET_ORDER_API = "/v2/checkout/orders/";
	private static final String CLIENT_ID = "AWyfeNIv2OGcZghi93ShUrYd0MN_XJks74ybOH6M_IpEQTeuy2M67peIttp1zXC1-vUkX0Bel19vxHWl";
	private static final String CLIENT_SECRET = "ECg1eBGKmkhwe7lgFgIHb63r5p0dJ_UZGrIeGzHsf6RzClPOX8vFOgT1zjpIhCNHezNFlJE12zfgZ_97";
	
	@Test
	public void testGetOrderDetailsFirst() {
		String orderId = "4NB87945LN047793C";
		String requestURL = BASE_URL + GET_ORDER_API + orderId;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Accept-Language", "en_US");
		headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response = restTemplate.exchange(
				requestURL, HttpMethod.GET, request, String.class);
		
		System.out.println(response);
		
	}
	
	@Test
	public void testGetOrderDetails() {
		String orderId = "4NB87945LN047793C";
		String requestURL = BASE_URL + GET_ORDER_API + orderId;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("Accept-Language", "en_US");
		headers.setBasicAuth(CLIENT_ID, CLIENT_SECRET);
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<PayPalOrderResponse> response = restTemplate.exchange(
				requestURL, HttpMethod.GET, request, PayPalOrderResponse.class);
		PayPalOrderResponse orderResponse = response.getBody();
		
		System.out.println("response: " + response);
		System.out.println("response.getBody(): " + response.getBody());
		System.out.println("orderResponse: " + orderResponse);
		//System.out.println("Order ID: " + orderResponse.getId());
		//System.out.println("Validated: " + orderResponse.validate(orderId));
		
	}
}
