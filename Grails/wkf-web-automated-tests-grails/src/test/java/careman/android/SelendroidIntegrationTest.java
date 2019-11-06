/*
 * Copyright 2012-2014 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package careman.android;

/*import io.selendroid.SelendroidCapabilities;
import io.selendroid.SelendroidConfiguration;
import io.selendroid.SelendroidDriver;
import io.selendroid.SelendroidLauncher;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;*/

public class SelendroidIntegrationTest {/*
    private static SelendroidLauncher selendroidServer = null;
    private static WebDriver driver = null;


    // @Test
    public void AndroidTests() throws Exception {

       Test01_Login();
       Test02_Cancelamento();
       Test03_Instalacao();
       Test04_ManutencaoSemTroca();
     //  Test05_ManutencaoComTroca();
     //  Test06_TrocaDeTecnologia();
     //  Test07_Desinstalacao();

    }


    public void Test01_Login  () throws InterruptedException {

        WebElement txtIpServidor = driver.findElement(By.id("configure_tenant_url_text_view"));
        txtIpServidor.sendKeys("192.168.2.101:8080/osmanager-tenant");
        Assert.assertEquals("192.168.2.101:8080/osmanager-tenant", txtIpServidor.getText());

        WebElement btnServerConfigure = driver.findElement(By.id("configure_form_button"));
        btnServerConfigure.click();

        // WebElement msgIpServerConfig = driver.findElement(By.name("tenant_url_configured"));
        // Assert.assertEquals("URL do Tenant configurada",msgIpServerConfig.getText());

        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("tec_mob");
        Assert.assertEquals("tec_mob", userName.getText());

        WebElement userPassword = driver.findElement(By.id("password"));
        userPassword.sendKeys("1234567");

        WebElement btnUserSignIn = driver.findElement(By.id("sign_in_button"));
        btnUserSignIn.click();

        Thread.sleep(3000);

        // WebElement msgErro = driver.findElement(By.name("login.fail"));
        // Assert.assertEquals("Ops, seu usuário ou senha estão incorretos",msgErro.getText());

        Assert.assertEquals("tec_mob", userName.getText());
        userPassword.clear();
        userPassword.sendKeys("123456");
        btnUserSignIn.click();
        Thread.sleep(18000);
        System.out.println("LoginTest Finalizado. ");
        }

    public void Test02_Cancelamento  () throws InterruptedException {


       WebElement updateOs = driver.findElement(By.xpath("//ImageButton[@id='imageButton']"));
       updateOs.click();
       Thread.sleep(5000);

       WebElement os1 = driver.findElement(By.xpath("(//TextView[@id='service_order_list_item_code'])[1]"));
       os1.click();
       Thread.sleep(1000);
       //WebElement pages = driver.findElement(By.id("service_order_edit_fragment_activity_pager"));
       TouchActions down1 = new TouchActions(driver).scroll(0,200);
       down1.perform();
       Thread.sleep(500);
       WebElement status = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_status_spinner']"));
       status.click();
       Thread.sleep(500);
       WebElement cancelado = driver.findElement(By.linkText("Cancelado"));
       cancelado.click();
       Thread.sleep(500);
       WebElement reasonForCancellation = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_reason_of_cancellation_spinner']"));
       reasonForCancellation.click();
       Thread.sleep(500);
       WebElement nokFaltaAssinatura = driver.findElement(By.linkText("NOK - FALTA ASSINATURA DO CONTRATO"));
       nokFaltaAssinatura.click();
       TouchActions down2 = new TouchActions(driver).scroll(0,325);
       down2.perform();
       WebElement closedDate = driver.findElement(By.id("service_order_edit_order_closed_date_change_button"));
       closedDate.click();
       Thread.sleep(1000);
       WebElement closedDateSet = driver.findElement(By.id("date_time_set"));
       closedDateSet.click();
       Thread.sleep(500);
       WebElement save = driver.findElement(By.xpath("(//ImageButton[@id='imageButton'])[2]"));
       save.click();
       Thread.sleep(5000);
       System.out.println("Cancelamento de OS Finalizado.");

    }

    public void Test03_Instalacao  () throws InterruptedException {
       WebElement updateOs = driver.findElement(By.xpath("//ImageButton[@id='imageButton']"));
       updateOs.click();
       Thread.sleep(5000);
       WebElement os1 = driver.findElement(By.xpath("(//TextView[@id='service_order_list_item_code'])[2]"));
       os1.click();
       Thread.sleep(1000);
       WebElement abaEquipamento = driver.findElement(By.xpath("(//TabView)[2]"));
       abaEquipamento.click();
       Thread.sleep(1000);
       WebElement selectFromStock = driver.findElement(By.id("service_order_edit_equipments_equipment_from_stock_button"));
       selectFromStock.click();
       WebElement selectFirstEquipment = driver.findElement(By.xpath("(//TextView[@id='equipment_dialog_model'])[1]"));
       selectFirstEquipment.click();
       Thread.sleep(1000);
       WebElement goBackToServiceOrder = driver.findElement(By.xpath("(//TabView)[1]"));
       goBackToServiceOrder.click();
       Thread.sleep(500);
       TouchActions down1 = new TouchActions(driver).scroll(0,200);
       down1.perform();
       Thread.sleep(500);
       WebElement status = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_status_spinner']"));
       status.click();
       Thread.sleep(500);
       WebElement finalizado = driver.findElement(By.linkText("Finalizado"));
       finalizado.click();
       Thread.sleep(500);
       TouchActions down2 = new TouchActions(driver).scroll(0,325);
       down2.perform();
       WebElement closedDate = driver.findElement(By.id("service_order_edit_order_closed_date_change_button"));
       closedDate.click();
       Thread.sleep(1000);
       WebElement closedDateSet = driver.findElement(By.id("date_time_set"));
       closedDateSet.click();
       Thread.sleep(500);
        WebElement save = driver.findElement(By.xpath("(//ImageButton[@id='imageButton'])[2]"));
        save.click();
       Thread.sleep(5000);
       System.out.println("Instalação Finalizada.");
    }

    public void Test04_ManutencaoSemTroca  () throws InterruptedException {

        WebElement updateOs = driver.findElement(By.xpath("//ImageButton[@id='imageButton']"));
        updateOs.click();
        Thread.sleep(7000);

        WebElement os1 = driver.findElement(By.xpath("(//TextView[@id='service_order_list_item_code'])[3]"));
        os1.click();
        Thread.sleep(1000);

        TouchActions down1 = new TouchActions(driver).scroll(0,350);
        down1.perform();
        Thread.sleep(500);

        WebElement defect = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_defect_spinner']"));
        defect.click();
        Thread.sleep(500);

        WebElement posBloqueado = driver.findElement(By.linkText("POS: Terminal Bloqueado"));
        posBloqueado.click();
        Thread.sleep(500);

        WebElement solution = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_solution_spinner']"));
        solution.click();
        Thread.sleep(500);

        WebElement confTerminal = driver.findElement(By.linkText("CONFIGURACAO DO TERMINAL"));
        confTerminal.click();
        Thread.sleep(500);

        WebElement status = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_status_spinner']"));
        status.click();
        Thread.sleep(500);
        WebElement finalizado = driver.findElement(By.linkText("Finalizado"));
        finalizado.click();

        TouchActions down2 = new TouchActions(driver).scroll(0,325);
        down2.perform();

        WebElement closedDate = driver.findElement(By.id("service_order_edit_order_closed_date_change_button"));
        closedDate.click();
        Thread.sleep(1000);

        WebElement closedDateSet = driver.findElement(By.id("date_time_set"));
        closedDateSet.click();
        Thread.sleep(500);

        WebElement abaEquipamento = driver.findElement(By.xpath("(//TabView)[2]"));
        abaEquipamento.click();
        Thread.sleep(1000);

        WebElement serialEquipamento = driver.findElement(By.id("service_order_edit_order_equipment_serial_number_edit_text"));
        serialEquipamento.sendKeys("98292CT50040808");
        Assert.assertEquals("98292CT50040808", serialEquipamento.getText());
        Thread.sleep(500);

        WebElement save = driver.findElement(By.xpath("(//ImageButton[@id='imageButton'])[2]"));
        save.click();
        Thread.sleep(5000);

        System.out.println("Manutenção Sem Troca Finalizada. ");

    }


    public void Test05_ManutencaoComTroca  () throws InterruptedException {
        WebElement updateOs = driver.findElement(By.xpath("//ImageButton[@id='imageButton']"));
        updateOs.click();
        Thread.sleep(7000);

        WebElement os1 = driver.findElement(By.xpath("(//TextView[@id='service_order_list_item_code'])[3]"));
        os1.click();
        Thread.sleep(1000);

        TouchActions down1 = new TouchActions(driver).scroll(0,350);
        down1.perform();
        Thread.sleep(500);

        WebElement defect = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_defect_spinner']"));
        defect.click();
        Thread.sleep(500);

        WebElement posBloqueado = driver.findElement(By.linkText("POS: Terminal Bloqueado"));
        posBloqueado.click();
        Thread.sleep(500);

        WebElement solution = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_solution_spinner']"));
        solution.click();
        Thread.sleep(500);

        WebElement confTerminal = driver.findElement(By.linkText("CONFIGURACAO DO TERMINAL"));
        confTerminal.click();
        Thread.sleep(500);

        WebElement status = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_status_spinner']"));
        status.click();
        Thread.sleep(500);
        WebElement finalizado = driver.findElement(By.linkText("Finalizado"));
        finalizado.click();

        TouchActions down2 = new TouchActions(driver).scroll(0,325);
        down2.perform();

        WebElement closedDate = driver.findElement(By.id("service_order_edit_order_closed_date_change_button"));
        closedDate.click();
        Thread.sleep(1000);

        WebElement closedDateSet = driver.findElement(By.id("date_time_set"));
        closedDateSet.click();
        Thread.sleep(500);

        WebElement abaEquipamento = driver.findElement(By.xpath("(//TabView)[2]"));
        abaEquipamento.click();
        Thread.sleep(1000);

        WebElement serialEquipamento = driver.findElement(By.id("service_order_edit_order_equipment_serial_number_edit_text"));
        serialEquipamento.sendKeys("98292CT50040808");
        Assert.assertEquals("98292CT50040808", serialEquipamento.getText());
        Thread.sleep(500);

        WebElement save = driver.findElement(By.xpath("(//ImageButton[@id='imageButton'])[2]"));
        save.click();
        Thread.sleep(5000);

        System.out.println("Manutenção Com Troca Finalizada. ");

    }

    public void Test06_TrocaDeTecnologia  () throws InterruptedException {
        WebElement updateOs = driver.findElement(By.xpath("//ImageButton[@id='imageButton']"));
        updateOs.click();
        Thread.sleep(5000);
        WebElement os1 = driver.findElement(By.xpath("(//TextView[@id='service_order_list_item_code'])[4]"));
        os1.click();
        Thread.sleep(1000);
        //WebElement pages = driver.findElement(By.id("service_order_edit_fragment_activity_pager"));
        TouchActions down1 = new TouchActions(driver).scroll(0,200);
        down1.perform();
        Thread.sleep(500);
        WebElement status = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_status_spinner']"));
        status.click();
        Thread.sleep(500);
        WebElement cancelado = driver.findElement(By.linkText("Cancelado"));
        cancelado.click();
        Thread.sleep(500);
        WebElement reasonForCancellation = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_reason_of_cancellation_spinner']"));
        reasonForCancellation.click();
        Thread.sleep(500);
        WebElement nokFaltaAssinatura = driver.findElement(By.linkText("NOK - FALTA ASSINATURA DO CONTRATO"));
        nokFaltaAssinatura.click();
        TouchActions down2 = new TouchActions(driver).scroll(0,325);
        down2.perform();
        WebElement closedDate = driver.findElement(By.id("service_order_edit_order_closed_date_change_button"));
        closedDate.click();
        Thread.sleep(1000);
        WebElement closedDateSet = driver.findElement(By.id("date_time_set"));
        closedDateSet.click();
        Thread.sleep(500);
        WebElement save = driver.findElement(By.xpath("(//ImageButton[@id='imageButton'])[2]"));
        save.click();
        Thread.sleep(3000);
        System.out.println("Troca de Tecnologia Finalizada. ");


    }


    public void Test07_Desinstalacao  () throws InterruptedException {
        WebElement updateOs = driver.findElement(By.xpath("//ImageButton[@id='imageButton']"));
        updateOs.click();
        Thread.sleep(5000);
        WebElement os1 = driver.findElement(By.xpath("(//TextView[@id='service_order_list_item_code'])[5]"));
        os1.click();
        Thread.sleep(1000);
        //WebElement pages = driver.findElement(By.id("service_order_edit_fragment_activity_pager"));
        TouchActions down1 = new TouchActions(driver).scroll(0,200);
        down1.perform();
        Thread.sleep(500);
        WebElement status = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_status_spinner']"));
        status.click();
        Thread.sleep(500);
        WebElement cancelado = driver.findElement(By.linkText("Finalizado"));
        cancelado.click();
        Thread.sleep(500);
        WebElement reasonForCancellation = driver.findElement(By.xpath("//Spinner[@id='service_order_edit_order_reason_of_cancellation_spinner']"));
        reasonForCancellation.click();
        Thread.sleep(500);
        WebElement nokFaltaAssinatura = driver.findElement(By.linkText("NOK - FALTA ASSINATURA DO CONTRATO"));
        nokFaltaAssinatura.click();
        TouchActions down2 = new TouchActions(driver).scroll(0,325);
        down2.perform();
        WebElement closedDate = driver.findElement(By.id("service_order_edit_order_closed_date_change_button"));
        closedDate.click();
        Thread.sleep(1000);
        WebElement closedDateSet = driver.findElement(By.id("date_time_set"));
        closedDateSet.click();
        Thread.sleep(500);
        WebElement save = driver.findElement(By.xpath("(//ImageButton[@id='imageButton'])[2]"));
        save.click();
        Thread.sleep(3000);
        System.out.println("Desinstalação Finalizada. ");

    }

    @BeforeClass
    public static void startSelendroidServer() throws Exception {
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
        SelendroidConfiguration config = new SelendroidConfiguration();
        config.addSupportedApp("/home/dfmalafaia/src/src.android/careman-android/app/careman.apk");
        selendroidServer = new SelendroidLauncher(config);

        selendroidServer.launchSelendroid();
        SelendroidCapabilities caps =
                new SelendroidCapabilities("br.com.careman:0.2.2.1");

        driver = new SelendroidDriver(caps);

    }

    @AfterClass
    public static void stopSelendroidServer() {
        if (driver != null) {
            driver.quit();
        }
        if (selendroidServer != null) {
            selendroidServer.stopSelendroid();
        }
    }
*/
}
