package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GenerateResultPage extends BasePage {
    private static final int NUMBER_OF_ELEMENT=0;
    @FindBy(xpath = "//div[@id='lipsum']//p")
    private List<WebElement> listOfParagraphs;

    public WebElement getFirstParagraph() {
        return listOfParagraphs.get(NUMBER_OF_ELEMENT);
    }

    public List<WebElement> getListOfParagraphs() {
        return listOfParagraphs;
    }


    public GenerateResultPage(WebDriver driver) {
        super(driver);
    }
}
