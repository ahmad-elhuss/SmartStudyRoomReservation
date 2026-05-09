package smartstudyroomreservation.network;

import smartstudyroomreservation.model.ReservationManager;
import smartstudyroomreservation.model.StudyRoom;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 1234;

    public static void main(String[] args) {
        initializeRooms();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");
                new ClientHandler(clientSocket).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initializeRooms() {
        if (ReservationManager.getAllRooms().isEmpty()) {
            StudyRoom r1 = new StudyRoom("R101", "Room 101", 4, "First Floor");
            StudyRoom r2 = new StudyRoom("R102", "Room 102", 6, "Second Floor");
            StudyRoom r3 = new StudyRoom("R201", "Room 201", 5, "Second Floor");
            StudyRoom r4 = new StudyRoom("R301", "Room 301", 3, "Third Floor");

            ReservationManager.addRoom(r1);
            ReservationManager.addRoom(r2);
            ReservationManager.addRoom(r3);
            ReservationManager.addRoom(r4);
        }
    }
}