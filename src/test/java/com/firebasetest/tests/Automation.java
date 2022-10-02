package com.firebasetest.tests;

import java.io.IOException;
//import javax.swing.text.html.HTMLDocument.Iterator;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.firebase.basetest.test.Base;
import com.firebase.test.utility.CommonUtilities;

//import com.fasterxml.jackson.databind.deser.Deserializers.Base;

//import net.bytebuddy.implementation.bind.annotation.Morph.Binder.DefaultMethodLocator.Implicit;

public class Automation extends Base {

	@Test(priority = 1)
	public static void loginErrorMessageTestCase1() {
		
		String expected = "Please enter your password.";

		CommonUtilities CU = new CommonUtilities();
		Properties applicationProperties = CU.loadFile("Data");

		enterText("username", CU.getApplicationProperty("valid_username", applicationProperties), "UserName");

		waitUntilVisibilityof("username", "UserName");
		enterText("password", "", "Password");
		clickElement("Login", "Login");
		
		String actual = retrieveText("error", "password invalid");


		if (!expected.equals(actual)) {
			String screenshotPath = Base.captureWindowScreenshot();
			report.logTestAssertionFailed(
					"Assertion failed. " + "Actual Text : " + actual + " Expected tex  : " + expected,
					screenshotPath);
		} else {
			report.logTestPassed("Assertion passed");			
		}

	}

	@Test(priority = 2)
	public static void loginToSalesForceTestCase2() {
		System.out.println("Started running the test");
		System.out.println("After chroome");
		loginToSalesForce();
		captureWindowScreenshot();
		System.out.println("screenshot captured");

	}

	@Test(priority = 3)
	public static void checkRemeberMeTestCase3() {

		CommonUtilities CU = new CommonUtilities();
		Properties applicationProperties = CU.loadFile("Data");
		enterText("username", CU.getApplicationProperty("valid_username", applicationProperties), "UserName");
		waitUntilVisibilityof("username", "UserName");
		enterText("password", CU.getApplicationProperty("valid-pwd", applicationProperties), "Password");
		clickElement("rememberUn", "Remember me checkbox");
		waitUntilVisibilityof("rememberUn", "checkbox");
		clickElement("Login", "Login");
		waitUntilVisibilityof("home_Tab", "Home");
		WebElement searchElement = driver.findElement(By.id("userNavButton"));
		searchElement.click();
		WebElement logout = driver.findElement(By.xpath("//*[@id=\"userNav-menuItems\"]/a[5]"));
		Actions action = new Actions(driver);
		action.moveToElement(logout).click().build().perform();
		System.out.println("logout sucess");

	}

	@Test(priority = 4)
	public static void forgotPasswordTestCase4A() {

		CommonUtilities CU = new CommonUtilities();
		Properties applicationProperties = CU.loadFile("Data");
		enterText("username", CU.getApplicationProperty("valid_username", applicationProperties), "UserName");
		clickElement("forgot_password_link", "Forgot Paasword");
		// waitUntilVisibilityof("header", "Forgot Your password header");

	}

	@Test(priority = 5)
	public static void forgotPasswordTestCase4B() {
		CommonUtilities CU = new CommonUtilities();
		Properties applicationProperties = CU.loadFile("Data");

		try {
			enterText("username", CU.getApplicationProperty("valid_username", applicationProperties), "UserName");

			enterText("password", "345", "Password");
			clickElement("Login", "Login");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 6)
	public static void selectUserMenuDropDownTestCase5() {

		loginToSalesForce();

		waitUntilVisibilityof("home_Tab", "Home");
		clickElement("userNav", "Usermenu");

	}

	@Test(priority = 7)
	public static void clickUserMenuDropDownOptionsTestCase6() {
		selectUserMenuDropDownTestCase5();
		clickElement("//*[@id=\"userNav-menuItems\"]/a[1]", "MyProfile");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		clickElement("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img", "EditProfile");
		waitUntilPageLoads();
		driver.switchTo().frame("contactInfoContentId");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}
		WebElement about = driver.findElement(By.partialLinkText("About"));
		about.click();
		WebElement lastname = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		lastname.clear();
		lastname.sendKeys("Anantatamukal");
		System.out.println("lastname is updated");
		clickElement("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]", "save all");
		driver.switchTo().defaultContent();
		clickElement("publisherAttachTextPost", "post");
		driver.switchTo().frame(0);
		WebElement postmessage = driver.findElement(By.xpath("/html/body"));// *[@id="cke_43_contents"]/iframe
		postmessage.clear();
		postmessage.sendKeys("This is my first automated post");
		System.out.println("Automated post message sucess");
		driver.switchTo().defaultContent();
		WebElement share = driver.findElement(By.id("publishersharebutton"));
		share.click();
		System.out.println("share is clicked");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WebElement file = driver.findElement(By.id("publisherAttachContentPost"));
		file.click();

		System.out.println("File is clicked");
		WebElement uploadFile = driver.findElement(By.id("chatterUploadFileAction"));
		uploadFile.click();
		System.out.println("uploadFile is clicked");
		System.out.println("File is clicked");
		WebElement uploadChooseFile = driver.findElement(By.id("chatterFile"));
		System.out.println(" choose file is found");

		System.out.println("uploadChooseFile is clicked");
		uploadChooseFile.sendKeys("D:\\amrutha\\Code.txt");
		System.out.println("File is choosen");
		moveToElement("//*[@id=\"photoSection\"]/span[2]/img[1]", "profile photo");
		clickElement("//*[@id=\"uploadLink\"]", "Add  photo");
		switchToIframe("uploadPhotoContentId", "browse photo frame");
		WebElement browseimage = driver.findElement(By.xpath("//input[@id='j_id0:uploadFileForm:uploadInputFile']"));
		browseimage.sendKeys("C:\\XboxGames\\Minecraft Launcher\\Content\\StoreLogo.png");
		// clickElement("\"//*[@id=\\\"uploadPhotoContentId\\\"]\")", "image upload");
		clickElement("j_id0:uploadFileForm:uploadBtn", "save");
		clickElement("//*[@id=\"j_id0:j_id7:save\"]", "save");
	}

	@Test(priority = 8)
	public static void selectMySettingsFromUserMenuDropDownTestCase7() {
		selectUserMenuDropDownTestCase5();
		clickElement("//*[@id=\"userNav-menuItems\"]/a[2]", "MySettings");

		clickElement("//*[@id=\"PersonalInfo\"]/a", "PersonalLink"); // amrtha version
		clickElement("//*[@id=\"LoginHistory_font\"]", "LoginHistory");
		clickElement("//*[@id=\"DisplayAndLayout_font\"]", "Dispaly&LayoutLink");
		clickElement("//*[@id=\"CustomizeTabs_font\"]", "CustomizeMyTab");
		clickElement("//*[@id=\"p4\"]", "CustomApp");
		clickElement("//*[@id=\"p4\"]/option[9]", "SalesForceChatter");
		clickElement("//*[@id=\"duel_select_0\"]/option[75]", "ReportsTab");
		clickElement("//*[@id=\"duel_select_0_right\"]/img", "AddButton");
		clickElement("//*[@id=\"EmailSetup\"]/a", "Email");
		clickElement("//*[@id=\"EmailSettings_font\"]", "MyEmailSettings");
		enterText("sender_name", "Amrutha Anantatamukala", "EmailnameField");
		enterText("sender_email", "amrutha.sark@gmail.com", "emailAddressField");
		clickElement("//*[@id=\"useremailSection\"]/table/tbody/tr[7]/td[2]/div/label[1]",
				"AutomaticBccYesRadioButton");
		clickElement("//*[@id=\"bottomButtonRow\"]/input[1]", "save");
		clickElement("//*[@id=\"CalendarAndReminders_font\"]", "CalenderAndReminders");
		clickElement("//*[@id=\"Reminders_font\"]", "ActivityReminder");
		clickElement("//*[@id=\"testbtn\"]", "OpentestReminderButtton");

	}

	@Test(priority = 9)
	public static void selectDevelopersConsoleOptionFromUsermenudropdownTestCase8() {
		selectUserMenuDropDownTestCase5();
		clickElement("//*[@id=\"userNav-menuItems\"]/a[3]", "DeveloperConsole");
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
				driver.close();
			}
		}
		// switch to the parent window
		driver.switchTo().window(parent);

	}

	@Test(priority = 10)
	public static void selectLogoutoptionFromUsermenudropdownTestCase9() {
		selectUserMenuDropDownTestCase5();
		clickElement("//*[@id=\"userNav-menuItems\"]/a[5]", "Logout");

	}

	@Test(priority = 11)
	public static void createanAccountTestCase10() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Account_Tab\"]/a", "Account");
		clickElement("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input", "NewButton");
		enterText("//*[@id=\"acc2\"]", " Sreenidhik", "Account Name");
		clickElement("//*[@id=\"acc6\"]/option[7]", "Technology Partner");
		clickElement("//*[@id=\"00N4x00000Wf5Es\"]/option[2]", "Customer Priority High");
		clickElement("//*[@id=\"bottomButtonRow\"]/input[1]", "Save");
	}
	@Test(priority = 12)
	public static void createNewViewtTestCase11() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Account_Tab\"]/a", "Account");
		clickElement("//*[@id=\"filter_element\"]/div/span/span[2]/a", "Create New View");
		enterText("//*[@id=\"fname\"]", "SreenidhiKarri", "view name");
		enterText("//*[@id=\"devname\"]", "SreenidhiK", "view Unique name");
		clickElement("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]", "save");

	}
	@Test(priority = 13)
	public static void editviewtTestCase12() {

		loginToSalesForceTestCase2();

		clickElement("//*[@id=\"Account_Tab\"]/a", "Account");
		clickElement("//*[@id=\"fcf\"]/option[2]", "Accountname");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickElement("//*[@id=\"filter_element\"]/div/span/span[2]/a[1]", "edit");
		enterText("//*[@id=\"fname\"]", "Saatvik karri", "new Account name");
		clickElement("//*[@id=\"fcol1\"]/option[2]", "Filter Field Accountname");
		clickElement("//*[@id=\"fop1\"]/option[4]", "Operator");
		enterText("//*[@id=\"fval1\"]", "a", "value");
		clickElement("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]", "save");

	}
	@Test(priority = 14)

	public static void mergeAccountstTestCase13() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Account_Tab\"]/a", "Account");
		clickElement("//*[@id=\"toolsContent\"]/tbody/tr/td[2]/div/div/div/ul/li[4]/span/a", "Merge link");
		enterText("//*[@id=\"srch\"]", "sre", "Find Account Search");
		clickElement("//*[@id=\"stageForm\"]/div/div[2]/div[4]/input[2]", "Find Accounts Button");
		clickElement("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[1]", "Next Button");
		clickElement("//*[@id=\"stageForm\"]/div/div[2]/div[5]/div/input[2]", "merge button Step 2");
		Alert al = driver.switchTo().alert();
		// click on OK to accept with accept()
		al.accept();
		driver.close();
		System.out.println("popup is closed successfully");

	}
	@Test(priority = 15)
	public static void createAccountReportTestCase14() {

		loginToSalesForceTestCase2();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		clickElement("//*[@id=\"Account_Tab\"]/a", "Account");

		clickElement("//a[contains(text(),'Accounts with')]", "Account with last Activity >30");
		clickElement("//input[@name='dateColumn' ]", "Date coloumn field");
		// input[@name='dateColumn' ]
		clickElement("//div[contains(text(),'Created Date')]", "created date");
		System.out.println("created date is clicked");
		clickElement(
				"(//img[starts-with(@id,'ext-gen') and starts-with(@class,'x-form-trigger x-form-date-trigger') ])[1]",
				"From date Calender icon");

		clickElement("//button[@type='button' and contains(text(),'Today')]", "From date Today");
		clickElement(
				"(//img[starts-with(@id,'ext-gen') and starts-with(@class,'x-form-trigger x-form-date-trigger') ])[2]",
				"To date Calender icon");
		clickElement("//button[@type='button' and contains(text(),'Today')]", "To date Today");

		clickElement("(//button[starts-with(@id,'ext-gen') and contains(., 'Save') ])[1]", "save");
		enterText("//*[@id=\"saveReportDlg_reportNameField\"]", "First Report", "Report  name");
		enterText("saveReportDlg_DeveloperName", "Report113", "Report unique name");
		clickElement("//button[starts-with(@id,'ext-gen') and contains(., 'Save and Run Report') ]", "save and run");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		System.out.println("Test 14 passed");

	}
	@Test(priority = 16)
	public static void opportunitiesDropdownTestCase15() {
		loginToSalesForceTestCase2();

		clickElement("Opportunity_Tab", "oppurtunities");

		waitUntilVisibilityof("//a[contains(text(),'Create New View')]", "Create New View");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("fcf", "verify Oppurtunity drop down");
		System.out.println("Test case 15 is complete");

	}
	@Test(priority = 17)
	public static void createanewOptyTestCase16() {
		loginToSalesForceTestCase2();

		clickElement("Opportunity_Tab", "oppurtunities");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input", "New");
		enterText("opp3", "Private", "oppurtunity name");
		enterText("opp4", "mani", "accountname");
		clickElement("//*[@id=\"opp6\"]/option[2]", "lead source Web");
		clickElement("//*[@id=\"ep\"]/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/span/span/a", "closed date");
		clickElement("//*[@id=\"opp11\"]/option[3]", "stage");
		enterText("opp12", "60", "probabitlity");
		enterText("opp17", " ", "primary campaign source");
		clickElement("//*[@id=\"bottomButtonRow\"]/input[1]", "save");
		System.out.println("test case 16 passed");

	}
	@Test(priority = 18)
	public static void testOpportunityPipelineReportyTestCase17() {
		loginToSalesForceTestCase2();
		clickElement("Opportunity_Tab", "oppurtunities");
		clickElement("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[1]/a", "oppurtunities pipelink");

	}
	@Test(priority = 19)
	public static void testStuckOpportunitiesReportTestCase18() {
		loginToSalesForceTestCase2();
		clickElement("Opportunity_Tab", "oppurtunities");
		clickElement("//*[@id=\"toolsContent\"]/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]/a", "Stuck Oppurtunities");
	}
	@Test(priority = 20)
	public static void testQuarterlySummaryReportTestCase19() {
		loginToSalesForceTestCase2();
		clickElement("Opportunity_Tab", "oppurtunities");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"quarter_q\"]/option[1]", "Quaterly Summary Interval");
		clickElement("//*[@id=\"open\"]/option[2]", "include Open opputunities (open oppurtunities)");
		clickElement("//*[@id=\"lead_summary\"]/table/tbody/tr[3]/td/input", "Run Report");

	}
	@Test(priority = 21)
	public static void checkLeadsTabLinkTestCase20() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Lead_Tab\"]/a", "Leads");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");

	}
	@Test(priority = 22)
	public static void validateViewDropdownListContentsTestcase21() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Lead_Tab\"]/a", "Leads");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("fcf", "Lead Drop Down Menu");
	}
	@Test(priority = 23)
	public static void functionalityOfTheGoButtonTestcase22() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Lead_Tab\"]/a", "Leads");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("fcf", "Lead Drop Down Menu");
		clickElement("//*[@id=\"fcf\"]/option[2]", "My Unread Leads");
		clickElement("//*[@id=\"userNavLabel\"]", "User Menu");
		clickElement("//*[@id=\"userNav-menuItems\"]/a[5]", "Logout");
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Lead_Tab\"]/a", "Leads");
		clickElement("//*[@id=\"filter_element\"]/div/span/span[1]/input", "Go");

	}
	@Test(priority = 24)
	public static void listItemTodaysLeadsWorkTestcase23() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Lead_Tab\"]/a", "Leads");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("fcf", "Lead Drop Down Menu");
		clickElement("//*[@id=\"fcf\"]/option[4]", "Todays Lead");
		clickElement("//*[@id=\"filter_element\"]/div/span/span[1]/input", "Go");

	}
	@Test(priority = 25)
	public static void checkNewButtonOnLeadsHomeTestcase24() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Lead_Tab\"]/a", "Leads");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input", "New");
		enterText("name_lastlea2", "ABCD", "LastName");
		enterText("lea3", "ABCD", "CompanyName");
		clickElement("//*[@id=\"bottomButtonRow\"]/input[1]", "save");

	}
	@Test(priority = 26)
	public static void createNewContactTestcase25() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Contact_Tab\"]/a", "Contact");
		clickElement("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input", "New");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		enterText("name_lastcon2", "Karri", "lastname");
		enterText("//*[@id=\"con4\"]", "mani", "Account Name");
		clickElement("//*[@id=\"bottomButtonRow\"]/input[1]", "save");
	}
	@Test(priority = 27)
	public static void createNewViewintheContactPageTestCase26() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Contact_Tab\"]/a", "Contact");
		clickElement("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]", "Create New View Link");
		enterText("fname", "Raghavendra", "View Name");
		enterText("devname", "karri1", "View Unique Name");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]", "save");

	}
	@Test(priority = 28)
	public static void checkRecentlyCreatedContactInTheContactPageTestCase27() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Contact_Tab\"]/a", "Contact");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"hotlist_mode\"]/option[1]", "Recently Created");
	}
	@Test(priority = 29)
	public static void checkMyContactsViewInTheContactPageTestCase28() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Contact_Tab\"]/a", "Contact");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"fcf\"]/option[7]", "My contact");
		clickElement("//*[@id=\"filter_element\"]/div/span/span[1]/input", "Go");

	}
	@Test(priority = 30)
	public static void viewContactInTheContactPageTestCase29() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Contact_Tab\"]/a", "Contact");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"bodyCell\"]/div[3]/div[1]/div/div[2]/table/tbody/tr[2]/th/a", "Karri");
	}
	@Test(priority = 31)
	public static void viewContactInTheContactPageTestCase30() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Contact_Tab\"]/a", "Contact");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]", "Create New View");
		enterText("devname", "EFGH", "View Unique Name");
		clickElement("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]", "Save");

	}
	@Test(priority = 32)
	public static void checkTheCancelButtonWorksFineInCreateNewViewTestCase31() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Contact_Tab\"]/a", "Contact");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"filter_element\"]/div/span/span[2]/a[2]", "Create New View");

		enterText("//*[@id=\"fname\"]", "ABCD", "View  Name");
		enterText("devname", "EFGH", "View Unique Name");
		clickElement("//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[2]", "Cancel");

	}
	@Test(priority = 33)

	public static void checkTheSaveAndNewButtonWorksInNewContactPageTestCase32() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"Contact_Tab\"]/a", "Contact");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"hotlist\"]/table/tbody/tr/td[2]/input", "New Button");
		enterText("name_lastcon2", "Indian", "Last Name");
		enterText("con4", "Global Media", "Account Name");
		clickElement("//*[@id=\"bottomButtonRow\"]/input[2]", "Save & New");
		String screenshotPath = Base.captureWindowScreenshot();
		report.logTestScreenShot("Screenshot",screenshotPath);
		
		

	}
	@Test(priority = 34)
	public static void verifyIfTheFirstnameAndLastnameOfTheLoggedInUserIsDisplayedTestCase33() {
		loginToSalesForceTestCase2();
		String expectedText = "Amrutha Anantatamukal";
		// String Expected
		// =getTextOfWebElement("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a",
		// "expected");
		clickElement("//*[@id=\"home_Tab\"]/a", "home");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		String expectedUrl = createWebElement("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a").getAttribute("href");
		System.out.println(expectedUrl);
		validateText("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a", expectedText);
		clickElement("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a", "userlink");
		String expectedTitle = "User: Amrutha Anantatamukal ~ Salesforce - Developer Edition";
		String screenshotPath = Base.captureWindowScreenshot();
		report.logTestScreenShot("Screenshot",screenshotPath);
		
	}
	@Test(priority = 35)
	public static void verifytheEditedLastnameIsUpdatedAtVariousPlacesTestCase34() {
		loginToSalesForceTestCase2();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		clickElement("//*[@id=\"home_Tab\"]/a", "home");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"ptBody\"]/div/div[2]/span[1]/h1/a", "userlink");

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clickElement("//*[@id=\"chatterTab\"]/div[2]/div[2]/div[1]/h3/div/div/a/img", "EditProfile");

		driver.switchTo().frame("contactInfoContentId");
		/*
		 * try { Thread.sleep(6000); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		WebElement about = driver.findElement(By.partialLinkText("About"));
		about.click();
		WebElement lastname = driver.findElement(By.xpath("//*[@id=\"lastName\"]"));
		lastname.clear();
		lastname.sendKeys("Abcd");
		clickElement("//*[@id=\"TabPanel\"]/div/div[2]/form/div/input[1]", "saveAll button");
		String screenshotPath = Base.captureWindowScreenshot();
		report.logTestScreenShot("Screenshot",screenshotPath);
		
		// driver.close();

	}
	@Test(priority = 36)

	public static void verifyTheTabCustomizationTestcase35() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"AllTab_Tab\"]/a/img", "All Tab +");
		clickElement("//*[@id=\"bodyCell\"]/div[3]/div[1]/table/tbody/tr/td[2]/input", "Customize My Tabs");
		clickElement("//*[@id=\"duel_select_1\"]/option[2]", "Libraries From Select Tab");
		clickElement("//*[@id=\"duel_select_0_left\"]/img", "Remove");
		clickElement("//*[@id=\"bottomButtonRow\"]/input[1]", "save");
		clickElement("userNavLabel", "user menu");
		clickElement("//*[@id=\"userNav-menuItems\"]/a[5]", "LogOut");
		loginToSalesForceTestCase2();
		String screenshotPath = Base.captureWindowScreenshot();
		report.logTestScreenShot("Screenshot",screenshotPath);
		

	}
	@Test(priority = 37)
	public static void blockingAnEventInTheCalendertestCase36() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"home_Tab\"]/a", "home");
		clickElement("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a", "Current Date link");
		clickElement("//a[contains(text(),'8:00 PM')]", "Current Date link");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img", "Subject combo Box");
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {

				driver.switchTo().window(child_window);

				clickElement("//a[contains(text(),'Other')]", "others");
				// driver.close();
			}
		}
		// switch to the parent window
		driver.switchTo().window(parent);
		waitUntilPageLoads();
		moveToElement("EndDateTime_time", "end date time");
		clickElement("EndDateTime_time", "end date time");
		clickElement("timePickerItem_42", "end time 9 pm");
		clickElement("//*[@id=\"bottomButtonRow\"]/input[1]", "save");
		
		String screenshotPath = Base.captureWindowScreenshot();
		report.logTestScreenShot("Screenshot",screenshotPath);
		

	}
	@Test(priority = 38)

	public static void blockingAnEventInTheCalenderWithWeeklyRecurranceTestCase37() {
		loginToSalesForceTestCase2();
		clickElement("//*[@id=\"home_Tab\"]/a", "home");
		clickElement("//*[@id=\"ptBody\"]/div/div[2]/span[2]/a", "Current Date link");
		clickElement("//a[contains(text(),'4:00 PM')]", "Current Date link");
		closeunimportantPoPupWindows("//*[@id=\"tryLexDialogX\"]", "popup");
		clickElement("//*[@id=\"ep\"]/div[2]/div[4]/table/tbody/tr[2]/td[2]/div/a/img", "Subject combo Box");
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {

				driver.switchTo().window(child_window);

				clickElement("//a[contains(text(),'Other')]", "others");
				// driver.close();
			}
		}
		// switch to the parent window
		driver.switchTo().window(parent);
		waitUntilPageLoads();
		moveToElement("EndDateTime_time", "end date time");
		clickElement("EndDateTime_time", "end date time");
		clickElement("timePickerItem_38", "end time 7 pm");
		clickElement("//*[@id=\"ep\"]/div[2]/div[8]/table/tbody/tr[1]/td/div/label", "Check recurrence");
		clickElement("//*[@id=\"recpat\"]/table/tbody/tr[1]/td[2]/table/tbody/tr/td[1]/div/div[2]/label",
				"Weekly radio button ");
		clickElement("RecurrenceEndDateOnly", "RecurrenceEndDateOnly ");
		clickElement("//*[@id=\"calRow3\"]/td[7]", "After 2 weeks date ");
		clickElement("//*[@id=\"bottomButtonRow\"]/input[1]", "save ");
		System.out.println("Test going to open the monthly view now");
		clickElement("//*[@id=\"bCalDiv\"]/div/div[2]/span[2]/a[3]/img", "monthly view ");
		System.out.println("Test blockingAnEventInTheCalenderWithWeeklyRecurranceTestCase37 is complete");
		
		String screenshotPath = Base.captureWindowScreenshot();
		report.logTestScreenShot("Screenshot",screenshotPath);
		

	}

}
