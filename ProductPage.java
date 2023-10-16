package TestAutomationProject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductPage {
    private WebDriver driver;




    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }




    @FindBy(className = "card-title")
    private List<WebElement> productHeaders;


    @FindBy(linkText  = "Samsung galaxy s6")
    private WebElement SamsungS6Pick;



    @FindBy(linkText = "Add to cart")
    private WebElement AddToCartButton;






    @FindBy(linkText = "Phones")
    private WebElement phonesLink;

    @FindBy(linkText = "Laptops")
    private WebElement laptopsLink;

    @FindBy(linkText = "Monitors")
    private WebElement monitorsLink;








    public void clickPhones() {
        phonesLink.click();

    }

    public void clickLaptops() {
        laptopsLink.click();

    }

    public void clickMonitors() {
        monitorsLink.click();

    }


   public  void clickOnSamsung() {
       clickPhones();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       SamsungS6Pick.click();

   }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }




    public void addToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        AddToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart")));
        AddToCartButton.click();


    }


    public void filterProducts(String category) {

    }
//    public void testVerifyAllProductsArePhones() {
//        ProductPage productPage = new ProductPage(driver);
//        String[] phoneNames = {"Samsung Galaxy", "Iphone", "Sony xperia"};
//
//        boolean allProductsArePhones = productPage.verifyAllProductsArePhones(phoneNames);
//        Assert.assertTrue(allProductsArePhones, "Not all products are phones");
//    }

    public boolean verifyAllProductsArePhones(String[] phoneNames) {
        for (WebElement productHeader : productHeaders) {
            String productHeaderText = productHeader.getText();
            boolean isPhone = false;

            for (String phoneName : phoneNames) {
                if (productHeaderText.contains(phoneName)) {
                    isPhone = true;
                    break;
                }
            }

            if (!isPhone) {
                return false;
            }
        }

        return true;

    }



//    public void verifyProducts(String category) {
//        filterProducts(category);
//
//        List<WebElement> productElements = driver.findElements(By.cssSelector(".product"));
//        for (int i = 0; i < productElements.size(); i++) {
//            WebElement productElement = productElements.get(i);
//            String productCategory = productElement.findElement(By.cssSelector(".category")).getText();
//            Assert.assertEquals(productCategory, category, "Product does not belong to the specified category");
//        }
//    }

    }

