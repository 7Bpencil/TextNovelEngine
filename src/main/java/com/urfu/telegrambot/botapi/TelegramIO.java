package com.urfu.telegrambot.botapi;

import com.urfu.chadnovelengine.backendapi.IO;
import com.urfu.chadnovelengine.backendapi.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class TelegramIO implements IO {
    private ReplyKeyboardMarkup buttons;
    private final ArrayList<Message> messages;
    private String currentUserAnswer;

    public TelegramIO() {
        messages = new ArrayList<>();
    }

    @Override
    public void printPossibleAnswers(String[] answers) {
        setPossibleReplies(answers);
    }

    @Override
    public void printExistingScriptsNames(String[] scriptsNames) {
        setPossibleReplies(scriptsNames);
    }

    @Override
    public String getUserAnswer() {
        return currentUserAnswer;
    }

    @Override
    public int getAnswerIndex(String answer, String[] answers) {
        for (var i = 0; i < answers.length; ++i) {
            if (answer.equals(answers[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public void sendServiceMessage(Message message) {
        messages.add(message);
    }

    @Override
    public void sendScriptMessages(ArrayList<Message> messages) {
        this.messages.addAll(messages);
    }

    public void setUserAnswer(String answer) {
        currentUserAnswer = answer;
    }

    private void setPossibleReplies(String[] answers) {
        buttons = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        for (String answer : answers) {
            KeyboardRow row = new KeyboardRow();
            KeyboardButton button = new KeyboardButton();
            button.setText(answer);
            row.add(button);
            keyboard.add(row);
        }

        buttons.setKeyboard(keyboard);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public ReplyKeyboardMarkup getButtons() {
        return buttons;
    }
}
