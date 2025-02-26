# Reversi Game

## 📌 Overview
This is a **Java-based Reversi game** with a **graphical user interface (GUI)** built using **Swing**. The game follows the traditional Reversi rules, allowing two players 
to take turns placing pieces, flipping opponent pieces, and competing to control the board.

## 🎮 Features
✅ **8x8 Board** : Classic Reversi game grid.  
✅ **Valid Move Checking** : Ensures only legal moves can be played.  
✅ **Piece Flipping** : Opponent pieces are flipped when a move is valid.  
✅ **Turn-Based Play** : Players take alternating turns.  
✅ **Score Tracking** : Displays the current score of both players.  
✅ **Game Over Detection** : Ends when no valid moves are left and declares the winner.  

## 🎲 How to Play
1️⃣ **Game Setup:**  
   - The game starts with **two black (`●`) and two white (`○`) pieces placed in the center of the 8x8 board.**  

2️⃣ **Making a Move:**  
   - Players take turns placing their pieces on an **empty square**.  
   - A valid move must **sandwich** at least one of the opponent's pieces (horizontally, vertically, or diagonally).  

3️⃣ **Flipping Pieces:**  
   - If a move is valid, all **sandwiched opponent pieces** are flipped to the current player’s color.  

4️⃣ **Skipping a Turn:**  
   - If a player has no valid moves, they must **skip their turn** and let the opponent play.  

5️⃣ **Game Over & Winning:**  
   - The game ends when **neither player has a valid move left**.  
   - The player with the **most pieces on the board wins!** 🎉  

## 🛠️ Technologies Used
- **Java** – Core programming language.
- **Swing (Java GUI)** – For the graphical user interface.
- **JUnit** – For automated testing.
- **Gradle** – For build automation and dependency management.
- **GitHub Actions** – For Continuous Integration (CI) to run tests automatically.

## 📂 Project Structure
```
ReversiGame/
│-- src/main/java/org/example/
│   ├── Board.java        # Handles game logic (valid moves, flipping pieces, score calculation)
│   ├── ReversiGUI.java   # Manages the game UI (buttons, board updates, score display)
│   ├── Main.java         # Entry point to start the game
│
│-- src/test/java/org/example/
│   ├── BoardTest.java    # Unit tests for game logic
│   ├── ReversiGUITest.java # Tests for the GUI functionality
│
│-- .github/workflows/
│   ├── main.yml          # GitHub Actions CI setup to run tests
│
│-- build.gradle.kts      # Gradle build configuration
│-- README.md             # Project documentation (this file)
```

## 🚀 How to Run the Game
### 1️⃣ **Clone the Repository**
```sh
git clone https://github.com/NourheneLdh/ReversiGame.git
cd ReversiGame
```

### 2️⃣ **Run the Game**
If using **Gradle**, simply execute:
```sh
./gradlew run  # For Linux/macOS
gradle.bat run  # For Windows
```
Or, if running manually in an IDE like IntelliJ or Eclipse:
1. Open the project in the IDE.
2. Run `Main.java`.

## ✅ Running Tests
Run all **JUnit tests** using Gradle:
```sh
./gradlew test
```
✅ If all tests pass, the game logic is working correctly!

## 🔄 GitHub Actions (CI)
This project uses **GitHub Actions** to **automatically test the game** whenever code is pushed. The workflow is defined in `.github/workflows/main.yml`.

## 📧 Contact
Developed by **Nourhene Loudhaief**. For any questions, feel free to reach out!

---
🚀 **Enjoy playing Reversi!**
