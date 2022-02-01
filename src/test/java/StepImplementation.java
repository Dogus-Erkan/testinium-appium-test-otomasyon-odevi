import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.List;
import java.util.Random;


public class StepImplementation extends BaseTest {
    Logger logger = LogManager.getLogger(StepImplementation.class);

    @Step("<time> saniye bekle")
    public void waitForSeconds(long second) {
        try {
            Thread.sleep(second * 1000);
            logger.info(second+" saniye beklenildi");
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.info(e+" hata mesajı verdi");
        }
    }

    @Step("<id> id li elemente tıkla")
    public void clickById(String id) {
        appiumDriver.findElement(By.id(id)).click();
        logger.info(id+" id li elemente tıklanıldı");
    }

    @Step("<xpath> xpath li elemente tıkla")
    public void clickByXpath(String xpath) {
        appiumDriver.findElement(By.xpath(xpath)).click();
        logger.info(xpath+" xpath li elemente tıklanıldı");
    }

    @Step("<id> id li elementi bul ve <text> textini içeriyor mu kontol et")
    public void textControlById(String id, String text) {
        Assert.assertTrue("Element verilen text değerini içermiyor", appiumDriver.findElement(By.id(id)).getText().contains(text));
        logger.info("Verilen :" + text + " değerini içeriyor");
    }

    @Step("<xpath> xpath li elementi bul ve <text> textini içeriyor mu kontol et")
    public void textControlByXpath(String xpath, String text) {
        Assert.assertTrue("Element verilen text değerini içermiyor", appiumDriver.findElement(By.xpath(xpath)).getText().contains(text));
        logger.info("Verilen :" + text + " değerini içeriyor");
    }

    @Step("Sayfayı En Aşağıya 2 Kez Kaydır")
    public void scrollToEnd() {

        final int PRESS_TIME = 200; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;
        // init screen variables
        Dimension dims = appiumDriver.manage().window().getSize();
        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
        for (int i = 0; i < 2; i++) {
            new TouchAction(appiumDriver)
                    .press(pointOptionStart)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        }
        logger.info("Sayfa 2 kere aşağıya scroll edildi");
    }

    @Step("<xpath> xpathi ile rastgele ürün seç")
    public void addRandomProductByXpath(String xpath) {
        Random Random = new Random();
        List<MobileElement> productList = appiumDriver.findElements(By.xpath(xpath));
        int index = Random.nextInt(productList.size());
        productList.get(index).click();
        logger.info(productList.size() + " element içerisinden " + index + " değerini random seçti");
    }

    public boolean returnElementVisibleByXpath(String xpath) {
        try {
            appiumDriver.findElement(By.xpath(xpath));
            logger.info("Xpath İle Aranılan Element Sayfada Bulundu");
            return true;
        } catch (Exception e) {
            logger.info("Xpath İle Aranılan Element Sayfada Bulunamadı");
            return false;
        }
    }

    public boolean returnElementVisibleById(String id) {
        try {
            appiumDriver.findElement(By.id(id));
            logger.info("Id İle Aranılan Element Sayfada Bulundu");
            return true;
        } catch (Exception e) {
            logger.info("Id İle Aranılan Element Sayfada Bulunamadı");
            return false;
        }
    }

    @Step("<xpath> xpath ile sayfada element var mı kontrol et")
    public void isElementVisibleByXpath(String xpath) {
        Assert.assertTrue("Verilen Element Yok ", returnElementVisibleByXpath(xpath));
        logger.info(xpath+" xpath li element sayfada bulundu");
    }

    @Step("<id> id ile sayfada element var mı kontrol et")
    public void isElementVisibleById(String id) {
        Assert.assertTrue("Verilen Element Yok ", returnElementVisibleById(id));
        logger.info(id+" id li element sayfada bulundu");
    }

    @Step("<input> input kısmına <text> değerini yazdır")
    public void sendKeysById(String id, String text) {
        appiumDriver.findElement(By.id(id)).sendKeys(text);
        logger.info("Input kısmına "+text+ " değeri gönderildi.");
    }

    @Step("Pantolon İçin Geçerli Beden Seç")
    public void selectAvailableSize() {
        Random Random = new Random();
        List<MobileElement> productList = appiumDriver.findElements(By.id("com.ozdilek.ozdilekteyim:id/tvInSizeItem"));
        int index = Random.nextInt(productList.size());
        productList.get(index).click();
        logger.info(productList.size() + " element içerisinden " + index + " değerini random seçti");
    }
}
