package org.guivic;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

    static Socket socket;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 12345);

            System.out.print("Introduce tipo de operación: 1- Suma, 2- Resta, 3-Multiplicación, 4 -División: ");
            int opr = new Scanner(System.in).nextInt();
            System.out.print("Introduce primer valor: ");
            int v1 = new Scanner(System.in).nextInt();
            System.out.print("Introduce segundo valor: ");
            int v2 = new Scanner(System.in).nextInt();
            OutputStream os = socket.getOutputStream();
            os.write((opr + "#" + v1 + "#" + v2).getBytes());
            os.flush();

            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
