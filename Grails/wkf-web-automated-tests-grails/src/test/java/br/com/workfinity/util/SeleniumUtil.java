package br.com.workfinity.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeleniumUtil {
	
    static final Logger LOG = LoggerFactory.getLogger(SeleniumUtil.class);
	
    public static void takeScreenShot(String fileName, WebDriver driver) {
    	takeScreenShot(ConfigUtil.FILE_PATH_TO_SCREENSHOT, fileName, driver);
    }

    public static void takeScreenShot(String fileName, WebDriver driver, long waitToTakeScreenShot) {
    	try {
			Thread.sleep(waitToTakeScreenShot);
			takeScreenShot(fileName, driver);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    
	public static void takeScreenShot(String filePath, String fileName, WebDriver driver) {

        if (null == driver) {
            LOG.error("Falha ao tirar SS: " + fileName + ", driver is null");
        } else {

            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try {
                File file = new File(filePath + fileName + ".png");
                FileUtils.copyFile(scrFile, file);
                LOG.error("Placed screen shot in " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
}
