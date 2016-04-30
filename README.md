# Game design using Cellular Automata
Level design in video games is a time-consuming and difficult task. It’s extremely difficult for humans to hand-craft areas that both look natural and are simultaneously fun to play in. With a high demand for such games and so many levels in each game, it would save an unfathomable amount of money to have computers generate the levels on the fly. Perhaps more importantly, a game with randomly generated levels inherently has a much higher replay value. To circumvent this problem, video game designers simply generate the world as the player moves through it.  
###### This project aims at developing a simple game for demonstration which implements cellular automata techniques for level generation.


The process of level generation implements the following steps:  
* Generate the level by applying the automata rule _B678/S45678_.
 
  ![Cave generation animation][cave_generation]

* Smoothen the edges by applying the automata rule _B345678/S45678_.

* Implement the flood fill algorithm to check the accessibility of the generated cave from a given point.

  ![Flood fill image][flood_fill]
  
* Introduce aesthetic elements (like _waterfalls_) and elements related to the game logic (like _treasure_) to generate the final level.
  
  ![Generated level][level]
 
* Certain initial configurations which never stabilize (like the _Gosper's Glider Guns_) can be used to make the game interesting and fun.
  
  ![Gosper Guns][gosper_guns]

[cave_generation]: https://raw.githubusercontent.com/ramyaj876/SE/master/images/cave_generation.gif
[flood_fill]: https://raw.githubusercontent.com/ramyaj876/SE/master/images/flood_fill.JPG
[level]: https://raw.githubusercontent.com/ramyaj876/SE/master/images/level.JPG
[gosper_guns]: https://raw.githubusercontent.com/ramyaj876/SE/master/images/gosper_guns.gif
