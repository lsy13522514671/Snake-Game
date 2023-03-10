# Snake Game

This Java project implements a Snake Game. It is a practice of the object-oriented design and makes use of MVC pattern.

## Description

The Snake Game is simple and traditional. Hence, it is good subject to practice with Java programming skill. The game is implemented using MVC pattern and its GUI is implemented in Java Swing. Gradle is used as a build tool for this project.

## Getting Started

### Dependencies

* Java Runtime Environment (JRE)
* Linux, MacOS, or Windows 10

### Executing Program

* Change directory to the root of this project
* If you are new to gradle, simply follow the instruction below. 
If you are a Linux/Mac user, run 
```
gradlew build
```
If you are a Windows user, run
```
gradlew.bat build
```

## Remaining Issue

I will come back to solve these issues if I have time.

### Controller Setting Direction

There is a bug for setting direction. Before each snake movement, the user is able to set the snake direction multiple times irrationally. For example, while the snake's direction is toward right, the user is able to set the snake to left by immediately setting the snake direction up first and then set the snake direction left. This bug bypasses the model's movement validation and makes the snake turn to invalid direction.

### Game Board GUI

There is a bug on GUI. The game board is unable to proportionally react to the window. If the row number and col number are set to large enough, the game board will zoom out and become hard to see.

## Author

Siyuan Li