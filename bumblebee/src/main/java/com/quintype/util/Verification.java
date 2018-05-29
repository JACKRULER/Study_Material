package com.quintype.util;

import com.quintype.pom.WorkspacePage;
import com.quintype.pom.EntitiesManagerPage;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.openqa.selenium.By;

public class Verification extends OpenBrowser {
	public static boolean verify(String actualString, String expectedString) {
		if (actualString.equalsIgnoreCase(expectedString))
			return true;
		else
			return false;
	}

	public static boolean verify(int actualValue, int expectedValue) {
		if (actualValue == expectedValue)
			return true;
		else
			return false;
	}

	public static boolean verifyStoryOnWorkspace(String storyState, String storyTitle) {
		int inc = 1;
		boolean flag = false;
		WorkspacePage workspacePage = new WorkspacePage(driver);
		Assert.assertEquals(storyState, workspacePage.getActiveTabName(),
				"After " + storyState + " a story, " + storyState + " tab was not a active tab");
		Assert.assertEquals(true, workspacePage.checkStoriesPresentInTab(),
				"Stories are not displaying in " + storyState + " tab");
		driver.navigate().refresh();
		while (inc <= 20) {
			try {
				Thread.sleep(500 * inc);
			} catch (Exception e) {
			}

			if (Verification.verify(storyTitle, workspacePage.getListOfStoryTitle().get(0).getText())) {
				flag = true;
				break;
			}
			inc++;
		}
		// Reporter.log("Expected story title: "+storyTitle);
		// Reporter.log("Actual story title:
		// "+workspacePage.getListOfStoryTitle().get(0).getText());
		return flag;
	}

	public static boolean verifyEntityOnEntitiesTable(String entityTitle) {
		int inc = 1;
		boolean flag = false;
		EntitiesManagerPage entitiesManagerPage = new EntitiesManagerPage(driver);

		while (inc <= 15) {
			try {
				Thread.sleep(1000 * inc);
			} catch (Exception e) {
			}

			if (Verification.verify(entityTitle,
					entitiesManagerPage.getEntityTableList(entityTitle).get(0).getText())) {
				flag = true;
				break;
			}
			inc++;
		}
		if (flag == false)
			Assert.fail();
		return flag;
	}

	public static boolean verifyExpectedElementPresentInList(List<WebElement> listOfElement, String expectedElement) {
		System.out.println("Size of the list =  "+listOfElement.size());
		for (int i = 0; i < listOfElement.size(); i++) {
			System.out.println(i + listOfElement.get(i).getText());
			if (expectedElement.equalsIgnoreCase(listOfElement.get(i).getText()))
				return true;
		}
		return false;
	}

	public static boolean verifyDuplicateElementsPresentInList(List<WebElement> listOfElement, String expectedElement) {
		int flag = 0;
		for (int i = 0; i < listOfElement.size(); i++) {
			if (expectedElement.equalsIgnoreCase(listOfElement.get(i).getText()))
				flag++;
		}
		if (flag >= 2)
			return true;
		else
			return false;
	}

	public static boolean verifyExpectedElementNotPresentInList(List<WebElement> listOfElement,
			String expectedElement) {
		for (int i = 0; i < listOfElement.size(); i++) {
			if (expectedElement.equalsIgnoreCase(listOfElement.get(i).getText()))
				return false;
		}
		return true;
	}

	public static boolean verifyAllListElementsAreSame(List<WebElement> listOfElement, int numOfStoriesToBeChecked,
			String expectedElement) {
		// numOfStoriesToBeChecked is added to limit the verification, Else the test
		// will run for a long time !
		return listOfElement.stream().limit(numOfStoriesToBeChecked)
				.allMatch(webElement -> expectedElement.equalsIgnoreCase(webElement.getText()));
	}

	public static boolean verifyAllListElementsContainsSame(List<WebElement> listOfElement, String expectedElement) {
		for (int i = 0; i < listOfElement.size(); i++) {
			if (!(listOfElement.get(i).getText().toLowerCase().contains(expectedElement.toLowerCase())))
				return false;
		}
		return true;
	}

	public static boolean compareTwoListOfString(List<WebElement> listOfElement, String[] listOFExpectedValues) {
		Set<String> expectedLinks = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		Set<String> actualLinks = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
		expectedLinks.addAll(Arrays.asList(listOFExpectedValues));

		for (int i = 0; i < listOfElement.size(); i++) {
			actualLinks.add(listOfElement.get(i).getText().trim().toLowerCase());
		}
		actualLinks.remove("");
		Reporter.log("Expected Count: " + expectedLinks.size());
		Reporter.log("Actual Count: " + actualLinks.size());
		if (expectedLinks.containsAll(actualLinks) == true && actualLinks.size() == expectedLinks.size())
			return true;
		else
			return false;
	}

	public static boolean verifyCardpinned(int cardNo) {
		List<WebElement> pins = driver.findElements(By.cssSelector(".card-pin"));
		String pinned = pins.get(cardNo).getAttribute("data-for");
		if (verify(pinned, "pinned-card")) {
			return true;
		} else {
			return false;
		}
	}
}