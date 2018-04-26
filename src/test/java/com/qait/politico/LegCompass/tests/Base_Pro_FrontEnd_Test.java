package com.qait.politico.LegCompass.tests;

public abstract class Base_Pro_FrontEnd_Test extends BaseProTest
{

	@Override
	protected String getLaunchUrl()
	{
		
//		return getConfigValue("Pro_Marketing_url");
		return getConfigValue("Pro_frontend_URL");

		
	}
}
