package com.example;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class App 
{
    public static void main(String[] args) throws InterruptedException
    {
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        Thread.sleep(2000);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(2000);

        // Open new tab
        driver.switchTo().newWindow(WindowType.TAB);

        // Add the website link you want to open here
        driver.get("https://practicetestautomation.com/practice-test-login/");

        Thread.sleep(2000);
        
        driver.findElement(By.id("username")).sendKeys("student");

        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("Password123");

        Thread.sleep(2000);
        driver.findElement(By.id("submit")).click();
        
        Thread.sleep(2000);

        // Open new tab
        driver.switchTo().newWindow(WindowType.TAB);

        // Add the website link you want to open here
        driver.get("https://automationexercise.com/");

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// Handle ad if it appears
try {
    wait.until(ExpectedConditions.elementToBeClickable(By.id("dismiss-button"))).click();
} catch (Exception e) {
    System.out.println("No ad appeared");
}

// Wait for product and click Add to Cart
// wait for add to cart button
WebElement addToCart = wait.until(
    ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-product-id='2']"))
);

// scroll to element
((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);

// click using JavaScript to avoid ad interception
((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

// Handle modal popup and click View Cart
wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//u[text()='View Cart']")
)).click();

// Handle ad again if it appears
try {
    wait.until(ExpectedConditions.elementToBeClickable(By.id("dismiss-button"))).click();
} catch (Exception e) {
    System.out.println("No ad appeared after cart");
}
        
        

    }
}
