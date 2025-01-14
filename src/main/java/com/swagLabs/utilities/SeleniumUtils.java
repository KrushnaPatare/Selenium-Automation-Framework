package com.swagLabs.utilities;

import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SeleniumUtils 
{  
	WebDriver driver = null;
    public SeleniumUtils(WebDriver driver) 
    {
        this.driver = driver;
    }

 // Browser Navigation
    public void refreshBrowser() 
    {
        try 
        {
            driver.navigate().refresh();
            LogUtils.info("Browser refreshed successfully.");
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to refresh the browser: " + e.getMessage());
            LogUtils.logException(e);
        }
    }

    public void navigateBackward() 
    {
        try 
        {
            driver.navigate().back();
            LogUtils.info("Navigated backward successfully.");
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to navigate backward: " + e.getMessage());
            LogUtils.logException(e);
        }
    }

    public void navigateForward() 
    {
        try 
        {
            driver.navigate().forward();
            LogUtils.info("Navigated forward successfully.");
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to navigate forward: " + e.getMessage());
            LogUtils.logException(e);
        }
    }

    // Alerts
    public void dismissAlert() 
    {
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.dismiss();
            LogUtils.info("Alert dismissed successfully.");
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to dismiss alert: " + e.getMessage());
            LogUtils.logException(e);
        }
    }

    // Frames and Windows
    public void switchToFrameByIndex(int index) 
    {
        try 
        {
            driver.switchTo().frame(index);
            LogUtils.info("Switched to frame at index: " + index);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to switch to frame by index: " + e.getMessage());
            LogUtils.logException(e);
        }
    }

    public void switchToWindowByTitle(String title) 
    {
        try 
        {
            Iterator<String> windowHandles = driver.getWindowHandles().iterator();
            while (windowHandles.hasNext()) 
            {
                String handle = windowHandles.next();
                driver.switchTo().window(handle);
                if (driver.getTitle().equals(title)) 
                {
                    LogUtils.info("Switched to window with title: " + title);
                    break;
                }
            }
            LogUtils.error("No window found with title: " + title);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to switch to window by title: " + e.getMessage());
            LogUtils.logException(e);
        }
    }

    public void switchToWindowByUrlBase(String baseUrl) 
    {
        try 
        {
            Iterator<String> windowHandles = driver.getWindowHandles().iterator();
            while (windowHandles.hasNext()) 
            {
                String handle = windowHandles.next();
                driver.switchTo().window(handle);
                String currentUrl = driver.getCurrentUrl();
                if (currentUrl.split("\\?")[0].equals(baseUrl)) 
                {
                    LogUtils.info("Switched to window with base URL: " + baseUrl);
                    return;
                }
            }
            LogUtils.error("No window found with base URL: " + baseUrl);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to switch to window by base URL: " + e.getMessage());
            LogUtils.logException(e);
        }
    }

    
    // Waiting Mechanisms
    public void explicitlyWaitForElement(WebElement element, int waitTimeInSeconds) 
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTimeInSeconds));
        try 
        {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            LogUtils.info("Element is clickable: " + element);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Error waiting for element to be clickable: " + e.getMessage());
            LogUtils.logException(e);
        }
    }

    
    public void waitForSeconds(int seconds) throws InterruptedException 
    {
        long time = 1000 * seconds;
        Thread.sleep(time);
    }

    
    public void waitForElementAndClick(WebElement element, int waitTimeInSeconds) 
    {
        try 
        {
            explicitlyWaitForElement(element, waitTimeInSeconds);
            element.click();
            LogUtils.info("Clicked successfully: " + element);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to click element: " + e.getMessage());
            LogUtils.logException(e);
            Assert.fail("Failed to click element");
        }
    }

    // Scrolling
    public void scrollToElement(WebElement element) 
    {
        try 
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            LogUtils.info("Scrolled to the element: " + element.toString()); 
        } 
        catch (Exception e) 
        {
            LogUtils.info("Failed to scroll to element: {}" + e.getMessage());
        }
    }
    

    public void waitScrollAndClick(WebElement element, int waitTimeInSeconds) 
    {
        try 
        {
            explicitlyWaitForElement(element, waitTimeInSeconds);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            LogUtils.info("Scrolled to the element: " + element.toString());
            element.click();
            LogUtils.info("Clicked successfully: " + element);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to wait, scroll, and click the element: " + e.getMessage());
            LogUtils.logException(e);
            Assert.fail("Failed to wait, scroll, and click the element");
        }
    }

    
    // Mouse Actions
    public void doubleClickElement(WebElement element) 
    {
        try 
        {
            Actions actions = new Actions(driver);
            actions.doubleClick(element).perform();
            LogUtils.info("Double-clicked on the element: " + element);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to double-click element: " + e.getMessage());
            LogUtils.logException(e);
            Assert.fail("Failed to double-click element");
        }
    }
    

    public void rightClickElement(WebElement element) 
    {
        try 
        {
            Actions actions = new Actions(driver);
            actions.contextClick(element).perform();
            LogUtils.info("Right-clicked on the element: " + element);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to right-click element: " + e.getMessage());
            LogUtils.logException(e);
            Assert.fail("Failed to right-click element");
        }
    }

    
    public void hoverOverElement(WebElement element) 
    {
        try 
        {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
            LogUtils.info("Hovered over the element: " + element);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to hover over element: " + e.getMessage());
            LogUtils.logException(e);
            Assert.fail("Failed to hover over element");
        }
    }

    // Clicking Multiple Elements
    public void clickElementsInReverseOrder(List<WebElement> elements) throws InterruptedException 
    {
        if (elements == null || elements.isEmpty()) 
        {
            LogUtils.warn("No elements to click.");
            return;
        }

        try 
        {
            int size = elements.size();
            for (int i = size - 1; i >= 0; i--) 
            {
                WebElement element = elements.get(i);
                scrollToElement(element);
                waitForElementAndClick(element, 2);
                LogUtils.info("Clicked successfully on element at index " + i + ": " + element);
            }
        } 
        catch (Exception e) 
        {
            LogUtils.error("Error while clicking on multiple elements: " + e.getMessage());
            LogUtils.logException(e);
            Assert.fail("Failed to click on multiple elements");
        }
    }

    // Zoom
    public void adjustPageZoom(int zoomPercentage) 
    {
        try 
        {
            if (zoomPercentage < 10 || zoomPercentage > 500) 
            {
                LogUtils.error("Invalid zoom percentage: " + zoomPercentage);
                throw new IllegalArgumentException("Zoom percentage must be between 10 and 500.");
            }

            double zoomLevel = zoomPercentage / 100.0;
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.body.style.zoom='" + zoomLevel + "'");
            LogUtils.info("Zoom adjusted to level: " + zoomPercentage + "%");
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to adjust zoom level: " + e.getMessage());
            LogUtils.logException(e);
        }
    }

    
    
    public void uploadFile(WebElement uploadElement, String filePath) 
    {
        try 
        {
            uploadElement.sendKeys(filePath);
            LogUtils.info("File uploaded successfully: " + filePath);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to upload file: " + e.getMessage());
            LogUtils.logException(e);
        }
    }

    
    
    public static String getFormattedDateTime(String pattern) 
    {
        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern(pattern);
        LocalDateTime time = LocalDateTime.now();
        return time.format(customFormat);
    }
    
    
    ////////////////////////////////////////////////////////

    public void switchToFrameByNameOrId(String nameOrId) 
    {
        try 
        {
            driver.switchTo().frame(nameOrId);
            LogUtils.info("Switched to frame: {}" + nameOrId);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to switch to frame: {}" + e.getMessage());
        }
    }

    
    public void switchToDefaultContent() 
    {
        try 
        {
            driver.switchTo().defaultContent();
            LogUtils.info("Switched to default content");
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to switch to default content: {}" + e.getMessage());
        }
    }

    
    public boolean isFileDownloaded(String downloadPath, String fileName) 
    {
        File file = new File(downloadPath + File.separator + fileName);
        boolean exists = file.exists();
        if (exists) 
        {
            LogUtils.info("File is downloaded successfully: {}" + fileName);
        } 
        else 
        {
            LogUtils.error("File is not downloaded: {}" + fileName);
        }
        return exists;
    }

    
    public WebElement fluentWaitForElement(By locator, long timeout, long pollingTime) 
    {
        try 
        {
            Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeout))
                .pollingEvery(Duration.ofMillis(pollingTime))
                .ignoring(NoSuchElementException.class);
            WebElement element = wait.until(driver -> driver.findElement(locator));
            LogUtils.info("Element located with fluent wait: {}" + locator);
            return element;
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to locate element with fluent wait: {}" + e.getMessage());
            return null;
        }
    }

    
    public void highlightWebElement(WebElement element) 
    {
        try 
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].style.border='3px solid red'", element);
            LogUtils.info("Highlighted element: {}" + element);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to highlight element: {}" + e.getMessage());
        }
    }

    
    public void closeAllOtherWindows() 
    {
        try 
        {
            String mainWindow = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) 
            {
                if (!handle.equals(mainWindow)) 
                {
                    driver.switchTo().window(handle).close();
                }
            }
            driver.switchTo().window(mainWindow);
            LogUtils.info("Closed all other windows successfully");
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to close other windows: {}" + e.getMessage());
        }
    }

    
    public boolean isElementPresent(WebElement element) 
    {
        try 
        {
            return element.isDisplayed() && element.isEnabled();
        } 
        catch (NoSuchElementException | StaleElementReferenceException e)
        {
            LogUtils.error("Element '{}' is not present: {}" + element + e.getMessage());
            return false;
        }
    }
    

    public void selectOptionByVisibleText(WebElement element, String visibleText) 
    {
        try 
        {
            Select select = new Select(element);
            select.selectByVisibleText(visibleText);
            LogUtils.info("Selected '{}' from the dropdown: {}" + visibleText + element);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to select '{}' from dropdown: {}" + visibleText + e.getMessage());
            Assert.fail("Failed to select value from dropdown");
        }
    }

    
    public void selectOptionFromReactDropdown(WebElement dropdown, String optionText) 
    {
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Actions actions = new Actions(driver);

            wait.until(ExpectedConditions.elementToBeClickable(dropdown));
            actions.moveToElement(dropdown).click().perform();
            LogUtils.info("Clicked on the dropdown.");

            String optionXPath = String.format("//li[contains(text(), '%s')]", optionText);
            WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(optionXPath)));
            actions.moveToElement(option).click().perform();
            LogUtils.info("Selected option: " + optionText);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to select option from React dropdown: " + e.getMessage());
        }
    }

    
    public void sendKeysMethod(WebElement element, String value, int waitTimeInSeconds) 
    {
        try 
        {
            explicitlyWaitForElement(element, waitTimeInSeconds);
            element.clear();
            element.sendKeys(value);
            LogUtils.info(String.format("Entered '%s' into element: %s", value, element));
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to send keys to element: {}" + e.getMessage());
            Assert.fail("Failed to send keys to element");
        }
    }

    
    public void waitScrollAndSendKeys(WebElement element, WebDriver driver, int waitTimeInSeconds, String keysToSend) 
    {
        try 
        {
            explicitlyWaitForElement(element, waitTimeInSeconds);
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element);
            LogUtils.info("Scrolled to the element: " + element.toString());
            element.sendKeys(keysToSend);
            LogUtils.info("Keys sent successfully to the element: " + element);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to wait, scroll, and send keys to the element: " + e.getMessage());
            LogUtils.logException(e);
            Assert.fail("Failed to wait, scroll, and send keys to the element");
        }
    }
    
/*
    public void verifyTextElement(String expectedText, WebElement actualElement, String elementDescription, int waitTimeInSeconds) 
    {
        try 
        {
            explicitlyWaitForElement(actualElement, waitTimeInSeconds);
            String actualText = actualElement.getText();
            if (actualText.equals(expectedText)) 
            {
                LogUtils.info("{}: '{}' is verified successfully" + elementDescription + expectedText);
            } 
            else 
            {
                LogUtils.error("{}: '{}' does not match actual text '{}'" + elementDescription + expectedText + actualText);
                Assert.fail(elementDescription + ": Text verification failed");
            }
        } 
        catch (Exception e) 
        {
            LogUtils.error("Error verifying text of element: {}" + e.getMessage());
            Assert.fail("Text verification failed");
        }
    }
*/
    
    public String getAlertText() 
    {
        String alertText = "";
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alertText = alert.getText();
            LogUtils.info("Alert text retrieved successfully: " + alertText);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to retrieve alert text: " + e.getMessage());
        }
        return alertText;
    }

    public void acceptAlert() 
    {
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
            LogUtils.info("Alert accepted successfully");
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to accept alert: {}" + e.getMessage());
        }
    }

    public void handlingAutoSuggestion(String xpathExpression, String expectedValue) 
    {
        try 
        {
            List<WebElement> suggestions = driver.findElements(By.xpath(xpathExpression));
            for (WebElement suggestion : suggestions) 
            {
                if (suggestion.getText().equals(expectedValue)) 
                {
                    suggestion.click();
                    LogUtils.info("Clicked on suggestion: {}" + expectedValue);
                    return;
                }
            }
            LogUtils.error("Suggestion '{}' not found" + expectedValue);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Error handling auto-suggestions: {}" + e.getMessage());
        }
    }

    public void openNewTab(String url) 
    {
        try 
        {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            if (url == null || url.isEmpty()) 
            {
                js.executeScript("window.open();");
                LogUtils.info("Opened a blank new tab.");
            } 
            else  
            {
                js.executeScript("window.open(arguments[0], '_blank');", url);
                LogUtils.info("Opened a new tab with URL: " + url);
            }
        } 
        catch (Exception e) 
        {
            LogUtils.error("Error while opening a new tab: " + e.getMessage());
        }
    }

    
    public void enterTextInSearchBar(WebElement ele, String txt) 
    {
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            ele.clear();
            Actions actions = new Actions(driver);
            actions.moveToElement(ele)
                   .click()
                   .sendKeys(txt)
                   .sendKeys(Keys.ENTER)
                   .build()
                   .perform();
            LogUtils.info("Entered text into search bar and pressed Enter: " + txt);
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to enter text into search bar and press Enter: " + e.getMessage());
        }
    }

    public List<String> getOptionsFromMultiSelectBox(WebElement multiSelectBox, By optionLocator) 
    {
        List<String> optionsText = new ArrayList<>();
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(multiSelectBox));

            List<WebElement> options = multiSelectBox.findElements(optionLocator);

            for (WebElement option : options) 
            {
                optionsText.add(option.getText());
            }

            LogUtils.info("Retrieved " + options.size() + " options from the multi-selection box.");
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to retrieve options from the multi-selection box: " + e.getMessage());
        }

        return optionsText;
    }

    
    public Map<String, String> mapRowValuesToHeaders(WebElement tableElement, By headerLocator, By rowLocator, int rowIndex) 
    {
        Map<String, String> rowData = new HashMap<>();
        try 
        {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(tableElement));

            List<WebElement> headers = tableElement.findElements(headerLocator);
            List<WebElement> rows = tableElement.findElements(rowLocator);
            if (rowIndex <= 0 || rowIndex > rows.size()) 
            {
                LogUtils.error("Invalid row index: " + rowIndex);
                return rowData;
            }

            WebElement row = rows.get(rowIndex - 1);
            List<WebElement> cells = row.findElements(By.xpath(".//td"));

            if (headers.size() != cells.size()) 
            {
                LogUtils.error("Mismatch between header count and cell count in row " + rowIndex);
            } 
            else 
            {
                for (int i = 0; i < headers.size(); i++) 
                {
                    String headerText = headers.get(i).getText().trim();
                    String cellText = cells.get(i).getText().trim();
                    rowData.put(headerText, cellText);
                }
                LogUtils.info("Mapped headers to row " + rowIndex + " successfully.");
            }
        } 
        catch (Exception e) 
        {
            LogUtils.error("Failed to map row values to headers: " + e.getMessage());
        }

        return rowData;
    }


    
    
}
























