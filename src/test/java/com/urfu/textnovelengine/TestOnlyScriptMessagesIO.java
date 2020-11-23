package com.urfu.textnovelengine;

import com.urfu.chadnovelengine.backendapi.IO;
import com.urfu.chadnovelengine.backendapi.Message;

import java.util.ArrayList;
import java.util.List;

public class TestOnlyScriptMessagesIO implements IO {
    private final String[] repliesFromUser;
    private final List<String> messages;
    private int currentReplyIndex;

    public TestOnlyScriptMessagesIO(String[] testAnswers) {
        repliesFromUser = testAnswers;
        messages = new ArrayList<>();
        currentReplyIndex = 0;
    }

    @Override
    public void printPossibleAnswers(String[] answers) {
    }

    @Override
    public void printExistingScriptsNames(String[] scriptsNames) {
    }

    @Override
    public String getUserAnswer() {
        return repliesFromUser[currentReplyIndex++];
    }

    @Override
    public int getAnswerIndex(String answer, String[] answers) {
        return Integer.parseInt(answer);
    }

    @Override
    public void sendServiceMessage(Message message) {
    }

    @Override
    public void sendScriptMessages(ArrayList<Message> messages) {
        for (var message : messages) {
            this.messages.add(message.content);
        }
    }

    public List<String> getMessages() {
        return messages;
    }
}
