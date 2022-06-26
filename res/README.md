# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Fall 2022 Edition!

**Name:** Malav Ashish Patel , Aneesh Kumar Baskaran 

**Email:** patel.mala@northeastern.edu , baskaran.a@northeastern.edu

**Preferred Name:** put your preferred name here (how shall we address you)

#### Malav Patel , Aneesh Baskaran

### About/Overview

Give a general overview of the problem and how your program solve the problem
Developing a board game that has rooms , target character and items. Items are placed in rooms and target character moves to different rooms on the basis of index location. 
Added new players that would play the game. Also has a provision to add  computer player to the game which would do all the tasks on its own. Game would be played by the controller.

Player can attack target using the items which they possess and health of the target will be reduced in accordance to damage associated with weapon. The attack is considered as unsuccessful
if some player sees it i.e they are in the same room or in the neighbouring room. The target will also have a pet that is initialized at the same location to that of the target. At every 
turn the target moves across the world in DFS method and target moves to room having next index location. The game ends if player successfully kills the target or the number of turns
are over.


### List of Features

- Draw the world
- Create the player
- Move the player
- Look around performed by the player
- Picking up item
- Describe a player
- Space information
- Attack the target
- Pet of the Target moving in DFS pattern
- Target moving with every turn

#### List Of Features in Milestone 4

- Implemented GUI
- Player move on screen by mouse click
- Player attack using weapons from his/her inventory on keyboard press
- Player Information available on clicking on the player
- Move pet manually using key press
- Pick up weapons using key press
- Look around using key press
- ##### Now User can also load a new file specifications 


### How to Run

Describe how to run your program from the JAR file. Describe what arguments are needed (if any) and what they mean.
Run the jar file in the terminal by giving the file path and number of turns as command line argument.
java -jar Milestone3/res/milestone03.jar Milestone3/res/mansion.txt 12 



### How to Use the Program

Provide instructions on how to use the functionality in your program. If it is interactive, describe how to interact with your program. Pay particular attention to the parts that are not part of the example runs that you provide.
After running the jar file you can create the players and play the game. Target and Pet will be initialized at the same location.
You can create Human or Computer Player using "create" command
You can look around uisng "look" command
You can move to neighbouring space using "move" command - you will be provided with a list of all available spaces to move
You can attack the target using "attack" command - you can use poke in case if you don't have any weapons present
You can pick up items yo use during attack using "pick" command- you will be provided with a list of all available spaces to move
At beginning of every turn , player will also be provided with information about the target as well as about the space in which the player is currently located in.

Once you run the jar file you are directed to HomePage which has a menu bar at the top and start button if you want to play the game with default configuration , menu bar consist with 3 options , 1 : Load New Game , 2 : Help , 3 : Quit.
Load New Game allows to play the game with your own specification. 
Help provides the assistance with the game rules.
Quit is for quitting the game.

**** Starting The Game ****
Once we hit the start button , we are directed to EntryPage where we can provide the player details,
PlayerDetails consist of Name , Capacity , PlayerType , Location of Player.
Name takes the string input which can't be null.
Capacity will have JOptionPane ranging between 0 and 2. 
PlayerType is type of boolean , it can be either Human Player or Computer Player . User can select player type using the radio button provided.
Location will have JOptionPane consist all the existing room in the game.
After Filling all the details' user will hit the add player button and now he can either add more players or play the game.

### ---- Playing The Game ----

After adding player/s user will hit the play button on the Entry Page.
Player can perform below listed task during his/her turn :
- Press M to move the player in his/her neighbour rooms , if player select the wrong room to move , Game will show the  dialog box stating "Invalid Neighbours".
- Press P to move the pet in the desired room. If player doesn't move the pet during the turn it will automatically move following DFS pattern.
- Press L to find player/s , item and target in surrounding rooms.
- Press I to pick up the weapon.
- Press A to attack the target.

### ---- Tips From Developers ----

Press C for clue during the game which will assist you with the surroundings.
Click on Player to get the Player Information.
Player will be invisible to others if he/she shares the same room with the pet.



The file to test the overlap in rooms is stored at res/mansionOverlap.txt


### Example Runs

List any example runs that you have in res/ directory and provide a description of what each example represents or does. Make sure that your example runs are provided as *plain text files*.
#### Example run 1 
File : res/Milestone 3 Example Run 1
Here in this run, we create 2 human players namely Malav and Daksh which are located in Armory and Billiard Room respectively.
Player Malav uses look command to around. Now its turn of Player Daksh and based on hint given that target it attacks the target .But
attack fails are player Malav is in next room. As player Daksh had no weapon it used poke to attack. Player Malav uses look again but 
couldn't find Drawing Room as its neighbour. This is because the pet is moving in DFS manner. Player Daksh then moves to Dining Hall.
Player Malav then uses attack command to attack ,but attack fails as Target is not present in same room.

#### Example run 2
File : res/Milestone 3 Example Run 2
Use file res/mansion2.txt
Here in this run we create 2 human player and 1 computer player namely Malav , S and DK at Carriage House,Drawing Room and Trophy Room.
PLayer Malav uses "pick" command and pick up Chain Saw. Player S will use "look" command and after that Computer Player DK will move to library.
Then Player Malav uses "movePet" command and move the pet to Foyer. Now from the hint , Player S comes to know
that Target is in same space and then uses "attack" command. As it doesn't have any weapon , Player S will type
"poke" and kill the target. In this gameplay, target has only 1 life. 

#### Example run 3
File : res/Milestone 3 Example Run 3
Use file res/mansion2.txt
Here in this run, we create 2 human player and 1 computer player namely Malav, Shivam and Daksh at location Armory, Billiard Room and Carriage House. Now , it is turn of Player Malav
and he choose to movePet at Library. Now it is turn of Player Shivam and he choose to move to Armory. Now after 2 turns , Target Character has reached Carriage House where Computer Player
Daksh is present. The computer checks whether there is no one in neighbouring spaces and automatically attacks the Target character. As target character had health of 1 , he dies
immediately and game ends.

#### Example run 4 ( Tracking DFS movement of Pet)
File : res/Milestone 3 Example Run 4
Use file res/mansion2.txt
Here in this run, we create 1 human player named M at room named Armory. In space description we can see that pet is present here. Now we move Player M to Billiard Room and can see the
presence of pet in space description. Now we move Player M to Dining Hall and can see the presence of pet in space description. Now we move Player M to Drawing Room and can see the
presence of pet in space description. Now we move Player M to Foyer and can see the presence of pet in space description. Now we move Player M to Piazza and can see the
presence of pet in space description. Now we move Player M to Hedge Maze and can see the presence of pet in space description. Now we move Player M to Piazza  and can see the
presence of pet in space description. Now we move Player M to Winter Garden and can see the presence of pet in space description. Now we move Player M to Carriage House and can see the
presence of pet in space description. 

Pet follows the DFS Path - [Armory, Billiard Room, Dining Hall, Drawing Room, Foyer, Piazza, Hedge Maze, Green House, Hedge Maze, Piazza, Winter Garden,Carriage House, Winter Garden, 
Piazza, Foyer, Drawing Room, Wine Cellar, Kitchen, Parlor, Servants' Quarters, Lancaster Room, Lilac Room, Master Suite, Library, Nursery, Library, Trophy Room, Tennessee Room]


### Design/Model Changes

Document what changes you have made from earlier designs. Why did you make those changes? Keep an on-going list using some form of versioning so it is clear when these changes occurred.
Changes were made in Player Class , Target Class and WorldImpl class.
New Class PetImpl was added which represents the pet of the target.
Controller was changed a bit as new controls were added to it.


### Assumptions

List any assumptions that you made during program development and implementations. Be sure that these do not conflict with the requirements of the project.
No rooms can have same name
All rooms should be mentioned in the text file in alphabetical form.


### Limitations

What limitations exist in your program. This should include any requirements that were *not* implemented or were not working correctly (including something that might work some of the time).

- No limitations

### Citations

Be sure to cite your sources. A good guideline is if you take more than three lines of code from some source, you must include the information on where it came from. Citations should use proper [IEEE citation guidelines](https://ieee-dataport.org/sites/default/files/analysis/27/IEEE Citation Guidelines.pdf) and should include references (websites, papers, books, or other) for ***any site that you used to research a solution***. For websites, this includes name of website, title of the article, the url, and the date of retrieval**.** Citations should also include a qualitative description of what you used, and what you changed/contributed.
- GeeksForGeeks
- Stack Overflow
- https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html


