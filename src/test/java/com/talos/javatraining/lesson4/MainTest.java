package com.talos.javatraining.lesson4;

import com.talos.javatraining.lesson4.exceptions.AddressNotFoundException;
import com.talos.javatraining.lesson4.model.AddressModel;
import com.talos.javatraining.lesson4.model.CountryModel;
import com.talos.javatraining.lesson4.model.UserModel;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;
import static org.apache.commons.lang3.StringUtils.*;

public class MainTest
{

	// the class to test
	private Main main = new MainImpl();

	@Test
	public void testGetLineNull(){
		String actual = main.getLine1(null);

		assertEquals(EMPTY, actual);
	}

	@Test
	public void testGetLineBlank(){
		AddressModel addressModel = createAddress(EMPTY, EMPTY, SPACE, false, false, false, false, null);

		String actual = main.getLine1(addressModel);

		assertEquals(EMPTY, actual);
	}

	@Test
	public void testGetLineOk(){
		String expected = "av 22";
		AddressModel addressModel = createAddress(EMPTY, EMPTY, expected, false, false, false, false, null);

		String actual = main.getLine1(addressModel);

		assertEquals(expected, actual);
	}

	@Test
	public void testGetFullNameNull(){
		String actual = main.getFullName(null);

		assertEquals(EMPTY, actual);
	}

	@Test
	public void testGetFullNameLastNameNull(){
		String expected = "John";
		AddressModel addressModel = createAddress(expected, null, EMPTY, false, false, false, false, null);

		String actual = main.getFullName(addressModel);

		assertEquals(expected, actual);
	}

	@Test
	public void testGetFullNameLastNameBlank(){
		String expected = "John";
		AddressModel addressModel = createAddress(expected, SPACE, EMPTY, false, false, false, false, null);

		String actual = main.getFullName(addressModel);

		assertEquals(expected, actual);
	}

	@Test
	public void testGetFullNameFirstNameNull(){
		String expected = "Doe";
		AddressModel addressModel = createAddress(null, expected, EMPTY, false, false, false, false, null);

		String actual = main.getFullName(addressModel);

		assertEquals(expected, actual);
	}

	@Test
	public void testGetFullNameFirstNameBlank(){
		String expected = "Doe";
		AddressModel addressModel = createAddress(SPACE, expected, EMPTY, false, false, false, false, null);

		String actual = main.getFullName(addressModel);

		assertEquals(expected, actual);
	}

	@Test
	public void testGetFullName(){
		AddressModel addressModel = createAddress("John", "Doe", EMPTY, false, false, false, false, null);

		String actual = main.getFullName(addressModel);

		assertEquals("John Doe", actual);
	}

	@Test
	public void testGetBillingAddressNull(){
		AddressModel addressModel = main.getBillingAddress(null);

		assertNull(addressModel);
	}

	@Test
	public void testGetBillingAddressEmpty(){
		UserModel userModel = createUser(new Date(), Collections.emptyList());

		AddressModel addressModel = main.getBillingAddress(userModel);

		assertNull(addressModel);
	}

	@Test
	public void testGetBillingAddressNoExisting(){
		Collection<AddressModel> addresses = Arrays.asList(createAddress(EMPTY, EMPTY, EMPTY, false, false, false, false, null ));
		UserModel userModel = createUser(new Date(), addresses);

		AddressModel addressModel = main.getBillingAddress(userModel);

		assertNull(addressModel);
	}

	@Test
	public void testGetBillingAddressNullBooleans(){
		Collection<AddressModel> addresses = Arrays.asList(createAddress(EMPTY, EMPTY, EMPTY, null, null, null, null, null ));
		UserModel userModel = createUser(new Date(), addresses);

		AddressModel addressModel = main.getBillingAddress(userModel);

		assertNull(addressModel);
	}

	@Test
	public void testGetBillingAddressOk(){
		AddressModel expected = createAddress(EMPTY, EMPTY, EMPTY, null, null, true, null, null );
		Collection<AddressModel> addresses = Arrays.asList(expected);
		UserModel userModel = createUser(new Date(), addresses);

		AddressModel actual = main.getBillingAddress(userModel);

		assertEquals(expected, actual);
	}

	@Test
	public void testGetLastLoginFormattedNull(){
		String actual = main.getLastLoginFormatted(null);

		assertNotNull(actual);
		assertEquals("the user has not been logged yet", actual);
	}

	@Test
	public void testGetLastLoginFormattedNullDate(){
		UserModel user = createUser(null, null);

		String actual = main.getLastLoginFormatted(user);

		assertNotNull(actual);
		assertEquals("the user has not been logged yet", actual);
	}

	@Test
	public void testGetLastLoginFormattedOk(){
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		UserModel user = createUser(date, null);
		String expected = format.format(date);

		String actual = main.getLastLoginFormatted(user);

		assertNotNull(actual);
		assertEquals(expected, actual);
	}


	@Test
	public void testGetContactCountryNull(){
		String country = main.getContactCountry(null);

		assertEquals("CA", country);
	}

	@Test
	public void testGetContactCountryEmpty(){
		UserModel userModel = createUser(null, Collections.emptyList());

		String country = main.getContactCountry(userModel);

		assertEquals("CA", country);
	}

	@Test
	public void testGetContactCountryOtherAddressKind(){
		AddressModel address = createAddress(EMPTY, EMPTY, EMPTY, true, null, null, null, null );
		Collection<AddressModel> addresses = Arrays.asList(address);
		UserModel userModel = createUser(new Date(), addresses);

		String country = main.getContactCountry(userModel);

		assertEquals("CA", country);
	}

	@Test
	public void testGetContactCountryWithoutCountry(){
		AddressModel address = createAddress(EMPTY, EMPTY, EMPTY, false, true, null, null, null);
		Collection<AddressModel> addresses = Arrays.asList(address);
		UserModel userModel = createUser(new Date(), addresses);

		String country = main.getContactCountry(userModel);

		assertEquals("CA", country);
	}

	@Test(timeout = 800)// the timeout verifies is not calling the expensive method
	public void testGetContactCountryOk(){
		String expected = "CO";
		CountryModel countryModel = createCountryModel(expected);
		AddressModel address = createAddress(EMPTY, EMPTY, EMPTY, false, true, null, null, countryModel );
		Collection<AddressModel> addresses = Arrays.asList(address);
		UserModel userModel = createUser(new Date(), addresses);

		String country = main.getContactCountry(userModel);

		assertEquals(expected, country);
	}


	@Test(expected = AddressNotFoundException.class)
	public void testGetShippingAddressEmpty() throws Exception{
		UserModel userModel = createUser(null, Collections.emptyList());

		main.getShippingAddress(userModel);
	}

	@Test(expected = AddressNotFoundException.class)
	public void testGetShippingAddressOtherAddressKind() throws Exception{
		AddressModel address = createAddress(EMPTY, EMPTY, EMPTY, null, true, null, null, null );
		Collection<AddressModel> addresses = Arrays.asList(address);
		UserModel userModel = createUser(new Date(), addresses);

		main.getShippingAddress(userModel);
	}

	@Test
	public void testGetShippingAddressOk() throws Exception{
		AddressModel expected = createAddress(EMPTY, EMPTY, EMPTY, true, false, null, null, null );
		Collection<AddressModel> addresses = Arrays.asList(expected);
		UserModel userModel = createUser(new Date(), addresses);

		AddressModel actual = main.getShippingAddress(userModel);

		assertNotNull(actual);
		assertEquals(expected, actual);
	}


	private static UserModel createUser(Date lastLogin, Collection<AddressModel> addresses)
	{
		UserModel userModel = new UserModel();
		userModel.setLastLogin(lastLogin);
		userModel.setAddresses(addresses);
		return userModel;
	}

	private static AddressModel createAddress(String firstName,
			String lastName,
			String line1,
			Boolean shippingAddress,
			Boolean contactAddress,
			Boolean billingAddress,
			Boolean deliveryAddress,
			CountryModel country)
	{
		AddressModel addressModel = new AddressModel();
		addressModel.setLastName(lastName);
		addressModel.setFirstName(firstName);
		addressModel.setLine1(line1);
		addressModel.setShippingAddress(shippingAddress);
		addressModel.setContactAddress(contactAddress);
		addressModel.setBillingAddress(billingAddress);
		addressModel.setDeliveryAddress(deliveryAddress);
		addressModel.setCountry(country);
		return addressModel;
	}

	private static CountryModel createCountryModel(String isoCode)
	{
		CountryModel countryModel = new CountryModel();
		countryModel.setIsocode(isoCode);
		return countryModel;
	}
}
