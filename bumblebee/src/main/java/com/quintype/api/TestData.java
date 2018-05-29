package com.quintype.api;

import java.util.List;

import com.quintype.api.PublisherAPI;
import com.quintype.api.StoryAPI;
import com.quintype.util.OpenBrowser;

public class TestData extends OpenBrowser implements Runnable
{
	public static List <String> responseData;
	private static String[] storyState = {"story-submit", "story-approve", "story-publish"};
	
	public void run()
	{
//		createTestStoriesInOpenTab();
//		createTestStoriesInNeedApprovalTab();
//		createTestStoriesInApprovedTab();
//		createTestStoriesInPublishedTab();
	}
	public void createTestStoriesInOpenTab()
	{
		StoryAPI storyAPI = new StoryAPI();
		//	Create 3 stories in Open State
		for (int i = 1; i <= 5; i++) {
			storyAPI.createPhotoStoryInOpenState("This is a photo story title ", i);
			storyAPI.createVideoStoryInOpenState("Title of Video Story ", i);
			storyAPI.createTextStoryInOpenState("Text Story Title ", i);
			storyAPI.createPhotoStoryInOpenState("Photo Story ", i);
			storyAPI.createTextStoryInOpenState("Text Story ", i);
		  	storyAPI.createVideoStoryInOpenState("Demo Story of type Video ", i);
		  }
	}
	public void createTestStoriesInNeedApprovalTab()
	{
		List <String> storyVersion;
		StoryAPI storyAPI = new StoryAPI();
		for (int i = 0; i < 3; i++) {
			storyVersion = storyAPI.createTextStoryInOpenState("Need Approval Text Story", i);
		 	storyAPI.changeBlankStoryStatus(storyState[0], storyVersion);
			 	
		 	storyVersion = storyAPI.createVideoStoryInOpenState("Need Approval Video Story", i);
		 	storyAPI.changeVideoStoryStatus(storyState[0], storyVersion);
		 }
	}
	public void createTestStoriesInApprovedTab()
	{
		List <String> storyVersion;
		StoryAPI storyAPI = new StoryAPI();
		// Create 3 stories in Approved State
		for (int i = 0; i < 3; i++) {
			storyVersion = storyAPI.createPhotoStoryInOpenState("Approved Photo Story", i);
		 	storyAPI.changePhotoStoryStatus(storyState[0], storyVersion);
		 	storyAPI.changePhotoStoryStatus(storyState[1], storyVersion);
		 }		
	}
	public void createTestStoriesInPublishedTab()
	{
		int storyCount = 1;
		List <String> storyVersion;
		StoryAPI storyAPI = new StoryAPI();
		// Create 3 stores in Published State
		if(System.getProperty("suite").equalsIgnoreCase("functional"))
		{
			storyCount = 8;
		}
		for (int i = 1; i <= storyCount; i++) {
			storyVersion = storyAPI.createTextStoryInOpenState("Published Text Story", i);
			storyAPI.changeBlankStoryStatus(storyState[0], storyVersion);
			storyAPI.changeBlankStoryStatus(storyState[1], storyVersion);
			storyAPI.changeBlankStoryStatus(storyState[2], storyVersion);
			
			storyVersion = storyAPI.createPhotoStoryInOpenState("Published Photo Story", i);
			storyAPI.changePhotoStoryStatus(storyState[0], storyVersion);
			storyAPI.changePhotoStoryStatus(storyState[1], storyVersion);
			storyAPI.changePhotoStoryStatus(storyState[2], storyVersion);			
				
			storyVersion = storyAPI.createVideoStoryInOpenState(" Published Video Story", i);
			storyAPI.changeVideoStoryStatus(storyState[0], storyVersion);
			storyAPI.changeVideoStoryStatus(storyState[1], storyVersion);
			storyAPI.changeVideoStoryStatus(storyState[2], storyVersion);						
		}		
	}
	
	public static void createTestStories() 
	{
//		System.out.println("Creating Test Stories: ");
//		TestData testData1 = new TestData();
//		TestData testData2 = new TestData();
//		Thread thread1 = new Thread(testData1);
//		Thread thread2 = new Thread(testData2);
//		thread1.start();
//		thread2.start();		
		
		TestData td = new TestData();
		td.createTestStoriesInOpenTab();
		td.createTestStoriesInNeedApprovalTab();
		td.createTestStoriesInApprovedTab();
		td.createTestStoriesInPublishedTab();
		
//		StoryAPI storyAPI = new StoryAPI();
//		//	Create 3 stories in Open State
//		for (int i = 1; i <= 5; i++) {
//			storyAPI.createPhotoStoryInOpenState("This is a photo story title ", i);
//			storyAPI.createVideoStoryInOpenState("Title of Video Story ", i);
//			storyAPI.createTextStoryInOpenState("Text Story Title ", i);
//			storyAPI.createPhotoStoryInOpenState("Photo Story ", i);
//			storyAPI.createTextStoryInOpenState("Text Story ", i);
//		  	storyAPI.createVideoStoryInOpenState("Demo Story of type Video ", i);
//		  }
//		// Create 3 stories in Need Approval State
//		for (int i = 0; i < 3; i++) {
//		 	storyAPI.createTextStoryInOpenState("Need Approval Text Story", i);
//		 	storyAPI.changeStoryStatus(storyState[0]);
//			 	
//		 	storyAPI.createVideoStoryInOpenState("Need Approval Video Story", i);
//		 	storyAPI.changeStoryStatus(storyState[0]);
//		 }
//		// Create 3 stories in Approved State
//		for (int i = 0; i < 3; i++) {
//		 	storyAPI.createPhotoStoryInOpenState("Approved Photo Story", i);
//		 	storyAPI.changeStoryStatus(storyState[0]);
//		 	storyAPI.changeStoryStatus(storyState[1]);
//		 }		
//		// Create 3 stores in Published State
//		for (int i = 1; i <= 8; i++) {
//			storyAPI.createTextStoryInOpenState("Published Text Story", i);
//			storyAPI.changeStoryStatus(storyState[0]);
//			storyAPI.changeStoryStatus(storyState[1]);
//			storyAPI.changeStoryStatus(storyState[2]);
//			
//			storyAPI.createPhotoStoryInOpenState("Published Photo Story", i);
//			storyAPI.changeStoryStatus(storyState[0]);
//			storyAPI.changeStoryStatus(storyState[1]);
//			storyAPI.changeStoryStatus(storyState[2]);			
//				
//			storyAPI.createVideoStoryInOpenState(" Published Video Story", i);
//			storyAPI.changeStoryStatus(storyState[0]);
//			storyAPI.changeStoryStatus(storyState[1]);
//			storyAPI.changeStoryStatus(storyState[2]);						
//		}		
//		log.info("Test Stories Created");		
	}
}