package com.urfu.textnovelengine.parsers;

import com.urfu.chadnovelengine.backendapi.Message;
import com.urfu.chadnovelengine.backendapi.MessageType;
import com.urfu.chadnovelengine.parsers.ScriptParser;
import com.urfu.chadnovelengine.DialogNode;
import com.urfu.chadnovelengine.Script;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ScriptParserTest {

    @Test
    void scriptCreationTest() throws IOException {
        String scriptName = "test_dialog";
        Script script = ScriptParser.parse(scriptName);
        DialogNode first = script.getNode(0);
        DialogNode second = script.getNode(1);
        DialogNode third = script.getNode(2);
        DialogNode last = script.getNode(3);

        var contentPath = "Content/" + scriptName + "/";

        var firstExpectedMessages = new ArrayList<Message>();
        firstExpectedMessages.add(new Message("HELLO", MessageType.TEXT));
        firstExpectedMessages.add(new Message(contentPath + "alco.jpg", MessageType.IMAGE));
        firstExpectedMessages.add(new Message(contentPath + "cyber_dream.mp3", MessageType.MUSIC));

        var secondExpectedMessages = new ArrayList<Message>();
        secondExpectedMessages.add(new Message("GO", MessageType.TEXT));
        secondExpectedMessages.add(new Message(contentPath + "zorich.txt", MessageType.DOCUMENT));

        var thirdExpectedMessages = new ArrayList<Message>();
        thirdExpectedMessages.add(new Message("BYE", MessageType.TEXT));

        var lastExpectedMessages = new ArrayList<Message>();
        lastExpectedMessages.add(new Message("THE END", MessageType.TEXT));
        lastExpectedMessages.add(new Message(contentPath + "anime.mp3", MessageType.MUSIC));

        Assertions.assertAll(
                () -> Assertions.assertIterableEquals(firstExpectedMessages, first.getMessages()),
                () -> Assertions.assertArrayEquals(new int[]{1, 2}, first.getResponses()),
                () -> Assertions.assertArrayEquals(new String[]{"YES", "NO"}, first.getAnswers())
        );
        Assertions.assertAll(
                () -> Assertions.assertIterableEquals(secondExpectedMessages, second.getMessages()),
                () -> Assertions.assertArrayEquals(new int[]{3}, second.getResponses()),
                () -> Assertions.assertArrayEquals(new String[]{"OKAY"}, second.getAnswers())
        );
        Assertions.assertAll(
                () -> Assertions.assertIterableEquals(thirdExpectedMessages, third.getMessages()),
                () -> Assertions.assertArrayEquals(new int[]{3}, third.getResponses()),
                () -> Assertions.assertArrayEquals(new String[]{"GOODBYE"}, third.getAnswers())
        );
        Assertions.assertAll(
                () -> Assertions.assertIterableEquals(lastExpectedMessages, last.getMessages()),
                () -> Assertions.assertNull(last.getAnswers()),
                () -> Assertions.assertNull(last.getResponses()),
                () -> Assertions.assertNull(last.getWrongInputReaction()));
    }

}
