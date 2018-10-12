package com.talos.javatraining.lesson4.model;

import java.util.Collection;
import java.util.Date;


public class UserModel
{
	private Collection<AddressModel> addresses;
	private Date lastLogin;

	public Collection<AddressModel> getAddresses()
	{
		return addresses;
	}

	public void setAddresses(Collection<AddressModel> addresses)
	{
		this.addresses = addresses;
	}

	public Date getLastLogin()
	{
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin)
	{
		this.lastLogin = lastLogin;
	}

	@Override
	public String toString()
	{
		return "UserModel{" +
				"addresses=" + addresses +
				", lastLogin=" + lastLogin +
				'}';
	}
}
