package com.quintype.scripts.metype;

import com.quintype.config.MeTypeConfigFile;
import com.quintype.endpoints.metype.CommentEndPoints;
import com.quintype.utils.DataAndTimeUtilities;
import com.quintype.utils.JSONUtilities;
import com.quintype.utils.SetUp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

public class TestComments extends SetUp
{
	Logger logger = LogManager.getLogger();
	MeTypeConfigFile meTypeConfigFile;

	@SuppressWarnings({"rawtypes", "unchecked"})

	
	
	@Test
//Test-1 : Parent Comment creation	
	public void createACommentAndVerify() {
		logger.info("Starting createACommentAndVerify test");
		meTypeConfigFile= SetUp.getmetypeConfigObject();
		String commentString = "First Parent comment : " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/metype/metype_comment.json");
		//Updating Json body with the test comment
		Map commentBody = getMap(commentString, jsonBody);

		String commentext = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");
		//jsonBody.put("insert", commentString);


		Map commentList_pre = CommentEndPoints.listComments(meTypeConfigFile, 200);
		int commentsCount_pre= Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_pre, "total_count"));
		logger.debug("Before creating a new comment, comments count: "+ commentList_pre);
		String commendId_response = CommentEndPoints.createComment(meTypeConfigFile, 201, jsonBody.toString());
		logger.debug("Created a new comment with commend id "+ commendId_response);

		Map commentList_post = CommentEndPoints.listComments(meTypeConfigFile, 200);

		ArrayList<Map> comments_array = JSONUtilities.getArray(commentList_post, "comments");
		String commentID = comments_array.get(0).get("id").toString();
		Map commentBody_response = (Map) comments_array.get(0).get("body");
		String commentext_response = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");

		int commentsCount_post = Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_post, "total_count"));

		Assert.assertTrue(commendId_response.equals(commentID));
		logger.debug("Validated comment id: "+ commendId_response);
		Assert.assertEquals(commentsCount_pre+1, commentsCount_post);
		logger.debug("Validated comment count has been increased: "+ commentsCount_post);
		Assert.assertTrue(commentext_response.equals(commentext));
		logger.debug("Validated comment text : "+ commentext);
		logger.info("Completed createACommentAndVerify");

	}

	private Map getMap(String commentString, JSONObject jsonBody) {
		Map commentBody = JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(jsonBody,"comment"),"body");
		JSONUtilities.getArray(commentBody,"ops").get(0).put("insert",commentString);
		return commentBody;
	}
	//Test-2 : Parent Comment with child comment creation
	@Test
	public void createAChildCommentAndVerify() {
		logger.info("Starting createAChildCommentAndVerify test");
		meTypeConfigFile= SetUp.getmetypeConfigObject();
		String commentString = "Parent comment  : " + DataAndTimeUtilities.getCurrentDateAndTime();
		String childCommentString = " Child comment for the parent comment: " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/metype/metype_comment.json");
		JSONObject jsonBody_child = JSONUtilities.getJSONFileObject("./src/test/resources/metype/metype_comment.json");
		//Updating Json body with the test comment
		Map commentBody = getMap(commentString, jsonBody);

		String commentext = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");


		Map commentList_pre = CommentEndPoints.listComments(meTypeConfigFile, 200);
		int commentsCount_pre= Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_pre, "total_count"));
		logger.debug("Before creating a new comment, comments count: "+ commentList_pre);
		String commendId_response = CommentEndPoints.createComment(meTypeConfigFile, 201, jsonBody.toString());
		logger.debug("Created a new comment with commend id "+ commendId_response);

		//Updating Json body for test comment and parent comment id

		Map commentBody_child = updateBodyWithParentId(childCommentString,commendId_response, jsonBody_child);

		String childcommendId_response = CommentEndPoints.createComment(meTypeConfigFile, 201, jsonBody_child.toString());
		logger.info("Created a child comment with id "+ childcommendId_response +" for parent id "+ commendId_response);


		Map commentList_post = CommentEndPoints.listComments(meTypeConfigFile, 200);

		ArrayList<Map> comments_array = JSONUtilities.getArray(commentList_post, "comments");
		String commentID = comments_array.get(1).get("id").toString();
		String parentID_response = comments_array.get(1).get("parent_comment_id").toString();

		Map commentBody_response = (Map) comments_array.get(1).get("body");
		String commentext_response = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");

		int commentsCount_post = Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_post, "total_count"));

		Assert.assertTrue(childcommendId_response.equals(commentID));
		logger.debug("Validated result comment id: "+ childcommendId_response);
		Assert.assertEquals(commentsCount_pre+2, commentsCount_post);
		logger.debug("Validated comment count has been increased: "+ commentsCount_post);
		Assert.assertTrue(commentext_response.equals(commentext));
		logger.debug("Validated comment text : "+ commentext);

		Assert.assertTrue(parentID_response.equals(commendId_response));
		logger.debug("Validated child comment is having the right parent comment id : "+ commendId_response);
		logger.info("Completed createAChildCommentAndVerify test");

	}

	private Map updateBodyWithParentId(String childCommentString, String commendId_response, JSONObject jsonBody_child) {
		Map commentBody = JSONUtilities.getInnerJSON(JSONUtilities.getInnerJSON(jsonBody_child,"comment"),"body");
		JSONUtilities.getInnerJSON(jsonBody_child,"comment").put("parent_comment_id",commendId_response);
		JSONUtilities.getArray(commentBody,"ops").get(0).put("insert",childCommentString);
		return commentBody;
	}
	

	
// Test =3 Test for the parent comment with Emoji
	
	@Test
	public void createAEmojiCommentAndVerify() {
		logger.info("Starting ccreateAEmojiCommentAndVerify test");
		meTypeConfigFile= SetUp.getmetypeConfigObject();
		String commentStringwithEmoji = "Test comment with Emojis 🍇 🍒 🍓 🍋 🍊 🍉 🍟 🍎 !! : " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/metype/metype_comment.json");
		//Updating Json body with the test comment
		Map commentBody = getMap(commentStringwithEmoji, jsonBody);

		String commentext = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");
		//jsonBody.put("insert", commentStringwithEmoji);


		Map commentList_pre = CommentEndPoints.listComments(meTypeConfigFile, 200);
		int commentsCount_pre= Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_pre, "total_count"));
		logger.debug("Before creating a new comment, comments count: "+ commentList_pre);
		String commendId_response = CommentEndPoints.createComment(meTypeConfigFile, 201, jsonBody.toString());
		logger.debug("Created a new comment with commend id "+ commendId_response);

		Map commentList_post = CommentEndPoints.listComments(meTypeConfigFile, 200);

		ArrayList<Map> comments_array = JSONUtilities.getArray(commentList_post, "comments");
		String commentID = comments_array.get(0).get("id").toString();
		Map commentBody_response = (Map) comments_array.get(0).get("body");
		String commentext_response = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");

		int commentsCount_post = Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_post, "total_count"));

		Assert.assertTrue(commendId_response.equals(commentID));
		logger.debug("Validated comment id: "+ commendId_response);
		Assert.assertEquals(commentsCount_pre+1, commentsCount_post);
		logger.debug("Validated comment count has been increased: "+ commentsCount_post);
		Assert.assertTrue(commentext_response.equals(commentext));
		logger.debug("Validated comment text : "+ commentext);
		logger.info("Completed createACommentAndVerify");

	}

//Test =4  for Creating a child comment with Emoji
	

	@Test
	public void createAChildCommentWithEmojiAndVerify() {
		logger.info("Starting createAChildCommentWithEmojiAndVerify test");
		meTypeConfigFile= SetUp.getmetypeConfigObject();
		String commentString = "Parent comment with the Emojis : 🏆 🥇 🥈 🥉 🥊 🥋 " + DataAndTimeUtilities.getCurrentDateAndTime();
		String childCommentString = " Child comment with the Emoji :☠ 🔪 🎎 💻 🔬 📡  " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/metype/metype_comment.json");
		JSONObject jsonBody_child = JSONUtilities.getJSONFileObject("./src/test/resources/metype/metype_comment.json");
		//Updating Json body with the test comment
		Map commentBody = getMap(commentString, jsonBody);

		String commentext = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");


		Map commentList_pre = CommentEndPoints.listComments(meTypeConfigFile, 200);
		int commentsCount_pre= Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_pre, "total_count"));
		logger.debug("Before creating a new comment, comments count: "+ commentList_pre);
		String commendId_response = CommentEndPoints.createComment(meTypeConfigFile, 201, jsonBody.toString());
		logger.debug("Created a new comment with commend id "+ commendId_response);

		//Updating Json body for test comment and parent comment id

		Map commentBody_child = updateBodyWithParentId(childCommentString,commendId_response, jsonBody_child);

		String childcommendId_response = CommentEndPoints.createComment(meTypeConfigFile, 201, jsonBody_child.toString());
		logger.info("Created a child comment with id "+ childcommendId_response +" for parent id "+ commendId_response);


		Map commentList_post = CommentEndPoints.listComments(meTypeConfigFile, 200);

		ArrayList<Map> comments_array = JSONUtilities.getArray(commentList_post, "comments");
		String commentID = comments_array.get(1).get("id").toString();
		String parentID_response = comments_array.get(1).get("parent_comment_id").toString();

		Map commentBody_response = (Map) comments_array.get(1).get("body");
		String commentext_response = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");

		int commentsCount_post = Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_post, "total_count"));

		Assert.assertTrue(childcommendId_response.equals(commentID));
		logger.debug("Validated result comment id: "+ childcommendId_response);
		Assert.assertEquals(commentsCount_pre+2, commentsCount_post);
		logger.debug("Validated comment count has been increased: "+ commentsCount_post);
		Assert.assertTrue(commentext_response.equals(commentext));
		logger.debug("Validated comment text : "+ commentext);

		Assert.assertTrue(parentID_response.equals(commendId_response));
		logger.debug("Validated child comment is having the right parent comment id : "+ commendId_response);
		logger.info("Completed createAChildCommentAndVerify test");

	}
	
// Test =5  for Parent comment Image upload 
	@Test
	public void createAParentCommentAndVerify() {
		logger.info("Started to create parent comment with Image upload And Verify test");
		meTypeConfigFile= SetUp.getmetypeConfigObject();
		String commentStringwithImage = "Test comment with Emojis 🍇 🍒 🍓 🍋 🍊 🍉 🍟 🍎 !! : " + DataAndTimeUtilities.getCurrentDateAndTime();
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/metype/imageUpload.json");
		//Updating Json body with the test comment
		Map commentBody = getMap(commentStringwithImage, jsonBody);

		String commentext = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");
		//jsonBody.put("insert", commentStringwithImage);


		Map commentList_pre = CommentEndPoints.listComments(meTypeConfigFile, 200);
		int commentsCount_pre= Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_pre, "total_count"));
		logger.debug("Before creating a new comment, comments count: "+ commentList_pre);
		String commendId_response = CommentEndPoints.createComment(meTypeConfigFile, 201, jsonBody.toString());
		logger.debug("Created a new comment with commend id "+ commendId_response);

		Map commentList_post = CommentEndPoints.listComments(meTypeConfigFile, 200);

		ArrayList<Map> comments_array = JSONUtilities.getArray(commentList_post, "comments");
		String commentID = comments_array.get(0).get("id").toString();
		Map commentBody_response = (Map) comments_array.get(0).get("body");
		String commentext_response = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");

		int commentsCount_post = Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_post, "total_count"));

		Assert.assertTrue(commendId_response.equals(commentID));
		logger.debug("Validated comment id: "+ commendId_response);
		Assert.assertEquals(commentsCount_pre+1, commentsCount_post);
		logger.debug("Validated comment count has been increased: "+ commentsCount_post);
		Assert.assertTrue(commentext_response.equals(commentext));
		logger.debug("Validated comment text : "+ commentext);
		logger.info("Completed createACommentAndVerify");

	}

	
// Test = 6  for ChildCommentWithImageUpload 
	@Test
	public void createAChildCommentWithImageUploadAndVerify() {
		logger.info("Starting createAChildCommentWithImageUploadAndVerify test");
		meTypeConfigFile= SetUp.getmetypeConfigObject();
		String commentString = "Parent comment with the Emojis with Image : 🏆 🥇 🥈 🥉 🥊 🥋 " + DataAndTimeUtilities.getCurrentDateAndTime();
		String childCommentString = " Child comment with the Emoji with the Image :☠ 🔪 🎎 💻 🔬 📡  " + DataAndTimeUtilities.getCurrentDateAndTime();
	
		JSONObject jsonBody = JSONUtilities.getJSONFileObject("./src/test/resources/metype/imageUpload.json");
		JSONObject jsonBody_child = JSONUtilities.getJSONFileObject("./src/test/resources/metype/imageUpload.json");
		//Updating Json body with the test comment
		Map commentBody = getMap(commentString, jsonBody);

		String commentext = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");


		Map commentList_pre = CommentEndPoints.listComments(meTypeConfigFile, 200);
		int commentsCount_pre= Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_pre, "total_count"));
		logger.debug("Before creating a new comment, comments count: "+ commentList_pre);
		String commendId_response = CommentEndPoints.createComment(meTypeConfigFile, 201, jsonBody.toString());
		logger.debug("Created a new comment with commend id "+ commendId_response);

		//Updating Json body for test comment and parent comment id

		Map commentBody_child = updateBodyWithParentId(childCommentString,commendId_response, jsonBody_child);

		String childcommendId_response = CommentEndPoints.createComment(meTypeConfigFile, 201, jsonBody_child.toString());
		logger.info("Created a child comment with id "+ childcommendId_response +" for parent id "+ commendId_response);


		Map commentList_post = CommentEndPoints.listComments(meTypeConfigFile, 200);

		ArrayList<Map> comments_array = JSONUtilities.getArray(commentList_post, "comments");
		String commentID = comments_array.get(1).get("id").toString();
		String parentID_response = comments_array.get(1).get("parent_comment_id").toString();

		Map commentBody_response = (Map) comments_array.get(1).get("body");
		String commentext_response = JSONUtilities.getArrayValue(commentBody,"ops",0,"insert");

		int commentsCount_post = Integer.parseInt(JSONUtilities.getValueFromResponse(commentList_post, "total_count"));

		Assert.assertTrue(childcommendId_response.equals(commentID));
		logger.debug("Validated result comment id: "+ childcommendId_response);
		Assert.assertEquals(commentsCount_pre+2, commentsCount_post);
		logger.debug("Validated comment count has been increased: "+ commentsCount_post);
		Assert.assertTrue(commentext_response.equals(commentext));
		logger.debug("Validated comment text : "+ commentext);

		Assert.assertTrue(parentID_response.equals(commendId_response));
		logger.debug("Validated child comment is having the right parent comment id : "+ commendId_response);
		logger.info("Completed createAChildCommentAndVerify test");

	  }
	
	}

			