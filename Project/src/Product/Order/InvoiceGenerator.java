package Product.Order;

public class InvoiceGenerator {
    private static int invoiceCounter = 1;

    public String generateInvoice(Order order) {
        StringBuilder invoiceBuilder = new StringBuilder();

        invoiceBuilder.append("Faktura nr: FV/")
                .append(invoiceCounter++)
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
