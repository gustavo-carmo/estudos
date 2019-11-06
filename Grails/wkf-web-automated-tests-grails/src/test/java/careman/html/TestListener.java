package careman.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import br.com.workfinity.util.SeleniumUtil;
import br.com.workfinity.web.page.StringUtils;

public class TestListener implements ITestListener {

    static final Logger LOG = LoggerFactory.getLogger(TestListener.class);
    static final String FILE_BKP = "target/artifacts/bkp/";

    @Override
    public void onTestStart(ITestResult result) {
        LOG.info("START " + getFullName(result));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
         LOG.info("SUCCESS: " + getFullName(result));
        // TODO - these values must be injected
         doBkpMySQL(result, FILE_BKP, "osmanagersas", "osmanagersas", "osmanagersas", "192.168.2.33", "9200");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        try {

            Object currentClass = result.getInstance();
            WebDriver webDriver = ((TestBase) currentClass).getDriver();

            String fullName = getFullName(result);

            if (null != result.getThrowable()) {
                LOG.error("ERROR: " + fullName + " / " + result.getThrowable().getMessage());
                result.getThrowable().getStackTrace();
            }
            
            if (webDriver.getTitle().contains("Workfinity - Erro")) {
            	
            	webDriver.findElement(By.cssSelector("#accordion2")).click();				
			}

            takeScreenShot(fullName, webDriver);

        } catch (Exception e) {
            LOG.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    public void takeScreenShot(String methodName, WebDriver driver) {
    	SeleniumUtil.takeScreenShot(methodName, driver, 5000);
    }

    private void doBkpMySQL(ITestResult result, String pathToSaveBKP, String databaseName, String userName, String password,
                            String host, String port) {

        Object currentClass = result.getInstance();
        TestBase testBase = ((TestBase) currentClass);

        if (testBase.isDoBkp()) {
            try {
                LOG.info("SUCCESS: Start bkp process..");

                File fileToSaveBKP = doBkpMysqlPathToSaveBKP(pathToSaveBKP, doBkpMySQLFileName(result));

                StringBuilder mysqldumpCommand = doBkpMySQLMysqldumpCommand(databaseName, userName, password, port, host,
                        fileToSaveBKP);

                doBkpMySQLExecutor(fileToSaveBKP, mysqldumpCommand);

            } catch (Exception e) {
                LOG.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void doBkpMySQLExecutor(File fileToSaveBKP, StringBuilder mysqldumpCommand) throws IOException, InterruptedException {

        // TODO refactor all....
        Runtime rt = Runtime.getRuntime();
        Process exec = rt.exec(new String[]{"/bin/bash", "-c", mysqldumpCommand.toString()});
        int waitFor = exec.waitFor();

        BufferedReader reader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        StringBuffer output = new StringBuffer();
        String line = "";
        while ((line = reader.readLine()) != null) {
            output.append(line + "\n");
        }

        if (waitFor != 0) {
            LOG.error("BKP command -> " + mysqldumpCommand.toString());
            LOG.error(output.toString());
        }
    }

    private StringBuilder doBkpMySQLMysqldumpCommand(String databaseName, String userName, String password, String port,
                                                     String host, File fileToSaveBKP) {
        StringBuilder mysqldumpCommand = new StringBuilder();
        mysqldumpCommand.append("/usr/bin/mysqldump");
        mysqldumpCommand.append(" --single-transaction --skip-add-locks --skip-comments");

        mysqldumpCommand.append(" -u ");
        mysqldumpCommand.append(userName);

        mysqldumpCommand.append(" -p");
        mysqldumpCommand.append(password);

        if (null != port) {
            mysqldumpCommand.append(" -P ");
            mysqldumpCommand.append(port);
        }

        if (null != host) {
            mysqldumpCommand.append(" -h ");
            mysqldumpCommand.append(host);
        }

        mysqldumpCommand.append(" -B ");
        mysqldumpCommand.append(databaseName);

        mysqldumpCommand.append(" > ");
        mysqldumpCommand.append(fileToSaveBKP.getAbsolutePath());
        return mysqldumpCommand;
    }

    private File doBkpMysqlPathToSaveBKP(String pathToSaveBKP, String fileName) {

        File folderToSaveBKP = new File(pathToSaveBKP);
        if (!folderToSaveBKP.exists()) {
            folderToSaveBKP.mkdirs();
        }

        File fileToSaveBKP = new File(pathToSaveBKP + File.separator + fileName);
        if (fileToSaveBKP.exists()) {
            fileToSaveBKP.delete();
        }
        return fileToSaveBKP;
    }

    private String doBkpMySQLFileName(ITestResult result) {
        String fileName = getFullName(result) + ".sql";
        return fileName;
    }

    private String getFullName(ITestResult result) {
        String fullName = result.getMethod().getRealClass().getCanonicalName() + "." + result.getName().toString();
        return StringUtils.returnShortPackageName(fullName);
    }
}
