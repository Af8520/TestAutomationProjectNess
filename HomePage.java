package TestAutomationProject;

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomePage {
    WebDriver driver;

    CartPage cartPage;
    NavigationMenu navigationMenu;
    ProductPage productPage;
    private String[] phoneNames = {"Galaxy", "Sony i7", "Sony a5"};





    @BeforeClass
    public void startSession() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        cartPage=PageFactory.initElements(driver, CartPage.class);
        navigationMenu=PageFactory.initElements(driver, NavigationMenu.class);
        productPage= PageFactory.initElements(driver,ProductPage.class);


    }

@Test
@Description("Filtering by Phones")
public void testFilterByPhones() {
    productPage.clickPhones();
    boolean allProductsArePhones = productPage.verifyAllProductsArePhones(phoneNames);
    Assert.assertTrue(allProductsArePhones, "Not all products are phones");

}
@Test(priority = 1)
@Description("verify upper menu buttons")

    public void testUpperMenuButtons() {
        navigationMenu.verifyHomeButton();
        navigationMenu.verifyContactButton();
        navigationMenu.verifyAboutUsButton();
        navigationMenu.verifyCartButton();
        navigationMenu.verifyLoginButton();
        navigationMenu.verifySignUpButton();
        navigationMenu.verifyHomeButton();


    }
    @Test(priority = 2)
    @Description("check contact-us menu")

    public void testContactUs(){
        navigationMenu.createContactMessage();

    }
    @Test(priority = 3)
    @Description("check empty field")

    public void testEmptyFieldsValidation() {
        navigationMenu.enterName("");
        navigationMenu.enterEmail("");
        navigationMenu.submitForm();

    }

    @Test(priority = 4)
    @Description("check invalid field")

    public void testInvalidFieldsValidation() {
        navigationMenu.enterName("123456");
        navigationMenu.enterEmail("789456");
        navigationMenu.submitForm();

    }


    @Test(priority = 5)
    @Description("check add to card field")

    public void testAddToCart(){
        productPage.clickOnSamsung();
        String ActualUrl =productPage.getCurrentURL();
        Assert.assertTrue(ActualUrl.contains("prod.html?idp_="),"URL contains 'prod.html?idp_='");
        System.out.println("ActualUrl:"+ActualUrl);
        productPage.addToCart();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        Assert.assertEquals(alertText, "Product added", "Alert text does not match");
        System.out.println("alertText:"+alertText);

    }
    @Test(priority = 6)
    @Description("check order field")

    public void testVerifyOrder(){
        cartPage.verifyCartButton();
        cartPage.verifyProductInCart();

    }


    @Test(priority = 7)
    @Description("submit order")

    public void testSubmitOrder(){
        stepClickOnPlaceOrder();
        stepVerifyCheckOutFields();
        stepVerifyPopUpDetails();

    }

    @Step("Click on 'Place Order' button")
    private void stepClickOnPlaceOrder() {
        cartPage.clickOnPlaceOrder();
    }

    @Step("Verify Checkout Fields")
    private void stepVerifyCheckOutFields() {
        cartPage.verifyCheckOutFields();
    }

    @Step("Verify Pop-Up Details")
    private void stepVerifyPopUpDetails() {
        cartPage.verifyPopUpDetails();
    }




    @AfterClass
    public  void  endSession(){
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        driver.quit();

    }

}
