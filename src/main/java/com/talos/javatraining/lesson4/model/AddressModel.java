package com.talos.javatraining.lesson4.model;

public class AddressModel
{
	private String firstName;
	private String lastName;
	private String line1;
	private Boolean shippingAddress;
	private Boolean contactAddress;
	private Boolean billingAddress;
	private Boolean deliveryAddress;
	private CountryModel country;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getLine1()
	{
		return line1;
	}

	public void setLine1(String line1)
	{
		this.line1 = line1;
	}

	public Boolean getShippingAddress()
	{
		return shippingAddress;
	}

	public void setShippingAddress(Boolean shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}

	public Boolean getContactAddress()
	{
		return contactAddress;
	}

	public void setContactAddress(Boolean contactAddress)
	{
		this.contactAddress = contactAddress;
	}

	public Boolean getBillingAddress()
	{
		return billingAddress;
	}

	public void setBillingAddress(Boolean billingAddress)
	{
		this.billingAddress = billingAddress;
	}

	public Boolean getDeliveryAddress()
	{
		return deliveryAddress;
	}

	public void setDeliveryAddress(Boolean deliveryAddress)
	{
		this.deliveryAddress = deliveryAddress;
	}

	public CountryModel getCountry()
	{
		return country;
	}

	public void setCountry(CountryModel country)
	{
		this.country = country;
	}

	@Override
	public String toString()
	{
		return "AddressModel{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", line1='" + line1 + '\'' +
				", shippingAddress=" + shippingAddress +
				", contactAddress=" + contactAddress +
				", billingAddress=" + billingAddress +
				", deliveryAddress=" + deliveryAddress +
				", country=" + country +
				'}';
	}
}
