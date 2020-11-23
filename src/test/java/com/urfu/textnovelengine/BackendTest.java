package com.urfu.textnovelengine;

import com.urfu.chadnovelengine.Backend;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BackendTest {

    @Test
    void testDifferentPathToOneNode() throws IOException {
//      This test shows that user can move to one node by different ways.
//      Showed paths: 0-1-3, 0-2-3.
        var backend = new Backend();
        var testScript = "test_dialog";
        var contentPath = "Content/" + testScript + "/";

        var firstTestUserID = 1;
        var secondTestUserID = 2;

        var firstUserReplies = new String[]{testScript, "0", "0"};
        var secondUserReplies = new String[]{testScript, "1", "0"};

        var firstUserTestIO = new TestOnlyScriptMessagesIO(firstUserReplies);
        var secondUserTestIO = new TestOnlyScriptMessagesIO(secondUserReplies);

        var expectedFirstUserScriptMessages = new String[]{
                "HELLO",
                contentPath + "alco.jpg",
                contentPath + "cyber_dream.mp3",
                "GO",
                contentPath + "zorich.txt",
                "THE END",
                contentPath + "anime.mp3"
        };

        var expectedSecondUserScriptMessages = new String[]{
                "HELLO",
                contentPath + "alco.jpg",
                contentPath + "cyber_dream.mp3",
                "BYE",
                "THE END",
                contentPath + "anime.mp3"
        };

        for (var i = 0; i < 3; ++i) {
            backend.updateUser(firstTestUserID, firstUserTestIO);
            backend.updateUser(secondTestUserID, secondUserTestIO);
        }

        Assertions.assertArrayEquals(expectedFirstUserScriptMessages, firstUserTestIO.getMessages().toArray());
        Assertions.assertArrayEquals(expectedSecondUserScriptMessages, secondUserTestIO.getMessages().toArray());
    }

    @Test
    void testPathsToDifferentTerminalNodes() throws IOException {
//      This test represents the ability to move to different terminal node from one node.
//      FirstUser represents path to terminate node through 0-1-3.
//      SecondUser represents path to terminate node through 0-2-4.
        var backend = new Backend();
        var testScript = "test_dialog_2";
        var contentPath = "Content/" + testScript + "/";

        var firstTestUserID = 1;
        var secondTestUserID = 2;

        var firstUserReplies = new String[]{testScript, "0", "0"};
        var secondUserReplies = new String[]{testScript, "1", "0"};

        var firstUserTestIO = new TestOnlyScriptMessagesIO(firstUserReplies);
        var secondUserTestIO = new TestOnlyScriptMessagesIO(secondUserReplies);

        var expectedFirstUserScriptMessages = new String[]{
                "HELLO",
                contentPath + "alco.jpg",
                contentPath + "cyber_dream.mp3",
                "GO",
                contentPath + "zorich.txt",
                "GOOD END",
                contentPath + "anime.mp3"
        };

        var expectedSecondUserScriptMessages = new String[]{
                "HELLO",
                contentPath + "alco.jpg",
                contentPath + "cyber_dream.mp3",
                "WHYYYYY???",
                "BAD END",
                contentPath + "doom.mp3"
        };

        for (var i = 0; i < 3; ++i) {
            backend.updateUser(firstTestUserID, firstUserTestIO);
            backend.updateUser(secondTestUserID, secondUserTestIO);
        }

        Assertions.assertArrayEquals(expectedFirstUserScriptMessages, firstUserTestIO.getMessages().toArray());
        Assertions.assertArrayEquals(expectedSecondUserScriptMessages, secondUserTestIO.getMessages().toArray());
    }


    @Test
    void testCheckPossibleAnswersPathOne() throws IOException {
        // This group of tests checks that we receive planned possible answers
        var testUserID = 1;
        var replies = new String[]{"test_dialog", "0", "0"};
        var expectedPossibleAnswers = new String[]{
                "YES",
                "NO",
                "OKAY",
                "test_dialog_2",
                "test_dialog",
        };

        var testIO = new TestOnlyPossibleAnswersIO(replies);
        var backend = new Backend();

        for (var i = 0; i < 3; ++i) {
            backend.updateUser(testUserID, testIO);
        }

        Assertions.assertArrayEquals(expectedPossibleAnswers, testIO.getMessages().toArray());
    }

    @Test
    void testCheckPossibleAnswersPathTwo() throws IOException {
        // This group of tests checks that we receive planned possible answers
        var testUserID = 1;
        var replies = new String[]{"test_dialog", "1", "0"};
        var expectedPossibleAnswers = new String[]{
                "YES",
                "NO",
                "GOODBYE",
                "test_dialog_2",
                "test_dialog",
        };

        var testIO = new TestOnlyPossibleAnswersIO(replies);
        var backend = new Backend();

        for (var i = 0; i < 3; ++i) {
            backend.updateUser(testUserID, testIO);
        }

        Assertions.assertArrayEquals(expectedPossibleAnswers, testIO.getMessages().toArray());
    }

}
