package TestAutomationProject;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class NavigationMenu {
    WebDriver driver;
    String ExpectedHomeTitle="https://www.demoblaze.com/index.html";
    String ActualHomeTitle;

    String ExpectedCartTitle="https://www.demoblaze.com/cart.html";
    String ActualCartTitle;
    String EmailAddress="AlonBarkoni007@gmail.com";
    String MyName="alon";
    String MyMessage="Love your website! ";



    public NavigationMenu(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//*[@id=\"navbarExample\"]/ul/li[1]/a")
    private WebElement homeButton;

    @FindBy(linkText = "Contact")
    private WebElement ContactButton;


    @FindBy(xpath = "//*[@id=\"exampleModal\"]/div/div/div[1]/button/span")
    private WebElement closeButton ;

    @FindBy(xpath = "/html/body/nav/div[1]/ul/li[3]/a")
    private WebElement AboutButton ;

    @FindBy(xpath = "//*[@id=\"videoModal\"]/div/div/div[1]/button/span")
    private WebElement closeAboutButton ;

    @FindBy(xpath = "//*[@id=\"navbarExample\"]/ul/li[4]/a")
    private WebElement cartButton;

    @FindBy(id = "login2")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"logInModal\"]/div/div/div[1]/button/span")
    private  WebElement closeLoginButton;

    @FindBy(id = "signin2")
    private WebElement signUpButton;

    @FindBy(xpath = "//*[@id=\"signInModal\"]/div/div/div[1]/button/span")
    private  WebElement closeSignUpButton;


    @FindBy(id = "recipient-email")
    private WebElement EmailField;

    @FindBy(xpath = "//*[@id=\"recipient-name\"]")
    private WebElement NameField;
    @FindBy(xpath = "//*[@id=\"message-text\"]")
    private WebElement MessageField;

    @FindBy(xpath = "//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]")
    private WebElement sendMessageButton;





    public void verifyHomeButton() {
        homeButton.click();
        ActualHomeTitle=driver.getCurrentUrl();
        Assert.assertEquals(ActualHomeTitle,ExpectedHomeTitle);
        System.out.println("ActualHomeTitle:"+ ActualHomeTitle);
        System.out.println("ExpectedHomeTitle:"+ ExpectedHomeTitle);
    }

    public void verifyContactButton(){
        ContactButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement popupTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"exampleModalLabel\"]")));
        Assert.assertTrue(popupTitle.isDisplayed(), "Popup title is not displayed");
        System.out.println("Popup title:"+popupTitle.getText());
        closeButton.click();
    }

    public void createContactMessage(){
        ContactButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement contactTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"exampleModalLabel\"]")));
        Assert.assertTrue(contactTitle.isDisplayed(), "Popup title is not displayed");
        System.out.println("Contact title:"+contactTitle.getText());
        EmailField.sendKeys(EmailAddress);
        NameField.sendKeys(MyName);
        MessageField.sendKeys(MyMessage);
        sendMessageButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("alertText:"+alertText);
        alert.accept();

    }

    public void enterName(String name) {
        ContactButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement NameTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"recipient-name\"]")));
        NameTitle.sendKeys(name);

    }

    public void enterEmail(String email) {
        EmailField.clear();
        EmailField.sendKeys(email);

    }
    public void submitForm() {
        sendMessageButton.click();
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        System.out.println("alertText:"+alertText);
        alert.accept();

    }



    public void verifyCartButton() {
        cartButton.click();
        ActualCartTitle=driver.getCurrentUrl();
        Assert.assertEquals(ActualCartTitle,ExpectedCartTitle);
        System.out.println("ActualCartTitle:"+ ActualCartTitle);
        System.out.println("ExpectedCartTitle:"+ ExpectedCartTitle);
    }

    public void verifyAboutUsButton(){
        AboutButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(7));
        WebElement AboutTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"videoModalLabel\"]")));
        Assert.assertTrue(AboutTitle.isDisplayed(), "Popup title is not displayed");
        System.out.println("MediaPopup title:"+AboutTitle.getText());
        closeAboutButton.click();



    }

    public void verifyLoginButton(){
        loginButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logInModalLabel\"]")));
        Assert.assertTrue(loginTitle.isDisplayed(), "login title is not displayed");
        System.out.println("Login title:"+loginTitle.getText());
        closeLoginButton.click();
    }
    public void verifySignUpButton(){
        signUpButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement SignUpTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"signInModalLabel\"]")));
        Assert.assertTrue(SignUpTitle.isDisplayed(), "signUp title is not displayed");
        System.out.println("SignUp title:"+SignUpTitle.getText());
        closeSignUpButton.click();
    }

}

