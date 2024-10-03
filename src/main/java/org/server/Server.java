package org.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8080; // Порт для соединения
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер запущен и ожидает подключения...");

            // Ожидание подключения клиента
            while (true) {
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    // Получение строки от клиента
                    String inputString = in.readLine();
                    if (inputString == null) {
                        continue;
                    }
                    System.out.println("Получено от клиента: " + inputString);

                    // Подсчет гласных и согласных
                    int vowels = countVowels(inputString);
                    int consonants = countConsonants(inputString);

                    // Возврат результата клиенту
                    out.println("Гласные: " + vowels + ", Согласные: " + consonants);
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка сервера: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Подсчет гласных букв
    private static int countVowels(String str) {
        int count = 0;
        String vowels = "AEIOUaeiouАЕЁИОУЫЭЮЯаеёиоуыэюя";
        for (char c : str.toCharArray()) {
            if (vowels.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }

    // Подсчет согласных букв
    private static int countConsonants(String str) {
        int count = 0;
        String consonants = "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyzБВГДЖЗЙКЛМНПРСТФХЦЧШЩбвгджзйклмнпрстфхцчшщ";
        for (char c : str.toCharArray()) {
            if (consonants.indexOf(c) != -1) {
                count++;
            }
        }
        return count;
    }
}