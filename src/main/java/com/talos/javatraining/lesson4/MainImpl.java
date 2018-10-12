package com.talos.javatraining.lesson4;


import com.talos.javatraining.lesson4.exceptions.AddressNotFoundException;
import com.talos.javatraining.lesson4.model.AddressModel;
import com.talos.javatraining.lesson4.model.UserModel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.function.Predicate;


public class MainImpl implements Main
{

	@Override
	public String getLine1(AddressModel addressModel)
	{
		String result = StringUtils.EMPTY;
		if (addressModel != null)
		{
			if (StringUtils.isNotBlank(addressModel.getLine1()))
			{
				result = addressModel.getLine1();
			}
		}
		return result;
	}

	@Override
	public String getFullName(AddressModel addressModel)
	{
		StringBuilder stringBuilder = new StringBuilder();
		if (addressModel != null)
		{
			if (StringUtils.isNotBlank(addressModel.getFirstName()))
			{
				stringBuilder.append(addressModel.getFirstName());
			}
			if (StringUtils.isNotBlank(addressModel.getLastName()))
			{
				if (stringBuilder.length() != 0)
				{
					stringBuilder.append(StringUtils.SPACE);
				}
				stringBuilder.append(addressModel.getLastName());
			}
		}
		return stringBuilder.toString();
	}

	@Override
	public AddressModel getBillingAddress(UserModel userModel)
	{
		AddressModel result = null;
		if (userModel != null)
		{
			if (CollectionUtils.isNotEmpty(userModel.getAddresses()))
			{
				result = getAddress(userModel.getAddresses(), a -> BooleanUtils.isTrue(a.getBillingAddress()));
			}
		}
		return result;
	}

	@Override
	public String getLastLoginFormatted(UserModel userModel)
	{
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String result = "the user has not been logged yet";
		if (userModel != null && userModel.getLastLogin() != null)
		{
			result = format.format(userModel.getLastLogin());
		}
		return result;
	}

	@Override
	public String getContactCountry(UserModel userModel)
	{
		String contactAddressIsoCode = null;
		if (userModel != null)
		{
			if (CollectionUtils.isNotEmpty(userModel.getAddresses()))
			{
				AddressModel contactAddress = getAddress(userModel.getAddresses(), a -> BooleanUtils.isTrue(a.getContactAddress()));
				if (contactAddress != null && contactAddress.getCountry() != null)
				{
					contactAddressIsoCode = contactAddress.getCountry().getIsocode();
				}
			}
		}
		if (contactAddressIsoCode == null)
		{
			contactAddressIsoCode = inferCountry();
		}
		return contactAddressIsoCode;
	}

	@Override
	public AddressModel getShippingAddress(UserModel userModel) throws AddressNotFoundException
	{
		AddressModel addressModel = null;
		if (CollectionUtils.isNotEmpty(userModel.getAddresses()))
		{
			addressModel = getAddress(userModel.getAddresses(), a -> BooleanUtils.isTrue(a.getShippingAddress()));
		}
		if(addressModel == null){
			throw new AddressNotFoundException();
		}
		return addressModel;
	}

	// ----------------------------------
	// DON'T MODIFY THE FOLLOWING METHODS
	// ----------------------------------

	/**
	 * This method returns an address based on the condition
	 *
	 * @param addresses the address list
	 * @param condition the condition
	 * @return the first address that matches the condition
	 */
	private AddressModel getAddress(Collection<AddressModel> addresses, Predicate<AddressModel> condition)
	{
		for (AddressModel addressModel : addresses)
		{
			if (condition.test(addressModel))
			{
				return addressModel;
			}
		}
		return null;
	}

	/**
	 * This method takes 1 second to return a response
	 *
	 * @return the user country
	 */
	private String inferCountry()
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException ex)
		{

		}
		return "CA";
	}
}
