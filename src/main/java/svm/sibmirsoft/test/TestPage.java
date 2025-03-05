package svm.sibmirsoft.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import svm.sibmirsoft.pages.Page;

public class TestPage {
    private Page page;

    private final String NAME = "Владислав";
    private final String PASSWORD = "123456";
    private final String DRINK_1 = "Milk";
    private final String DRINK_2 = "Coffee";
    private final String COLOR = "Yellow";
    private final String AUTOMATION = "Yes";
    private final String EMAIL = "vladislav@test.com";
    private final String EXPECTED_MESSAGE = "Количество инструментов: 5. Набольшее количество символов: Katalon Studio";
    private final String EXPECTED_ALERT_TEXT = "Message received!";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        page = new Page(driver);
    }

    @org.testng.annotations.Test
    public void testForm() {
        page.open();

        page.setNameInput(NAME);
        page.setPassword(PASSWORD);
        page.setDrink(DRINK_1);
        page.setDrink(DRINK_2);
        page.setColor(COLOR);
        page.setAutomation(AUTOMATION);
        page.setEmail(EMAIL);
        page.setToolsInfoToMessage();

        Assert.assertEquals(page.getNameInputValue(), NAME, "Имя не совпадает");
        Assert.assertEquals(page.getPasswordValue(), PASSWORD, "Пароль не совпадает");
        Assert.assertTrue(page.isDrinkSelected(DRINK_1), "Напиток " + DRINK_1 + " не выбран");
        Assert.assertTrue(page.isDrinkSelected(DRINK_2), "Напиток " + DRINK_2 + " не выбран");
        Assert.assertTrue(page.isColorSelected(COLOR), "Цвет " + COLOR + " не выбран");
        Assert.assertEquals(page.getEmailValue(), EMAIL, "Email не совпадает");
        Assert.assertEquals(page.getMessageValue(), EXPECTED_MESSAGE, "Сообщение не совпадает");

        page.clickSubmit();
        Assert.assertEquals(page.getAlertText(), EXPECTED_ALERT_TEXT, "Не совпадает");
    }
}