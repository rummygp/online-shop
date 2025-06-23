package Product.Order;

public class OrderProcessor {

    private final InvoiceGenerator invoiceGenerator = new InvoiceGenerator();

    public String processOrder(Order order) {
        if(order == null) {
            throw new IllegalArgumentException("Zamówienie nie może być puste");
        }
        return invoiceGenerator.generateInvoice(order);
    }
}
