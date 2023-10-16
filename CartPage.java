package TestAutomationProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CartPage {
    String ExpectedCartTitle="https://www.demoblaze.com/cart.html";
    String ActualCartTitle;
    String MyName="yoni";
    String MyCountry="israel";
    String MyCity="Tel-Aviv";
    String MyCreditNumber="45555013";
    String MyCreditMonth="14/10";
    String MyCreditYear="2026";



    private WebDriver driver;


    @FindBy(linkText = "Samsung galaxy s6")
    private WebElement samsungGalaxyS6InCart;
    @FindBy(xpath = "//*[@id=\"navbarExample\"]/ul/li[4]/a")
    private WebElement cartButton;

    @FindBy(xpath = "//*[@id=\"page-wrapper\"]/div/div[2]/button")
    private WebElement placeOrder;



    @FindBy(xpath = "//*[@id=\"name\"]")
    private WebElement NameField;

    @FindBy(xpath = "//*[@id=\"country\"]")
    private WebElement CountryField;

    @FindBy(xpath = "//*[@id=\"city\"]")
    private WebElement CityField;

    @FindBy(xpath = "//*[@id=\"card\"]")
    private WebElement CardField;

    @FindBy(xpath = "//*[@id=\"month\"]")
    private WebElement MonthField;

    @FindBy(xpath = "//*[@id=\"orderModal\"]/div/div/div[3]/button[2]")
    private WebElement PurchaseButton;



    @FindBy(xpath = "//*[@id=\"year\"]")
    private WebElement YearField;

    @FindBy(xpath = "/html/body/div[10]/p/text()[4]")
    private WebElement nameInPopup;

    @FindBy(xpath = "/html/body/div[10]/p/text()[3]")
    private WebElement cardNumberInPopup;

    @FindBy(xpath = "/html/body/div[10]/p/text()[5]")
    private WebElement dateInPopup;

    @FindBy(tagName = "h2")
    private WebElement thankYouHeader;

    @FindBy(id = "totalm")
    private WebElement totalAmountField;

    @FindBy(xpath = "/html/body/div[10]/p")
    private WebElement popupDetails;

    @FindBy(xpath = "/html/body/div[10]/div[7]/div/button")
    private WebElement OkButton;





    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }



    public void verifyCartButton() {
        cartButton.click();
        ActualCartTitle=driver.getCurrentUrl();
        Assert.assertEquals(ActualCartTitle,ExpectedCartTitle);
        System.out.println("ActualCartTitle:"+ ActualCartTitle);
        System.out.println("ExpectedCartTitle:"+ ExpectedCartTitle);
    }

    public void verifyProductInCart() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertNotNull(samsungGalaxyS6InCart, "Samsung Galaxy S6 was not found in the cart");
    }

    public void clickOnPlaceOrder(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        placeOrder.click();

    }

    public void verifyCheckOutFields(){
        NameField.sendKeys(MyName);
        CountryField.sendKeys(MyCountry);
        CityField.sendKeys(MyCity);
        CardField.sendKeys(MyCreditNumber);
        MonthField.sendKeys(MyCreditMonth);
        YearField.sendKeys(MyCreditYear);
        PurchaseButton.click();


    }



    public void verifyPopUpDetails(){
         CartPage cartPage = new CartPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popupDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[10]/p")));
         String popupDetailsText = cartPage.getPopupDetailsText();
        System.out.println("popupDetailsText: " + popupDetailsText);

        Assert.assertTrue(popupDetailsText.contains("Name: " + MyName), "Name does not match");
        Assert.assertTrue(popupDetailsText.contains("Card Number: " + MyCreditNumber), "Card number does not match");
        Assert.assertTrue(popupDetailsText.contains("Amount: 360 USD"), "Amount  does not match");
        OkButton.click();


    }

    public String getPopupDetailsText() {
        return popupDetails.getText();
    }

    public String getNameText() {
        return nameInPopup.getText();
    }


    public String DateInPopup() {
        return dateInPopup.getText();
    }

    public boolean isThankYouHeaderPresent() {
        return thankYouHeader.isDisplayed();
    }

        public String getTotalAmountText() {
            return totalAmountField.getText();
        }

    public String getCardNumberText() {
        return cardNumberInPopup.getText();
    }










}
