package com.talos.javatraining.lesson4;

import com.talos.javatraining.lesson4.exceptions.AddressNotFoundException;
import com.talos.javatraining.lesson4.model.AddressModel;
import com.talos.javatraining.lesson4.model.UserModel;


public interface Main
{

	/**
	 * Returns the line 1 if possible, otherwise a blank
	 * @param addressModel the address model or null
	 * @return the full name or a blank if was not possible
	 */
	String getLine1(AddressModel addressModel);

	/**
	 * Returns the full name if possible, otherwise a blank
	 * @param addressModel the address model or null
	 * @return the full name o
	 * r a blank if was not possible
	 */
	String getFullName(AddressModel addressModel);

	/**
	 * Returns the first address that is a billingAddress or null if was not possible
	 * @param userModel the user model or null
	 * @return the first billing address or null
	 */
	AddressModel getBillingAddress(UserModel userModel);

	/**
	 * Returns the last login data if exists, otherwise return "the user has not been logged yet"
	 * @param userModel the user model or null
	 * @return a not null string
	 */
	String getLastLoginFormatted(UserModel userModel);

	/**
	 * Gets the contact country if exists, otherwise it is inferred but this process is expensive
	 * @param userModel the user model or null
	 * @return the not null isocode country
	 */
	String getContactCountry(UserModel userModel);

	/**
	 * Gets the shipping address of the given user. if the user doesn't have a shipping address throws an exception
	 * @param userModel the user model. (It won't be null never)
	 * @return the not null shipping address
	 * @throws AddressNotFoundException if the user doesn't have a shipping address
	 */
	AddressModel getShippingAddress(UserModel userModel) throws AddressNotFoundException;
}
