package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;

/**
 * Created by eshkuratova on 31.07.2016.
 */
public class HelperBase {
    protected WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    protected void click(By locator) {
        wd.findElement(locator).click();
    }

    protected void type(By locator, String text) {
        click(locator);

        if (text != null) {
            String existText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file) {
            if(file != null) {
                wd.findElement(locator).sendKeys(file.getAbsolutePath());
            }
            }



    protected boolean isSelected(By locator) {
        return wd.findElement(locator).isSelected();

    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void alertAccept() {
        if (isAlertPresent()) {
            wd.switchTo().alert().accept();
        }
    }

    protected void alertReject() {
        if (isAlertPresent()) {
            wd.switchTo().alert().dismiss();
        }
    }

    protected boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }
}
