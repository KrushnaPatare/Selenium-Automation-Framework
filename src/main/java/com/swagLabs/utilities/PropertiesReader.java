package com.swagLabs.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PropertiesReader 
{
	private  static Logger LOG = LogManager.getLogger(PropertiesReader.class);
	private static FileInputStream fis;
	private static Properties prop = null;

	public static String getProperty(String property) {		

		try {
			fis = new FileInputStream(new File("F:\\WorkSpace\\SwagLabs-Ecommerce\\src\\test\\resources\\config\\config.properties"));
			prop = new Properties();
			prop.load(fis);
		} catch(FileNotFoundException fnfe) 
		{
			LOG.error("Properties File Not Found or Wrong file path entered YOU DumbAss!", fnfe);
		} catch(IOException ioe) {
			LOG.error("IO Exception while loading Properties File", ioe);
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				LOG.error("IO Exception while closing file input stream", e);
			}
		}
		return prop.getProperty(property).trim();
	}
}
