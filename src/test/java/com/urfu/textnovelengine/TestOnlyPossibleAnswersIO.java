package com.urfu.textnovelengine;

import com.urfu.chadnovelengine.backendapi.IO;
import com.urfu.chadnovelengine.backendapi.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestOnlyPossibleAnswersIO implements IO {
    private final String[] repliesFromUser;
    private final List<String> messages;
    private int currentReplyIndex;

    public TestOnlyPossibleAnswersIO(String[] testAnswers) {
        repliesFromUser = testAnswers;
        messages = new ArrayList<>();
        currentReplyIndex = 0;
    }

    @Override
    public void printPossibleAnswers(String[] answers) {
        Collections.addAll(messages, answers);
    }

    @Override
    public void printExistingScriptsNames(String[] scriptsNames) {
        Collections.addAll(messages, scriptsNames);
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
    }

    public List<String> getMessages() {
        return messages;
    }
}
