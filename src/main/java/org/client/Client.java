package org.client;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8080;

        try (Socket socket = new Socket(hostname, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            String inputString;

            // Цикл для многократного ввода строк
            while (true) {
                System.out.println("Введите строку для подсчета гласных и согласных (или 'exit' для завершения):");
                inputString = scanner.nextLine();

                // Проверка на завершение программы
                if ("exit".equalsIgnoreCase(inputString)) {
                    System.out.println("Завершение работы клиента...");
                    return;
                }

                // Отправка строки на сервер
                out.println(inputString);

                // Получение результата от сервера
                String response = in.readLine();
                System.out.println("Ответ от сервера: " + response);
            }

        } catch (IOException e) {
            System.out.println("Ошибка клиента: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

