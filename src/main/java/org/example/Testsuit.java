package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.className;

public class Testsuit {
    protected static WebDriver driver;

    @BeforeMethod
    public static void openBrowser() {
        //Open the browser
        driver = new ChromeDriver();
        //Type Url
        driver.get("https://demo.nopcommerce.com/");
        //
        driver.manage().window().maximize();
        //
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    //Method to close the browser
    public static void closeBrowser() {
        driver.close();
    }


    //method to click the function by driver
    public static void clickOnElement(By by) {
        driver.findElement(by).click();
    }

    //method to type the text by driver
    public static void typeTextFromElement(By by, String text) {
        driver.findElement(by).sendKeys(text);
    }

    //To reuse the email address
    public static long timestamp() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        return timestamp.getTime();
    }

    //to get the text from server
    public static String getTextFromElement(By by) {
        driver.findElement(by).getText();
        return null;
    }

    @Test
    public static void toVerifyUserShouldBeAbleToRegisterSuccessfully() {

        //click on register button
        clickOnElement(By.className("ico-register"));
        //type first name
        typeTextFromElement(By.id("FirstName"), "Tahmina");
        //type last name
        typeTextFromElement(By.id("LastName"), "liza");
        //type email address
        typeTextFromElement(By.name("Email"), "liza.tareen+" + timestamp() + "@gmail.com");
        //type the password
        typeTextFromElement(By.id("Password"), ("hd12345"));
        //type confirm password
        typeTextFromElement(By.id("ConfirmPassword"), ("hd12345"));
        //click on register button
        clickOnElement(By.id("register-button"));
        //actual message should print after pass the test
        String expectedProductInCart = "Your registration completed";
        String actualmessage = driver.findElement(className("result")).getText();
        System.out.println("My message=" + actualmessage);
        //if the test fail then accepted and actual message should print
        Assert.assertEquals(actualmessage, expectedProductInCart, "Your registration Unsuccessful");


    }

    @Test
    public static void toVerifyUserShouldnotAbleToVoteWithoutregisteration()  {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

     wait.until(ExpectedConditions.elementToBeClickable(By.id("pollanswers-2")));
        //select good option from community poll
        clickOnElement(By.id("pollanswers-2"));
        //click on vote
        clickOnElement(By.id("vote-poll-1"));
       //'only registered user can vote' this text should print
       String expectedProductInCart = "Only registered users can vote.";
        wait.until(ExpectedConditions.elementToBeClickable(By.id("block-poll-vote-error-1")));
       String actualmessage = driver.findElement(By.id("block-poll-vote-error-1")).getText();
       System.out.println("My message=" + actualmessage);
       Assert.assertEquals(actualmessage,expectedProductInCart,"Message displayed is incorrect");

    }

    @Test
    public static void toVerifyUserShouldEmailAFriendFromAProduct() {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"product-item\"]/div[@class=\"details\"]/h2[@class=" + "\"product-title\"]/a[@href=\"/apple-macbook-pro-13-inch\"]")));
        //click on apple Macbook pro 13 inch
        clickOnElement(By.xpath("//div[@class=\"product-item\"]/div[@class=\"details\"]/h2[@class=" + "\"product-title\"]/a[@href=\"/apple-macbook-pro-13-inch\"]"));
        //click on email a friend
        clickOnElement(By.className("email-a-friend"));
        //enter friends email
        typeTextFromElement(By.id("FriendEmail"), "friend123" + timestamp() + "@gmail.com");
        //enter your email addaress
        typeTextFromElement(By.id("YourEmailAddress"), "liala" + timestamp() + "@gmail.com");
         //click on send email
        clickOnElement(By.name("send-email"));
        // an error message should appear on the top
        String expectedProductInCart = "Only registered customers can use email a friend feature";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='page-body']/form/div[@class='message-error validation-summary-errors']")));
        String actualmessage = driver.findElement(By.xpath("//div[@class='page-body']/form/div[@class='message-error validation-summary-errors']")).getText();
        System.out.println("Error message=" + actualmessage);
        Assert.assertEquals(actualmessage, expectedProductInCart, "Only registered customers can use email a friend feature");

    }
    @Test
    public static void toVerifyUserShouldCompareTwoProducts() {
        WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
       // clickOnElement(By.xpath("//div[@class='product-grid home-page-product-grid']//div[@class='item-grid']//div[@class='item-box'][3]//div[@class='product-item']//div[@class='details']//div[@class='add-info']//div[@class='buttons']//button[@class='button-2 add-to-compare-list-button']"));
        clickOnElement(By.xpath("//div[@class='product-grid home-page-product-grid']//div[@class='item-grid']//div[@class='item-box'][3]//div[@class='product-item']//div[@class='details']//div[@class='add-info']//div[@class='buttons']//button[@class='button-2 add-to-compare-list-button']"));
        //clickOnElement(By.id("bar-notification"));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//p[@class='content']")));
        clickOnElement(By.xpath("//div[@class='product-grid home-page-product-grid']/div[@class='item-grid']/div[@class='item-box'][4]/div[@class='product-item']/div[@class='details']/div[@class='add-info']/div[@class='buttons']/button[@class='button-2 add-to-compare-list-button']"));
        clickOnElement(By.xpath("//p[@class=\"content\"]/a"));
        String productName1=driver.findElement(By.xpath("//table[@class='compare-products-table']//tr[@class='product-name']//a[@href='/25-virtual-gift-card']")).getText();
        System.out.println("First product name="+productName1);
       // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("\"\"//table[@class='compare-products-table']//tr[@class='product-name']//a[@href=\\\"/htc-one-m8-android-l-50-lollipop\\\"]\"")));
       String productName2=driver.findElement(By.xpath("//table[@class='compare-products-table']//tr[@class='product-name']//a[@href=\"/htc-one-m8-android-l-50-lollipop\"]")).getText();
       System.out.println("second product name="+productName2);
       clickOnElement(By.className("clear-list"));
       String expectedMessage="You have nothing to compare";
       String actualmessage= driver.findElement(By.className("no-data")).getText();
        System.out.println("Actual message= "+actualmessage);
        Assert.assertEquals(actualmessage,expectedMessage,"You have nothing to compare");

    }
  @Test
    public static void toVerifyRegisterUserShouldRefferedAProductToAFriend(){
        WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(40));
      //click on register button
      clickOnElement(By.className("ico-register"));
      //type first name
      typeTextFromElement(By.id("FirstName"), "Tahmina");
      //type last name
      typeTextFromElement(By.id("LastName"), "liza");
      //type email address
      typeTextFromElement(By.name("Email"), "Lila"+timestamp()+"@gmail.com");
      //type the password
      typeTextFromElement(By.id("Password"), ("hd12345"));
      //type confirm password
      typeTextFromElement(By.id("ConfirmPassword"), ("hd12345"));
      //click on register button
      clickOnElement(By.id("register-button"));
      clickOnElement(By.xpath("//a[@class=\"button-1 register-continue-button\"]"));
      //click on appleMacbook pro 13 inch
      clickOnElement(By.xpath("//div[@class=\"product-item\"]/div[@class=\"details\"]/h2[@class=" + "\"product-title\"]/a[@href=\"/apple-macbook-pro-13-inch\"]"));
      //click on email a friend
      clickOnElement(By.className("email-a-friend"));
      //enter friends email address
      typeTextFromElement(By.id("FriendEmail"),"friend123"+ timestamp() +"@gmail.com");
      //enter your email address
      typeTextFromElement(By.id("YourEmailAddress"),"Lila"+ timestamp() +"@gmail.com");
      clickOnElement(By.name("send-email"));
      String expectedProductInCart="Your message has been sent";
      String actualmessage=driver.findElement(By.className("result")).getText();
      System.out.println("Actual message="+actualmessage);
      Assert.assertEquals(expectedProductInCart,actualmessage,"Your message has been sent");

    }
    @Test
     public static void toVerifyRegisterUserShouldAbleToVote(){
         WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
         //click on register button
         clickOnElement(By.className("ico-register"));
         //type first name
         typeTextFromElement(By.id("FirstName"), "Tahmina");
         //type last name
         typeTextFromElement(By.id("LastName"), "liza");
         //type email address
         typeTextFromElement(By.name("Email"), "liza.tareen+" + timestamp() + "@gmail.com");
         //type the password
         typeTextFromElement(By.id("Password"), ("hd12345"));
         //type confirm password
         typeTextFromElement(By.id("ConfirmPassword"), ("hd12345"));
         //click on register button
         clickOnElement(By.id("register-button"));
         //actual message should print after pass the test
        clickOnElement(By.xpath("//a[@class=\"button-1 register-continue-button\"]"));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("pollanswers-2")));
         //select good option from community poll
         clickOnElement(By.id("pollanswers-2"));
         //click on vote
         clickOnElement(By.id("vote-poll-1"));
        String expectedProductInCart="Excellent (13 vote(s) - 59.1%) Good (6 vote(s) - 27.3%) Poor (2 vote(s) - 9.1%) Very bad (1 vote(s) - 4.5%)";
        String actualmessage=driver.findElement(By.id("poll-block-1")).getText();
        System.out.println("Result of community poll="+actualmessage);
        Assert.assertEquals(expectedProductInCart,actualmessage,"Excellent (13 vote(s) 59.1%) Good (6 vote(s) - 27.3%) Poor (2 vote(s) - 9.1%) Very bad (1 vote(s) - 4.5%)");

     }
     @Test
    public static void toVerifyTheShoppingBusketContainsAppropriateIteams(){
         WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(50));
         //click on electronics
         clickOnElement(By.xpath("//div[@class='header-menu']//ul[@class='top-menu notmobile']//a[@href='/electronics']"));
         //click on camera and photo
         clickOnElement(By.xpath("//div[@class='sub-category-item']//a[@href='/camera-photo']"));
         //add to cart leica camera
         clickOnElement(By.xpath("//div[@class='item-box'][3]//div[@class='product-item']//div[@class='details']//div[@class='add-info']//div[@class='buttons']//button[@class='button-2 product-box-add-to-cart-button']"));
       clickOnElement(By.xpath("//a[@href='/cart']"));
     }

}


