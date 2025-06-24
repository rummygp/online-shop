package Product.Order;

public record Person(String name, String lastName, String address, String email, int age) {

    @Override
    public String toString() {
        return name + " " + lastName + ", adres: " + address + ", e-mail: " + email + ", wiek: " + age;
    }
}