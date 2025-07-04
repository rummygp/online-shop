package services;

import model.ClientItems;
import model.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class OrderPersistenceService {

    private static final String FILE_PATH = "orders.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public void saveOrderToFile(Order order) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write("=== Zamówienie ===\n");
            writer.write("ID zamówienia: " + order.getOrderId() + "\n");
            writer.write("Data zamówienia: " + order.getOrderDate().atZone(ZoneId.systemDefault()).format(DATE_FORMATTER) + "\n");
            writer.write("Klient: " + order.getClient() + "\n");
            writer.write("Produkty:\n");

            for (ClientItems item : order.getOrderedItems()) {
                writer.write("- " + item.getFinalProduct().getProduct().getName() +
                        ", ilość: " + item.getQuantity() +
                        ", cena końcowa: " + item.getTotalPrice() + " zł\n");
            }

            writer.write("Suma: " + order.getTotalPrice() + " zł\n");
            writer.write("----------------------------\n\n");
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania zamówienia do pliku: " + e.getMessage());
        }
    }
}

