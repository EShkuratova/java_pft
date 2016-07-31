package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CreateGroupTests {

  FirefoxDriver wd;

  @BeforeMethod
    public void setUp(){
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    login();

  }

@Test
    public void  testGroupCreation() {



        wd.findElement(By.linkText("groups")).click();
        wd.findElement(By.xpath("//div[@id='content']/form/input[4]")).click();
        wd.findElement(By.name("group_name")).click();
        wd.findElement(By.name("group_name")).clear();
        wd.findElement(By.name("group_name")).sendKeys("test1");
        wd.findElement(By.name("group_header")).click();
        wd.findElement(By.name("group_header")).clear();
        wd.findElement(By.name("group_header")).sendKeys("test2");
        wd.findElement(By.name("group_footer")).click();
        wd.findElement(By.name("group_footer")).clear();
        wd.findElement(By.name("group_footer")).sendKeys("test3");
        wd.findElement(By.name("submit")).click();
        wd.findElement(By.linkText("group page")).click();
        wd.quit();
    }

   private void login(){

     wd.get("http://localhost/addressbook/");
     wd.findElement(By.name("pass")).click();
     wd.findElement(By.name("pass")).clear();
     wd.findElement(By.name("pass")).sendKeys("secret");
     wd.findElement(By.name("user")).click();
     wd.findElement(By.name("user")).clear();
     wd.findElement(By.name("user")).sendKeys("admin");
     wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();

   }

  @AfterMethod

   public void tearDown(){
    wd.quit();
  }
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
