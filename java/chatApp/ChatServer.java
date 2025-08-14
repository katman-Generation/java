import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<ClientHandler> clients = new HashSet<>();
    private static int userIdCounter = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Chat server started on port " + PORT);

        while (true) {
            Socket socket = serverSocket.accept();
            String userId = "User" + userIdCounter++;
            ClientHandler clientHandler = new ClientHandler(socket, userId);
            clients.add(clientHandler);
            new Thread(clientHandler).start();
        }
    }

    static void broadcast(String message, ClientHandler sender) {
        for (ClientHandler client : clients) {
            if (client != sender) {
                client.sendMessage(message);
            }
        }
    }

    static void removeClient(ClientHandler client) {
        clients.remove(client);
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private String userId;

    public ClientHandler(Socket socket, String userId) {
        this.socket = socket;
        this.userId = userId;
    }

    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("Welcome " + userId + "! You can start chatting.");
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println(userId + ": " + message);
                ChatServer.broadcast(userId + ": " + message, this);
            }
        } catch (IOException e) {
            System.out.println(userId + " disconnected.");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ChatServer.removeClient(this);
        }
    }

    void sendMessage(String message) {
        out.println(message);
    }
}

