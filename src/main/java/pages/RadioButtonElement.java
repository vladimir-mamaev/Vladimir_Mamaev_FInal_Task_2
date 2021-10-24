package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class RadioButtonElement extends BasePage {
    @FindBy(xpath = "//tbody//label")
    private List<WebElement> listOfRadioButton;


    public void setValue(final String value) {
        listOfRadioButton.stream().filter(x -> x.getText().contains(value)).forEach(WebElement::click);

    }


    public RadioButtonElement(WebDriver driver) {
        super(driver);
    }
}
