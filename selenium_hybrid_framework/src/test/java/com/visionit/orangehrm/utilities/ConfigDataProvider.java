package com.visionit.orangehrm.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
	public Properties prop;
	
	public ConfigDataProvider()
	{
		try
		{
			File fs=new File("./config/config.properties");
			FileInputStream fins=new FileInputStream(fs);
			prop= new Properties();
			prop.load(fins);
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public String getKeyValue(String searchKey)
	{
		return prop.getProperty( searchKey);
	}
public String  getUsername()
{
	return  prop.getProperty("uname");
}
public String getUserpassword()
{
	return prop.getProperty("upass");
}
public String getUrl()
{
	return prop.getProperty("url");
}

}
