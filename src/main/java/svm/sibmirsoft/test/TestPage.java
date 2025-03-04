package svm.sibmirsoft.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import svm.sibmirsoft.pages.Page;

public class TestPage {
    private Page page;

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

        page.setNameInput("Владислав");
        page.setPassword("123456");
        page.setDrink("2");
        page.setDrink("3");
        page.setColor("3");
        page.setAutomation("Yes");
        page.setEmail("vladislav@test.com");
        page.setToolsInfoToMessage();

        Assert.assertEquals(page.getNameInputValue(), "Владислав", "Имя не совпадает");
        Assert.assertEquals(page.getPasswordValue(), "123456", "Пароль не совпадает");
        Assert.assertTrue(page.isDrinkSelected("2"), "Напиток 2 не выбран");
        Assert.assertTrue(page.isDrinkSelected("3"), "Напиток 3 не выбран");
        Assert.assertTrue(page.isColorSelected("3"), "Цвет 3 не выбран");
        Assert.assertEquals(page.getEmailValue(), "vladislav@test.com", "Email не совпадает");

        String expectedMessage = "Количество инструментов: 5. Набольшее количество символов: Katalon Studio";
        Assert.assertEquals(page.getMessageValue(), expectedMessage, "Сообщение не совпадает");

        page.clickSubmit();

        Assert.assertEquals(page.getAlertText(), "Message received!", "Не совпадает");
    }
}