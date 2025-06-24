package Product.Order.InvoiceService;

import Product.Order.ClientItems;
import Product.Order.Order;

import java.time.format.DateTimeFormatter;

public class InvoiceGenerator {
    private static int invoiceCounter = 1;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public String generateInvoice(Order order) {
        StringBuilder invoiceBuilder = new StringBuilder();


        invoiceBuilder.append("Faktura nr: FV/")
                .append(invoiceCounter++)
                .append("\nData zamówienia: ").append(order.getOrderDate().format(DATE_FORMATTER))
                .append("\n----------------\n");
        invoiceBuilder.append("Klient: ").append(order.getClient()).append("\n");

        for (ClientItems item : order.getOrderedItems()) {
            invoiceBuilder.append("- ")
                    .append(item.getFinalProduct().getProduct().getName())
                    .append(" x ")
                    .append(item.getQuantity())
                    .append(" = ")
                    .append(item.getTotalPrice())
                    .append(" PLN\n");
        }
        invoiceBuilder.append("--------------------------\n")
                .append("Suma do zapłaty: ")
                .append(order.getTotalPrice())
                .append(" PLN\n");

        return invoiceBuilder.toString();
    }
}
