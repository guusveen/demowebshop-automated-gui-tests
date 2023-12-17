package demoWebshopPackage

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper


import internal.GlobalVariable

public class CartHandler {

	static void emptyShoppingCart() {
		// Navigate to the shopping cart
		WebUI.click(findTestObject('Object Repository/Page_Demo Web Shop/span_Shopping cart'))
		
		// All checkboxes to remove items from cart
		TestObject removeFromCartCheckboxesObject = findTestObject('Object Repository/Page_Demo Web Shop. Shopping Cart/all_removefromcart_checkboxes')

		// The button to empty cart of all checked items
		TestObject updateCartButton = findTestObject('Object Repository/Page_Demo Web Shop. Shopping Cart/updatecart element')

		// Find all matching checkbox elements
		List<WebElement> removeFromCartCheckboxes = WebUiCommonHelper.findWebElements(removeFromCartCheckboxesObject, 7)

		// Iterate and click each checkbox
		for (WebElement checkbox : removeFromCartCheckboxes) {
			WebUI.executeJavaScript("arguments[0].click();", Arrays.asList(checkbox))
		}

		// Click the update cart button to remove all checked products from the cart
		WebUI.click(updateCartButton)
	}
}
