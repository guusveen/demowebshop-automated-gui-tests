import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement

import demoWebshopPackage.CartHandler
import demoWebshopPackage.LoginHandler

final int SMARTPHONE_ID = 43
final int USED_PHONE_ID = 15
final int BELT_ID = 40
final int BLUE_JEANS_ID = 36

BigDecimal item1Price = 0G
BigDecimal item2Price = 0G
BigDecimal totalPrice = 0G

// Make x a random number between 2 and 10
int x = new Random().nextInt(9) + 2

// All the add to cart buttons of the products we want to test
// And all price elements of the products we want to test
TestObject addToCartButtonSmartphone = findTestObject('Object Repository/Page_Demo Web Shop. Cell phones/Smartphone - Add to cart button', [('smartphoneId') : SMARTPHONE_ID.toString()])
TestObject priceTextSmartphone = findTestObject('Object Repository/Page_Demo Web Shop. Cell phones/Smartphone - actual price', [('smartphoneId') : SMARTPHONE_ID.toString()])

TestObject addToCartButtonUsedPhone = findTestObject('Object Repository/Page_Demo Web Shop. Cell phones/Used Phone - Add to cart button', [('usedPhoneId') : USED_PHONE_ID.toString()])
TestObject priceTextUsedPhone = findTestObject('Object Repository/Page_Demo Web Shop. Cell phones/Used Phone - actual price', [('usedPhoneId') : USED_PHONE_ID.toString()])

TestObject addToCartButtonBlueJeans = findTestObject('Object Repository/Page_Demo Web Shop. Apparel  Shoes/Blue jeans - Add to cart button', [('blueJeansId') : BLUE_JEANS_ID.toString()])
TestObject priceTextBlueJeans = findTestObject('Object Repository/Page_Demo Web Shop. Apparel  Shoes/Blue jeans - actual price', [('blueJeansId') : BLUE_JEANS_ID.toString()])

TestObject addToCartButtonGolfBelt = findTestObject('Object Repository/Page_Demo Web Shop. Apparel  Shoes/Golf belt - Add to cart button', [('golfBeltId') : BELT_ID.toString()])
TestObject priceTextGolfBelt = findTestObject('Object Repository/Page_Demo Web Shop. Apparel  Shoes/Golf belt - actual price', [('golfBeltId') : BELT_ID.toString()])

// Element that contains the total price in the shopping cart
TestObject totalPriceElement = findTestObject('Object Repository/Page_Demo Web Shop. Shopping Cart/mini_shopping_cart_total')

// open browser
WebUI.openBrowser('')

// Set viewport size to 1000 by 1000 or the mini-shopping-cart will not show
WebUI.setViewPortSize(1000, 1000)
// navigate to test webshop
WebUI.navigateToUrl('https://demowebshop.tricentis.com/')

// Proactively check if the user is logged on. Log out if so.
if (WebUI.verifyElementPresent(findTestObject('Object Repository/Page_Demo Web Shop/a_Log out'), 2, FailureHandling.OPTIONAL))
{
	WebUI.click(findTestObject('Object Repository/Page_Demo Web Shop/a_Log out'))
}

// Fire the login module
LoginHandler.login()

// Get the cart cuantity span
TestObject cartCuantity = findTestObject('Object Repository/Page_Demo Web Shop/span_cart_cuantity')
WebUI.waitForElementPresent(cartCuantity, 15)
// Get the string value of the cart cuantity span
String cartQuantityText = WebUI.getText(cartCuantity)

// Proactively check if cart cuantity span does not equal '(0)'. Empty the shopping cart if so
if (cartQuantityText != '(0)')
{	
	// Fire the empty shopping cart module
	CartHandler.emptyShoppingCart()
	
	// navigate to the homepage
	WebUI.navigateToUrl('https://demowebshop.tricentis.com/')
}

KeywordUtil.logInfo("item1: " + item1)
KeywordUtil.logInfo("item2: " + item2)
// navigate to electronics
WebUI.click(findTestObject('Object Repository/Page_Demo Web Shop/a_Electronics'))
// click on cell phones
WebUI.click(findTestObject('Object Repository/Page_Demo Web Shop. Electronics/a_Cell phones'))

// if smartphone is selected as paramater add the smart phone x times to the cart
// else add the used phone x times to the cart
if ("smartphone".equals(item1.trim()))
{
	// verify that the add to cart button is there (it won't be there if out of stock)
	if (WebUI.verifyElementPresent(addToCartButtonSmartphone, 5, FailureHandling.OPTIONAL))
	{
		//set item1Price to the converted decimal value of the text in the priceTextSmartphone element
		// Retrieve the price text
		String priceText = WebUI.getText(priceTextSmartphone)
		
		// Convert price text to BigDecimal
		item1Price = new BigDecimal(priceText.replaceAll("[^\\d.]", "")) // Remove any non-numeric characters like currency symbols
		
		for (int i = 0; i < x; i++)
		{
			WebUI.click(addToCartButtonSmartphone)
			totalPrice += item1Price
		} 
		
	}
} else
{
	// Same process as smartphone but for used phone
	if (WebUI.verifyElementPresent(addToCartButtonUsedPhone, 5, FailureHandling.OPTIONAL)) 
	{
        String priceText = WebUI.getText(priceTextUsedPhone)
        item1Price = new BigDecimal(priceText.replaceAll("[^\\d.]", ""))

        for (int i = 0; i < x; i++) 
		{
            WebUI.click(addToCartButtonUsedPhone)
            totalPrice += item1Price
        }
    }
}

// navigate to apparel and shoes
WebUI.click(findTestObject('Object Repository/Page_Demo Web Shop/a_Apparel  Shoes'))

if (item2 == "blue jeans")
{
	// Same process as smartphone but for blue jeans
	if (WebUI.verifyElementPresent(addToCartButtonBlueJeans, 5, FailureHandling.OPTIONAL))
	{
		String priceText = WebUI.getText(priceTextBlueJeans)
		
		item2Price = new BigDecimal(priceText.replaceAll("[^\\d.]", ""))
		
		for (int i = 0; i < x; i++)
		{
			WebUI.click(addToCartButtonBlueJeans)
			totalPrice += item2Price
		}
		
	}
} else
{
	// Same process as smartphone but for golf belt
	if (WebUI.verifyElementPresent(addToCartButtonGolfBelt, 5, FailureHandling.OPTIONAL)) 
	{
		String priceText = WebUI.getText(priceTextGolfBelt)
		item2Price = new BigDecimal(priceText.replaceAll("[^\\d.]", ""))

		for (int i = 0; i < x; i++) 
		{
			WebUI.click(addToCartButtonGolfBelt)
			totalPrice += item2Price
		}
	}
}

// navigate to shopping cart
WebUI.mouseOver(findTestObject('Object Repository/Page_Demo Web Shop/span_Shopping cart'))

WebUI.waitForElementPresent(totalPriceElement, 10)
// get the string value of the total price element
String totalPriceText = WebUI.getText(totalPriceElement)
KeywordUtil.logInfo("Calculated Total Price: " + totalPriceText)
// Convert the cleaned text to BigDecimal
BigDecimal totalPriceValue = new BigDecimal(totalPriceText.replaceAll("[^\\d.]", ""))

// Log the price calculated by us and the price shown in the cart
KeywordUtil.logInfo("Total Price from Web Page: " + totalPriceValue)
KeywordUtil.logInfo("Calculated Total Price: " + totalPrice)

// Assert that the price calculated by us matches the price displayed in the cart
assert totalPriceValue == totalPrice :  "The total price on the web page does not match the calculated total price."

// Fire the empty shopping cart module for cleanup
CartHandler.emptyShoppingCart()

// Log out
WebUI.click(findTestObject('Object Repository/Page_Demo Web Shop/a_Log out'))

// Close browser
WebUI.closeBrowser()
Thread.sleep(5000)