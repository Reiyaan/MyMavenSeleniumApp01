package com.example;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class App 
{
    public static void main(String[] args) throws InterruptedException
    {
        // 🔥 Force correct driver and avoid Selenium cache issues
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        System.setProperty("webdriver.chrome.silentOutput", "true");

        ChromeOptions options = new ChromeOptions();

        // 🔥 Force system Chrome (NOT Selenium cache)
        options.setBinary("/usr/bin/google-chrome-stable");

        // 🔥 Jenkins-safe options
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--remote-allow-origins=*");

        // 🔥 Fix DevToolsActivePort error
        options.addArguments("--user-data-dir=/tmp/chrome-" + System.currentTimeMillis());
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--disable-software-rasterizer");

        WebDriver driver = new ChromeDriver(options);

        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();

        Thread.sleep(2000);
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("secret_sauce");

        Thread.sleep(2000);
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(2000);

        // 🔹 Open second tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://practicetestautomation.com/practice-test-login/");

        Thread.sleep(2000);
        driver.findElement(By.id("username")).sendKeys("student");

        Thread.sleep(2000);
        driver.findElement(By.id("password")).sendKeys("Password123");

        Thread.sleep(2000);
        driver.findElement(By.id("submit")).click();

        Thread.sleep(2000);

        // 🔹 Open third tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://automationexercise.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Handle ad if present
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("dismiss-button"))).click();
        } catch (Exception e) {
            System.out.println("No ad appeared");
        }

        // Add to cart
        WebElement addToCart = wait.until(
            ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[data-product-id='2']"))
        );

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToCart);

        // View cart
        wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//u[text()='View Cart']")
        )).click();

        // Handle ad again
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("dismiss-button"))).click();
        } catch (Exception e) {
            System.out.println("No ad appeared after cart");
        }

        Thread.sleep(3000);

        driver.quit();
    }
}
