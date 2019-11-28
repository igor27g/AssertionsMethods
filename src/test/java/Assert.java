import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Assert {

    WebDriver driver;

    @BeforeEach
    public void driverSetup()
    {
        String fakeStoreNaElementach = "https://fakestore.testelka.pl/metody-na-elementach/";

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(fakeStoreNaElementach);

    }

    @AfterEach
    public void driverClose()
    {
        driver.close();
        driver.quit();
    }


    @Test
    public void checkingPropertiesOfElements()
    {
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        WebElement sailingButton = driver.findElement(By.cssSelector("[name='sailing']"));
        List<WebElement> yellowsButtons = driver.findElements(By.cssSelector("a.button"));
        WebElement selectCheckbox = driver.findElement(By.cssSelector("input[name='selected-checkbox']"));
        WebElement selectRadiobutton = driver.findElement(By.cssSelector("input[name='selected-radio']"));
        WebElement notSelectCheckbox = driver.findElement(By.cssSelector("input[name='not-selected-checkbox']"));
        WebElement notSelectRadiobutton = driver.findElement(By.cssSelector("input[name='not-selected-radio']"));
        List<WebElement> allButtons = driver.findElements(By.cssSelector((".button")));



        Assertions.assertAll("Checking properties of elements",
                ()-> Assertions.assertTrue(!mainPageButton.isEnabled(), "Main page button is not disabled"),
                ()-> Assertions.assertFalse(sailingButton.isDisplayed(), "Sailing button is probably displayed."),
                () -> assertThatButtonsAreYellow(yellowsButtons),
                () -> Assertions.assertTrue(selectCheckbox.isSelected(), "Checkbox is not select"),
                () -> Assertions.assertTrue(selectRadiobutton.isSelected(), "Radiobutton is not select"),
                () -> Assertions.assertTrue(!notSelectCheckbox.isSelected(), "Checkbox is select"),
                () -> Assertions.assertTrue(!notSelectRadiobutton.isSelected(), "Radiobutton is select"),
                () -> assertThatAllButtonsAreTagA(allButtons)


        );
    }

    public void assertThatButtonsAreYellow(List<WebElement> buttons)
    {
        for(WebElement button:buttons)
        {
            String color = button.getCssValue("background-color");
            Assertions.assertEquals("rgba(245, 233, 101, 1)",color,"Button color is not what expected");
        }
    }

    public void assertThatAllButtonsAreTagA(List<WebElement> elements)
    {
        for(WebElement element:elements)
        {
            Assertions.assertEquals("a", element.getTagName(), "Not all buttons are tag a");
        }
    }









}
