package com.qait.politico.LegCompass.tests;

public abstract class Base_Pro_FrontEnd_Test extends BaseProTest {

	@Override
	protected String getLaunchUrl() {
		return getConfigValue("base_url");

	}
}
