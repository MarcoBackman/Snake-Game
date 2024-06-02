<!-- PROJECT SHIELDS -->
<!--
*** I'm using markdown "reference style" links for readability.
*** Reference links are enclosed in brackets [ ] instead of parentheses ( ).
*** See the bottom of this document for the declaration of the reference variables
*** for contributors-url, forks-url, etc. This is an optional, concise syntax you may use.
*** https://www.markdownguide.org/basic-syntax/#reference-style-links
-->

[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]

## Regular user setup

Only for Windows for batch execution. Other Operating System requires manual command execution.

- Simply execute `Run.bat` file with `Run As Administrator` .
Follow instructions from batch file. This batch will download and install jdk 17 and setup the environment variables if not set.


- If you wish to set up by your own, please install jdk17+ and have it set as environment variable. Then run following command at the root of the downloaded file.
`java -jar Snake-Game.jar`

## How to play game

A simple snake game app in java

There are currently 6 features implemented.

1. User can use arrow keys to move the snake
2. User can see previous scores
3. User can continue game from the previous play, or can decide to play new game
4. Snake gets faster every 10 eat, also reduces the amount of apple to eat

- For initial start, snake usually starts at the middle.
Snake cannot move in reverse direction, because the snake cannot eat itself.

- You can configure configuration file in `src/config/config.json` to adjust the game feature.(Removing file will not run the program)

## Requirements for developer setup

- Java must be installed first, please make sure that your machine is operable with Java version higher than 17+ [here](https://www.java.com/en/)
  In oder to execute the program, please download the file by forking this repository. Or you can download a zipfile from above tab. Or you may use existing batch file for auto setup.

- Data files are version sensitive, app version mismatch will cause error on data load.

#### Dependencies
- Jackson databind 2.15.2 - to read JSON file: [download jar package for manual setup](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.15.2)
- Jackson's annotation 2.15.2 - to read JSON file: [download jar package for manual setup](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations/2.15.2)
- Jackson core 2.15.2 - to read JSON file: [download jar package for manual setup](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.15.2)


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <p>Game screen</p>
  <a href="https://github.com/MarcoBackman/Snake-Game">
    <img src="img/game_screen.png" alt="game_screen" width="380" height="440">
  </a>

<h3 align="center">Snake Game</h3>

  <p align="center">
    A basic snake game program with progress load & save
    <br />
    <br />
    <a href="https://github.com/MarcoBackman/Snake-Game/#demo-videos">View Demo</a>
    ·
    <a href="https://github.com/MarcoBackman/Snake-Game/issues">Report Bug</a>
    ·
    <a href="https://github.com/MarcoBackman/Snake-Game/issues">Request Feature</a>
  </p>
</div>

## Demo image and video

<div align="center">
  <p>Video</p>
  <a href="https://github.com/MarcoBackman/Snake-Game">
    <img src="img/demo.gif" alt="main_menu" width="380" height="440">
  </a>

  </br>
  </br>

  <p>Screen_board</p>
  <a href="https://github.com/MarcoBackman/Snake-Game">
    <img src="img/score_board.png" alt="screen_board" width="260" height="130">
  </a>

</div>

<p align="right">(<a href="#top">back to top</a>)</p>


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[forks-shield]: https://img.shields.io/github/forks/MarcoBackman/Snake-Game.svg?style=for-the-badge
[forks-url]: https://github.com/MarcoBackman/Snake-Game/network/members
[stars-shield]: https://img.shields.io/github/stars/MarcoBackman/Snake-Game.svg?style=for-the-badge
[stars-url]: https://github.com/MarcoBackman/Snake-Game/stargazers
[issues-shield]: https://img.shields.io/github/issues/MarcoBackman/Snake-Game.svg?style=for-the-badge
[issues-url]: https://github.com/MarcoBackman/Snake-Game/issues
