package svm.sibmirsoft.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Page extends BasePage {
    private final By nameInput = By.id("name-input");
    private final By password = By.cssSelector("input[type='password']");
    private final By automation = By.cssSelector("#automation");
    private final By email = By.cssSelector("#email");
    private final By message = By.cssSelector("#message");
    private final By submit = By.cssSelector("#submit-btn");
    private final By automationToolsList = By.xpath("//label[text()='Automation tools']/following-sibling::ul/li");

    private By drink(String drinkNumber) {
        return By.xpath("//input[@type='checkbox' and @id='drink" + drinkNumber + "']");
    }

    private By color(String colorNumber) {
        return By.xpath("//input[@type='radio' and @id='color" + colorNumber + "']");
    }

    public Page(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://practice-automation.com/form-fields/");
    }

    public void setNameInput(String name) {
        inputText(nameInput, name);
    }

    public void setPassword(String passwordIn) {
        inputText(password, passwordIn);
    }

    public void setDrink(String drinkNumber) {
        click(drink(drinkNumber));
    }

    public void setColor(String colorNumber) {
        click(color(colorNumber));
    }

    public void setEmail(String emailIn) {
        inputText(email, emailIn);
    }

    public void setMessage(String messageIn) {
        inputText(message, messageIn);
    }

    public void clickSubmit() {
        click(submit);
    }

    public void setAutomation(String automationIn) {
        selectFromDropdown(automation, automationIn);
    }

    public List<String> getAutomationTools() {
        return driver.findElements(automationToolsList)
                .stream()
                .map(element -> element.getText().replace("::marker", "").trim())
                .collect(Collectors.toList());
    }

    public void setToolsInfoToMessage() {
        List<String> tools = getAutomationTools();

        int toolCount = tools.size();

        String longestTool = tools.stream()
                .max((tool1, tool2) -> Integer.compare(tool1.length(), tool2.length()))
                .orElse("");

        setMessage("Количество инструментов: " + toolCount + ". Набольшее количество символов: " + longestTool);
    }

    public String getNameInputValue() {
        return getFieldValue(nameInput);
    }

    public String getPasswordValue() {
        return getFieldValue(password);
    }

    public boolean isDrinkSelected(String drinkNumber) {
        return findElement(drink(drinkNumber)).isSelected();
    }

    public boolean isColorSelected(String colorNumber) {
        return findElement(color(colorNumber)).isSelected();
    }

    public String getEmailValue() {
        return getFieldValue(email);
    }

    public String getMessageValue() {
        return getFieldValue(message);
    }

    public String getAutomationValue() {
        return findElement(automation).getText();
    }

    public String getAlertText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alertText = alert.getText();
        // alert.accept();
        return alertText;
    }
}