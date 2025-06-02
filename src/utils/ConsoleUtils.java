package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Classe utilitária para interações com o console.
 * Fornece métodos para ler entradas do usuário, confirmar ações, limpar a tela e pausar a execução.
 */
public class ConsoleUtils {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Lê uma string do console, convertendo-a para maiúsculas.
     *
     * @param prompt a mensagem a ser exibida ao usuário
     * @return a string lida do console, em maiúsculas
     */
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim().toUpperCase();
    }

    /**
     * Lê uma string do console, mantendo o formato original.
     *
     * @param prompt a mensagem a ser exibida ao usuário
     * @return a string lida do console
     */
    public static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    /**
     * Lê um número decimal do console.
     *
     * @param prompt a mensagem a ser exibida ao usuário
     * @return o número decimal lido do console
     */
    public static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número decimal.");
            }
        }
    }

    /**
     * Lê uma data do console no formato "DD-MM-AAAA".
     *
     * @param prompt a mensagem a ser exibida ao usuário
     * @return a data lida do console como um objeto LocalDate
     */
    public static LocalDate readDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true) {
            try {
                String input = readString(prompt + " (DD-MM-AAAA): ");
                return LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Tente novamente.");
            }
        }
    }

    /**
     * Lê uma data do console no formato "AAAA-MM-DD".
     *
     * @param prompt a mensagem a ser exibida ao usuário
     * @return a data lida do console como um objeto LocalDate
     */
    public static boolean confirm(String prompt) {
        while (true) {
            System.out.print(prompt + " (S/N): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("s")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                System.out.println("Resposta inválida. Digite 'S' para sim ou 'N' para não.");
            }
        }
    }

    /**
     * Pausa a execução do programa até que o usuário pressione Enter.
     */
    public static void pause() {
        System.out.println("Pressione Enter para continuar...");
        scanner.nextLine();
    }

    /**
     * Limpa a tela do console.
     * Funciona tanto em sistemas Windows quanto Unix/Linux/MacOS.
     */
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix/Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // fallback: apenas imprimir várias linhas
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}

