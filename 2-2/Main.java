import java.util.Scanner;
// Classe de informações de contato (Product)
class ContactInfo {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public ContactInfo(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
               "Address: " + address + "\n" +
               "Phone Number: " + phoneNumber + "\n" +
               "Email: " + email + "\n";
    }
}

// Builder Interface
interface ContactBuilder {
    ContactBuilder setName(String name);
    ContactBuilder setAddress(String address);
    ContactBuilder setPhoneNumber(String phoneNumber);
    ContactBuilder setEmail(String email);
    ContactInfo build();
}

// Builder para Contato Completo
class CompleteContactBuilder implements ContactBuilder {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    @Override
    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ContactBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    @Override
    public ContactBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public ContactBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public ContactInfo build() {
        return new ContactInfo(name, address, phoneNumber, email);
    }
}

// Builder para Contato com Internet
class InternetContactBuilder implements ContactBuilder {
    private String name;
    private String email;

    @Override
    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ContactBuilder setAddress(String address) {
        return this;
    }

    @Override
    public ContactBuilder setPhoneNumber(String phoneNumber) {
        return this;
    }

    @Override
    public ContactBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public ContactInfo build() {
        return new ContactInfo(name, null, null, email);
    }
}

// Builder para Contato com Telefone
class PhoneContactBuilder implements ContactBuilder {
    private String name;
    private String phoneNumber;

    @Override
    public ContactBuilder setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public ContactBuilder setAddress(String address) {
        return this;
    }

    @Override
    public ContactBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    @Override
    public ContactBuilder setEmail(String email) {
        return this;
    }

    @Override
    public ContactInfo build() {
        return new ContactInfo(name, null, phoneNumber, null);
    }
}

// Director para construir o contato
class ContactDirector {
    private ContactBuilder builder;

    public ContactDirector(ContactBuilder builder) {
        this.builder = builder;
    }

    public void constructContact(String name, String address, String phoneNumber, String email) {
        builder.setName(name)
               .setAddress(address)
               .setPhoneNumber(phoneNumber)
               .setEmail(email);
    }

    public ContactInfo getContact() {
        return builder.build();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ContactDirector director = new ContactDirector(new CompleteContactBuilder());
        System.out.println("Construindo um Contato Completo:");
        System.out.print("Nome: ");
        String name = scanner.nextLine();
        System.out.print("Endereço: ");
        String address = scanner.nextLine();
        System.out.print("Telefone: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        director.constructContact(name, address, phoneNumber, email);
        ContactInfo completeContact = director.getContact();
        System.out.println("\nContato Completo:\n" + completeContact);
    }
}