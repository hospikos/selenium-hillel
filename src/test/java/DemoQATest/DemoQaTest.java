package DemoQATest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoQaTest {

    public static final String DOUBLE_CLICK_MESSAGE = "doubleClickMessage";
    public static final String SHOW_SMALL_MODAL = "showSmallModal";
    private static final By ELEMENTS_CARD = By.xpath("//div[@class='card-body']/h5[text()='Elements']");
    private static final By BUTTONS_MENU_ITEM = By.id("item-4");
    public static final String DOUBLE_CLICK_BTN = "doubleClickBtn";
    public static final String RIGHT_CLICK_BTN = "rightClickBtn";
    public static final String RIGHT_CLICK_MESSAGE = "rightClickMessage";
    public static final String MOADL_MENU_ITEM = "//div[@class='header-text' and contains(text(),'Alerts, Frame & Windows')]";
    public static final String MOADL_DIALOGS_BUTTON = "//li[@id='item-4' and contains(span, 'Modal Dialogs')]";
    public static final String SMAL_MODAL_TEXT = "example-modal-sizes-title-sm";
    public static final String LARGE_MODAL = "showLargeModal";
    public static final String LARGE_MODAL_TEXT = "example-modal-sizes-title-lg";
    private static final By RADIO_MENU_ITEM = By.id("item-2");
    public static final String YES_RADIO = "yesRadio";
    public static final String YES_RADIO_MESSAGE = "Yes";

    WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        Dimension dm = new Dimension(1440, 1080);
        driver.manage().window().setSize(dm);
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }

    @Test
    void testDoubleClickButton() {
        WebElement elementsCard = driver.findElement(ELEMENTS_CARD);
        elementsCard.click();

        WebElement buttonsMenuItem = driver.findElement(BUTTONS_MENU_ITEM);
        buttonsMenuItem.click();

        WebElement doubleClickButton = driver.findElement(By.id(DOUBLE_CLICK_BTN));
        new Actions(driver)
                .doubleClick(doubleClickButton)
                .perform();

        WebElement message = driver.findElement(By.id(DOUBLE_CLICK_MESSAGE));
        String actualMessage = message.getText();
        assertEquals("You have done a double click", actualMessage);
    }

    @Test
    void testRightClick() {
        WebElement elementsCard = driver.findElement(ELEMENTS_CARD);
        elementsCard.click();

        WebElement buttonsMenuItem = driver.findElement(BUTTONS_MENU_ITEM);
        buttonsMenuItem.click();

        WebElement rightClickButton = driver.findElement(By.id(RIGHT_CLICK_BTN));
        new Actions(driver)
                .contextClick(rightClickButton)
                .perform();

        WebElement message = driver.findElement(By.id(RIGHT_CLICK_MESSAGE));
        String actualMessage = message.getText();
        assertEquals("You have done a right click", actualMessage);
    }

    @Test
    void testSmallModal() {
        WebElement elementsCard = driver.findElement(ELEMENTS_CARD);
        elementsCard.click();

        WebElement modalMenuItem = driver.findElement(By.xpath(MOADL_MENU_ITEM));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", modalMenuItem);
        modalMenuItem.click();

        WebElement modalDialogs = driver.findElement(By.xpath(MOADL_DIALOGS_BUTTON));
        modalDialogs.click();

        WebElement smallModal = driver.findElement(By.id(SHOW_SMALL_MODAL));
        smallModal.click();

        WebElement smallModalText = driver.findElement(By.id(SMAL_MODAL_TEXT));
        String actualMessage = smallModalText.getText();
        assertEquals("Small Modal", actualMessage);
    }

    @Test
    void testLargeModal() {
        WebElement elementsCard = driver.findElement(ELEMENTS_CARD);
        elementsCard.click();

        WebElement modalMenuItem = driver.findElement(By.xpath(MOADL_MENU_ITEM));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", modalMenuItem);
        modalMenuItem.click();

        WebElement modalDialogs = driver.findElement(By.xpath(MOADL_DIALOGS_BUTTON));
        modalDialogs.click();

        WebElement largeModal = driver.findElement(By.id(LARGE_MODAL));
        largeModal.click();

        WebElement largeModalText = driver.findElement(By.id(LARGE_MODAL_TEXT));
        String actualMessage = largeModalText.getText();
        assertEquals("Large Modal", actualMessage);
    }

    @Test
    void testRadioButton() {
        WebElement elementCard = driver.findElement(ELEMENTS_CARD);
        elementCard.click();

        WebElement radioButtonMenuItem = driver.findElement(RADIO_MENU_ITEM);
        radioButtonMenuItem.click();

        WebElement yesRadio = driver.findElement(By.id(YES_RADIO));
        ((JavascriptExecutor) driver).executeScript("arguments[0].checked = true;", yesRadio);
        //WebElement yesRadioMessage = driver.findElement(By.className(YES_RADIO_MESSAGE)); Original code
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement yesRadioMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(YES_RADIO_MESSAGE)));
        String actualMessage = yesRadioMessage.getText();
        assertEquals("Yes", actualMessage);
    }


}
