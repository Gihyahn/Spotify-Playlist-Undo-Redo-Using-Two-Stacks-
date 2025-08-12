/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gillo;
import java.util.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author BED
 */
public class Gillo {

    private static ArrayList<String> playlist = new ArrayList<>();
    private static Stack<ArrayList<String>> undoStack = new Stack<>();
    private static Stack<ArrayList<String>> redoStack = new Stack<>();
    public static void main(String[] args) {
        
     Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Spotify Playlist ---");
            System.out.println("1. Add Song");
            System.out.println("2. Remove Last Song");
            System.out.println("3. Undo");
            System.out.println("4. Redo");
            System.out.println("5. View Playlist");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter song name: ");
                    String song = scanner.nextLine();
                    saveState(undoStack);
                    redoStack.clear();
                    playlist.add(song);
                    System.out.println("Song added.");
                    break;

                case 2:
                    if (playlist.isEmpty()) {
                        System.out.println("Playlist is already empty.");
                    } else {
                        saveState(undoStack);
                        redoStack.clear();
                        String removed = playlist.remove(playlist.size() - 1);
                        System.out.println("Removed: " + removed);
                    }
                    break;

                case 3:
                    if (undoStack.isEmpty()) {
                        System.out.println("Nothing to undo.");
                    } else {
                        saveState(redoStack);
                        playlist = undoStack.pop();
                        System.out.println("Undo successful.");
                    }
                    break;

                case 4:
                    if (redoStack.isEmpty()) {
                        System.out.println("Nothing to redo.");
                    } else {
                        saveState(undoStack);
                        playlist = redoStack.pop();
                        System.out.println("Redo successful.");
                    }
                    break;

                case 5:
                    if (playlist.isEmpty()) {
                        System.out.println("Playlist is empty.");
                    } else {
                        System.out.println("Current Playlist:");
                        for (int i = 0; i < playlist.size(); i++) {
                            System.out.println((i + 1) + ". " + playlist.get(i));
                        }
                    }
                    break;

                case 6:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        scanner.close();
    }

    private static void saveState(Stack<ArrayList<String>> stack) {
        stack.push(new ArrayList<>(playlist));
    }
}