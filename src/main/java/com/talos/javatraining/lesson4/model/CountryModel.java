package com.talos.javatraining.lesson4.model;

public class CountryModel
{
	private String isocode;


	public String getIsocode()
	{
		return isocode;
	}

	public void setIsocode(String isocode)
	{
		this.isocode = isocode;
	}

	@Override
	public String toString()
	{
		return "CountryModel{" +
				"isocode='" + isocode + '\'' +
				'}';
	}
}
