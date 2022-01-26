package pages;

import base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.RandomNumber;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class MedicationEditPage extends BasePage {
    @FindBy(xpath = "//input[contains(@id,'patientTypeAhead-ember')]")
    private WebElement patientField;

    @FindBy(xpath = "//div[text() = ' - P00201']")
    private WebElement patientNumber;

    @FindBy(xpath = "//select[contains(@id,'visit-ember')]")
    private WebElement visitList;

    @FindBy(xpath = "//input[contains(@id,'inventoryItemTypeAhead-ember')]")
    private WebElement medicationField;

    @FindBy(xpath = "//div[contains(@class,'tt-dataset-1')]/div")
    private WebElement medicationNumber;

    @FindBy(className = "ember-text-area")
    private WebElement prescriptionField;

    @FindBy(xpath = "//input[contains(@id,'display_prescriptionDate')]")
    private WebElement dataField;

    @FindBy(xpath = "//input[contains(@id, 'quantity-ember')]")
    private WebElement quantityRequestField;

    @FindBy(xpath = "//input[contains(@id, 'refills-ember')]")
    private WebElement refillsField;

    @FindBy(xpath = "//button[text() = 'Add']")
    private WebElement addRequestButton;

    public MedicationEditPage(WebDriver driver) {
        super(driver);
    }

    @Step("Selected name of patient")
    public void fillPatientField() {
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        patientField.sendKeys("Test Patient");

        while (true) {
            List<WebElement> elements = getDriver().findElements(By.xpath("//pre[text()='Test Patient']"));
            patientField.sendKeys(Keys.BACK_SPACE);
            patientField.sendKeys("t");
            if (elements.size() > 0) {
                patientNumber.click();
                break;
            }
        }
    }

    @Step("Selected date for visit")
    public void selectVisitData() {
        Select select = new Select(visitList);
        select.selectByIndex(1);
    }

    @Step("Selected medication for patient")
    public void fillMedicationField() {
        medicationField.sendKeys("Pramoxine");

        while (true) {
            List<WebElement> elements = getDriver().findElements(By.xpath("//pre[text()='Pramoxine']"));
            medicationField.sendKeys(Keys.BACK_SPACE);
            medicationField.sendKeys("e");
            if (elements.size() > 0) {
                medicationNumber.click();
                break;
            }
        }
    }

    @Step("Filled prescription")
    public void fillPrescriptionField() {
        prescriptionField.sendKeys("Testing prescription");
    }

    @Step("Selected prescription date 1 day before today")
    public void selectPrescriptionData() {
        LocalDate dateMinusDay = LocalDate.now().minusDays(1);
        String date = String.format("%s/%s/%s", dateMinusDay.getMonthValue(), dateMinusDay.getDayOfMonth(), dateMinusDay.getYear());
        dataField.clear();
        dataField.sendKeys(date);
        dataField.sendKeys(Keys.ENTER);
    }

    @Step("Filled quantity request with random number from 1 to 5")
    public void fillQuantityField() {
        int randomNumber = RandomNumber.getRandomNumber(1, 5);
        quantityRequestField.sendKeys(String.valueOf(randomNumber));
    }

    @Step("Filled refills with random number from 5 to 10")
    public void fillRefillsField() {
        int randomNumber = RandomNumber.getRandomNumber(5, 10);
        refillsField.sendKeys(String.valueOf(randomNumber));
    }

    @Step("Form was added")
    public void clickOnAddRequestButton() {
        addRequestButton.click();
    }
}
