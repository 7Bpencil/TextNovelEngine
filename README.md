# TextNovelEngine
Engine for creating text novel games on popular chat platforms: Telegram, VK and others.
* Simple and powerful dialog state machine that works on dialog nodes
* Almost no restrictions: an unlimited amount of plot threads; loops; time-travel:  
  nodes are just a way to program a state machine.
* Backend content support: images, music, videos, documents
* Built-in user and script managers
* Frontend examples: Telegram Bot, VK and console
* Script examples: (see in resources)  
![Telegram Bot](/readme_images/t_bot.png)
```
Dialog node example:

    node: 0
    message text: Bartender: What would you drink?
    message image: available_alcohol.jpg
    message music: bar_music.mp3
    message text: Bartender: Today is a beer day, so a little sale!
    wrong_input_message: What are you talking about?
    answers:
    1: beer - 5$
    2: whiskey - 10$
    3: vodka - 7$


Each Dialog Node is represented in plain text format:

    node ID
    message block
    wrong_input_message
    answers block

* node ID       
                node: (ID)

                Index of a node in script. Nodes will be mapped to array, 
                so nodes' indexes should be valid array indexes:
                IDs belongs to sequence {0, 1, ... , nodes_amount - 1}


* message block
                message (format): (filename or text message)
                ... 
                message (format): (filename or text message)

                Any non zero amount of messages of every format,  
                that will be sent to player as a response on his answer.
                Backend supports formats: text, image, music, video, doc.
                Frontend decides how send them to player.


* wrong_input_message
                wrong_input_message: (text message)

                Wrong input text message will be sent to player
                if received input is not one of predefined answers or a special command


* answers block
                (node ID): (user answer)
                ...
                (node ID): (user answer)

                Defines possible player's replies or actions. 
                After answer player will be redirected to node 
                with corresponding index. 



Terminating Nodes:
    Dialog Node will send user to another node after answer.
    It means that script will run forever. So Terminating Nodes is a way to stop script.
    If some node sends user to such node user will receive it messages and script will end.

Terminating Node Example:

    node: 10
    message text: Goodbye! See you tomorrow!
    message music: ending_music.mp3

Terminating Node Format:

    node ID
    message block

        So basically terminating node doesn't give answers and lacks wrong_input_message
```

