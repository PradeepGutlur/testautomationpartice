package com.amdocs.amazon.genericUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.amdocs.amazon.genericUtils.IPathConstant;

/**
 * 
 * @author sujna
 *
 */

/**
 * It is used to read the data from commonData.properties File based on Key
 * which you pass as an argument.
 * 
 * @param key
 * @return
 * @throws IOException
 */
public class FileUtility {
	public String readDatafromPropertyfile(String key) throws IOException {
		FileInputStream fs = new FileInputStream(IPathConstant.FilePath);
		Properties pLoad = new Properties();
		pLoad.load(fs);
		return pLoad.getProperty(key);
	}

}
