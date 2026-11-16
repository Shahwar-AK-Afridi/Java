# Assignment 3: Maps Version of GladLibs

## Overview
This project is part of Duke University's **Java Programming: Arrays, Lists, and Structured Data** course on Coursera.

The goal is to refactor the GladLibs program using a **HashMap** to manage word categories and generate random stories without repeating words.

## Requirements
- Replace nine separate ArrayLists with a single `HashMap<String, ArrayList<String>>`.
- Initialize the HashMap in the constructors.
- Load words from category files using `initializeFromSource()`.
- Use `getSubstitute()` to randomly select words from the appropriate category.
- Prevent duplicate word substitutions in a story.
- Implement `totalWordsInMap()` to count all available words.
- Implement `totalWordsConsidered()` to count words from only the categories used in the generated story.
- Display the generated story and word statistics.

## Word Categories
Adjective, Noun, Color, Country, Name, Animal, Timeframe, Verb, and Fruit.

## Java Concepts Practiced
- HashMap and ArrayList
- Generics
- Enhanced for loops
- Constructors and encapsulation
- Random number generation
- File handling
- Methods and code refactoring
- Tracking unique words and categories

## Technologies
- Java
- BlueJ IDE
- Duke University `edu.duke` library

## Learning Outcome
Learned how to replace multiple category-specific lists and conditional statements with a HashMap, making the GladLibs program more organized, maintainable, and easier to extend.