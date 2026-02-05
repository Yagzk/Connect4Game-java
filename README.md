# Connect4Game (Console) -java

A simple console-based Connect Four game written in Java for two players (A and B).

## Features
- 6x7 board (standard Connect Four size)
- Two-player turn-based gameplay (Player A vs Player B)
- Disc dropping logic (discs stack from bottom)
- Win detection:
  - Horizontal
  - Vertical
  - Diagonal (both directions)
- Draw detection (top row is full)

### Win Condition / Kazanma Durumu
A player wins if **4 of their discs (A or B)** are connected:

## How to Run
1. Save the file as `ConnectFourGame.java`
2. Compile:
   ```bash
   javac ConnectFourGame.java
