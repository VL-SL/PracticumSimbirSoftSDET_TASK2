package svm.sibmirsoft.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BasePage {
    protected final WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    protected void scrollToElement(By locator) {
        WebElement element = findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    protected void click(By locator) {
        scrollToElement(locator);
        findElement(locator).click();
    }

    protected void inputText(By locator, String text) {
        scrollToElement(locator);
        findElement(locator).sendKeys(text);
    }

    protected void selectFromDropdown(By dropdownLocator, String value) {
        scrollToElement(dropdownLocator);
        Select dropdown = new Select(findElement(dropdownLocator));
        dropdown.selectByVisibleText(value);
    }

    protected String getFieldValue(By locator) {
        scrollToElement(locator);
        return findElement(locator).getAttribute("value");
    }

    protected String getElementText(By locator) {
        scrollToElement(locator);
        return findElement(locator).getText();
    }
}