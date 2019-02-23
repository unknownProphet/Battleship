import java.util.*;
import java.util.Scanner;

// Position ist eine Datenstruktur um die Zeilen/Spalten (Koordinaten) des Spiels zu spezifizieren.
// Es werden die Integers row und column initialisiert.
class Position {
    public int row;
    public int column;

    Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

// "enum" ist ein aufzählbarer Datentyp, der genau einen der beiden Zustände annehmen kann.
// South steht für "nach unten" und east für "nach rechts".
enum Orientation {  
    South,         
    East
}

// Es werden die Zustände der Felder definiert. Entweder weiß man nicht ob dieses Feld getroffen wurde,
// oder ein Schiff auf diesem Feld wurde getroffen oder nicht.
enum HitStatus {
    HitUnknown,
    HitSuccess,
    HitMiss
}

// Der Datentyp GameStatus definiert PlayerHasWon für der menschlihe Spieler hat gewonnen, EnemyHasWon für
// der Computer hat gewonnen und GameInProgress für das Spiel läuft noch.
enum GameStatus {
    PlayerHasWon,
    EnemyHasWon,
    GameInProgress
}

// Hier wird definiert, dass eine Schissposition immer aus den Koordinaten der Position, 
// sowie einer Ausrichtung bestehen muss.
class ShipLocation {
    Position position;
    Orientation orientation;

    ShipLocation(Position pos, Orientation orientation) {
        this.position = pos;
        this.orientation = orientation;
    }
}

/*
 * Die Class Settings besteht aus dem Integer Wert "Size" welcher für die Größe des Spielfeldes steht,
 * das Array shipSetting beinhaltet die Schiffgrößen welche gesetzt werden können.
 * die Funktionen isAvailableX gehen die Felder durch und überprüfen ob dort ein Schiff
 * platziert werden kann. In der spawn-Funktion werden die Schiffe platziert und überprüft ob sie dort
 * platziert werden können.
 */
class Settings {
    public static int Size = 10;
    public static int[] shipSettings = {1, 1, 2, 2, 3, 4, 5}; 
    
    private static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;     
        return (int)(Math.random() * range) + min;
    }

    private static boolean isAvailableSouth(int row, int column1, int column2, boolean[][] map) {
        for (int column = column1; column <= column2; column++) 
            if (map[row][column] == true)
                return false;
        
        return true;
    }

    private static boolean isAvailableEast(int column, int row1, int row2, boolean[][] map) {
        for (int row = row1; row <= row2; row++) 
            if (map[row][column] == true)
                return false;
        
        return true;
    }

    private static ShipLocation spawn (int length, boolean[][] map) {
        Position pos = new Position(randomWithRange(0, Size - 1), randomWithRange(0, Size - 1));   
        Orientation orientation = (randomWithRange(1, 2) % 2) != 0 ? Orientation.South : Orientation.East;

        if (orientation == Orientation.South &&
                pos.row + length - 1 < Size && 
                isAvailableSouth (pos.column, pos.row, pos.row + length - 1, map)) 
            return new ShipLocation(pos, orientation);
    
        if (orientation == Orientation.East &&
                pos.column + length - 1 < Size &&
                isAvailableEast(pos.row, pos.column, pos.column + length - 1, map))
            return new ShipLocation(pos, orientation);
    
        return spawn(length, map);
    }

    private static void spawnRandom (int length, boolean[][] map) {
        // todo: call spawn(length, map), then initialize map (set elements to true)
        
    }

    public static boolean[][] spawnRandomShips () {
        boolean[][] map = new boolean[Size][Size];
        return map;
    }
}

class Board {
    private boolean[][] fields;

    public Board () {
        fields = new boolean[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                fields[i][j] = false;
            }
        }
    }

    // Testet ob n schiff da ist.
    // Gibt true zurück wenn ein schiff da ist,
    // sonst false.
    public boolean hasShipsPresent () {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (fields[i][j] == true) {
                    return true;
                }
            }
        }
        return false;
    }
}

class Ship {

}

class GameEngine {
    Board enemyBoard;
    Board playerBoard;

    public GameEngine () {
        enemyBoard = new Board();
        playerBoard = new Board();
    }
    public void GameEngineStart() {
        System.out.println("Hallo");
    }
}

class TestFrontend {
    public static void main(String[] args) {
        GameEngine x = new GameEngine();
        x.test();   
    }
}
