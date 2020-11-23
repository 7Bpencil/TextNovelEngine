package com.urfu.chadnovelengine.backendapi;

import java.util.ArrayList;

public interface IO {
    void printPossibleAnswers(String[] answers);

    void printExistingScriptsNames(String[] scriptsNames);

    String getUserAnswer();

    int getAnswerIndex(String answer, String[] answers);

    void sendServiceMessage(Message message);

    void sendScriptMessages(ArrayList<Message> messages);
}
