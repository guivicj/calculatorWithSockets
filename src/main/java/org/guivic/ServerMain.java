package org.guivic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerMain {

    static ServerSocket serverSocket;

    public static void main(String[] args) {

        try {
            serverSocket = startServer(12345);

            Socket socket = waitForClient();

            String[] calculation = readClientData(socket.getInputStream());

            int opr = Integer.parseInt(calculation[0]);
            int v1 = Integer.parseInt(calculation[1]);
            int v2 = Integer.parseInt(calculation[2]);

            Map<Integer, OperationOption> options = initializeOperations();

            executeOperation(opr, v1, v2, options);

            closeConnection(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ServerSocket startServer(int port) throws IOException {
        System.out.println("Empezando servidor en el puerto: " + port);
        return new ServerSocket(port);
    }

    private static Socket waitForClient() throws IOException {
        System.out.println("Esperando cliente...");
        return serverSocket.accept();
    }

    private static String[] readClientData(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line = br.readLine();
        System.out.println("Datos recibidos: " + line);
        return line.split("#");
    }

    private static Map<Integer, OperationOption> initializeOperations() {
        Map<Integer, OperationOption> options = new HashMap<>();
        options.put(1, new Addition());
        options.put(2, new Subtraction());
        options.put(3, new Multiply());
        options.put(4, new Divide());
        return options;
    }

    private static void executeOperation(int opr, int v1, int v2, Map<Integer, OperationOption> options) {
        OperationOption operation = options.get(opr);
        if (operation != null) {
            int result = operation.execute(v1, v2);
            System.out.println("Resultado: " + result);
        } else {
            System.out.println("No existe la operacion");
        }
    }

    private static void closeConnection(Socket socket) throws IOException {
        System.out.println("Cerrando conexión con el cliente...");
        socket.close();
    }

}
