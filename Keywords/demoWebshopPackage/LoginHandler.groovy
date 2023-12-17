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

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import com.kms.katalon.core.webui.common.WebUiCommonHelper

public class LoginHandler {
	static void login () {
		//click login
		WebUI.click(findTestObject('Object Repository/Page_Demo Web Shop/a_Log in'))
		// enter email address
		WebUI.setText(findTestObject('Object Repository/Page_Demo Web Shop. Login/input_Email'), 'guusveen@gmail.com')
		// enter password
		WebUI.setEncryptedText(findTestObject('Object Repository/Page_Demo Web Shop. Login/input_Password'), 'cvzxAwMEC6c=')
		// press log in button
		WebUI.click(findTestObject('Object Repository/Page_Demo Web Shop. Login/input_button-1 login-button'))
	}
}
