package manager;
import org.openqa.selenium.WebDriver;

import pages.*;
public class PageFactoryManager {
    WebDriver driver;

    public PageFactoryManager(WebDriver driver) {
        this.driver = driver;
    }
    public HomePage getHomePage() {
        return new HomePage(driver);
    }
    public RussianPage getRussianPage(){return new RussianPage(driver);}
    public GenerateResultPage getGenerateResultPage(){return new GenerateResultPage(driver);}
public RadioButtonElement getRadioButtonElement(){return new RadioButtonElement(driver);}

}
