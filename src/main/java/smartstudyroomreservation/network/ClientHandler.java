package smartstudyroomreservation.network;

import smartstudyroomreservation.model.Reservation;
import smartstudyroomreservation.model.ReservationManager;
import smartstudyroomreservation.model.TimeSlot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler extends Thread {

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String message;
            while ((message = in.readLine()) != null) {
                String response = processCommand(message);
                out.println(response);
            }
        } catch (Exception e) {
            System.out.println("Client disconnected");
        }
    }

    private String processCommand(String command) {
        try {
            String[] parts = command.split("\\|");
            if (parts[0].equals("RESERVE") && parts.length >= 6) {
                String roomId = parts[1];
                String date = parts[2];
                String start = parts[3];
                String end = parts[4];
                String studentId = parts[5];

                TimeSlot slot = new TimeSlot(date, start, end);
                Reservation res = new Reservation("RES" + System.currentTimeMillis(), studentId, roomId, slot);

                boolean success = ReservationManager.makeReservation(res);

                if (success) {
                    return "RESERVE_SUCCESS|Reservation confirmed for room " + roomId;
                } else {
                    return "RESERVE_FAILED|Room not available";
                }
            }
            return "UNKNOWN_COMMAND";
        } catch (Exception e) {
            return "ERROR|" + e.getMessage();
        }
    }
}