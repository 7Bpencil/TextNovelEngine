package com.urfu.textnovelengine.parsers;

import com.urfu.textnovelengine.DialogNode;
import com.urfu.textnovelengine.SimpleTalker;
import com.urfu.textnovelengine.backendapi.Talker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class ScriptParser {
    public static DialogNode parse(String scriptPath) throws IOException {
        var filePath = Paths.get(scriptPath);
        if (!Files.exists(filePath)) {
            System.out.println("ERROR: script: " + scriptPath + " is not exist");
            throw new FileNotFoundException();
        }

        var text = Files.readAllLines(filePath);
        var nodesAmount = Integer.parseInt(text.get(0).split(": ")[1]);
        var nodes = new DialogNode[nodesAmount];
        var talkers = new HashMap<String, Talker>();

        for (int i = 2, nodeIndex = 0; i < text.size(); i++) {
            var buffer = text.get(i).split(": ");

            if (buffer[0].equals("talker")) {
                var talkerName = buffer[1];
                var talkerWrongInputSpeech = text.get(++i).split(": ")[1];
                talkers.put(talkerName, new SimpleTalker(talkerName, talkerWrongInputSpeech));
            }

            if (buffer[0].equals("node")) {
                var newNode = new DialogNode();
                newNode.setTalker(talkers.get(text.get(++i).split(": ")[1]));
                newNode.setMessage(text.get(++i).split(": ")[1]);

                nodes[nodeIndex++] = newNode;
            }

        }

        for (int i = 2, nodeIndex = 0; i < text.size(); i++) {
            var buffer = text.get(i).split(": ");
            if (buffer[0].equals("answers_amount")) {
                var node = nodes[nodeIndex++];
                var answersAmount = Integer.parseInt(buffer[1]);
                if (answersAmount == 0) {
                    continue;
                }

                var responseNodes = new DialogNode[answersAmount];
                var answers = new String[answersAmount];

                for (var k = 0; k < answersAmount; k++) {
                    var bufferLine = text.get(++i).split(": ");
                    responseNodes[k] = nodes[Integer.parseInt(bufferLine[0])];
                    answers[k] = bufferLine[1];
                }

                node.setAnswers(answers);
                node.setResponses(responseNodes);
            }

        }

        return nodes[0];
    }
}

