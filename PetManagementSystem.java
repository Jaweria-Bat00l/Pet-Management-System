package petshopmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Base class Pet
class Pet {
    private int id;
    private String name;
    private String type;
    private String owner;

    public Pet(int id, String name, String type, String owner) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.owner = owner;
    }

    public void updateDetails(String name, String type, String owner) {
        this.name = name;
        this.type = type;
        this.owner = owner;
    }

    public String toString() {
        return "Pet ID: " + id + ", Name: " + name + ", Type: " + type + ", Owner: " + owner;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }
}

// Base class Customer
class Customer {
    private int id;
    private String name;
    private String email;
    private String phone;
    private ArrayList<Pet> pets;

    public Customer(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.pets = new ArrayList<>();
    }

    public void updateDetails(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void addPet(Pet pet) {
        pets.add(pet);
    }

    public String toString() {
        StringBuilder petDetails = new StringBuilder("Pets: ");
        for (Pet pet : pets) {
            petDetails.append(pet.getName()).append(" (").append(pet.getType()).append("), ");
        }
        return "Customer ID: " + id + ", Name: " + name + ", Email: " + email + ", Phone: " + phone + ", " + petDetails;
    }

    public int getId() {
        return id;
    }
}

// Class Category (inherits from Pet)
class Category extends Pet {
    private String categoryName;

    public Category(int id, String name, String type, String owner, String categoryName) {
        super(id, name, type, owner);
        this.categoryName = categoryName;
    }

    public void updateDetails(String categoryName) {
        this.categoryName = categoryName;
    }

    public String toString() {
        return super.toString() + ", Category: " + categoryName;
    }
}

// Class Bill
class Bill {
    private int id;
    private Customer customer;
    private double amount;
    private String date;

    public Bill(int id, Customer customer, double amount, String date) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.date = date;
    }

    public void updateDetails(double amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    public String toString() {
        return "Bill ID: " + id + ", Customer: " + customer + ", Amount: $" + amount + ", Date: " + date;
    }
}

// Main class for Pet Management System
public class PetManagementSystem extends JFrame {
    private ArrayList<Pet> pets = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Bill> bills = new ArrayList<>();

    public PetManagementSystem() {
        // Setting up the JFrame
        setTitle("Pet Shop Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Adding buttons for each action
        JButton managePetsButton = new JButton("Manage Pets");
        JButton manageCustomersButton = new JButton("Manage Customers");
        JButton manageCategoriesButton = new JButton("Manage Categories");
        JButton manageBillingButton = new JButton("Manage Billing");

        managePetsButton.addActionListener(e -> openManagePetsFrame());
        manageCustomersButton.addActionListener(e -> openManageCustomersFrame());
        manageCategoriesButton.addActionListener(e -> openManageCategoriesFrame());
        manageBillingButton.addActionListener(e -> openManageBillingFrame());

        // Adding buttons to the main frame
        add(managePetsButton);
        add(manageCustomersButton);
        add(manageCategoriesButton);
        add(manageBillingButton);

        setVisible(true);
    }

    // Helper function to update the JTextArea with current data
    private void updateTextArea(JTextArea textArea, String data) {
        textArea.setText(data);
    }

    private void openManagePetsFrame() {
        JFrame petsFrame = new JFrame("Manage Pets");
        petsFrame.setSize(300, 300);
        petsFrame.setLayout(new FlowLayout());
        petsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel idLabel = new JLabel("Pet ID:");
        JTextField idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);
        JLabel typeLabel = new JLabel("Type:");
        JTextField typeField = new JTextField(10);
        JLabel ownerLabel = new JLabel("Owner:");
        JTextField ownerField = new JTextField(10);

        JTextArea petsTextArea = new JTextArea(10, 25);
        petsTextArea.setEditable(false);

        // Buttons to manage pets
        JButton addPetButton = new JButton("Add Pet");
        addPetButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String type = typeField.getText();
                String owner = ownerField.getText();
                Pet pet = new Pet(id, name, type, owner);
                pets.add(pet);
                updateTextArea(petsTextArea, getPetsData());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(petsFrame, "Invalid ID.");
            }
        });

        petsFrame.add(idLabel);
        petsFrame.add(idField);
        petsFrame.add(nameLabel);
        petsFrame.add(nameField);
        petsFrame.add(typeLabel);
        petsFrame.add(typeField);
        petsFrame.add(ownerLabel);
        petsFrame.add(ownerField);
        petsFrame.add(addPetButton);
        petsFrame.add(new JScrollPane(petsTextArea));

        petsFrame.setVisible(true);
    }

    private String getPetsData() {
        StringBuilder data = new StringBuilder("Current Pets:\n");
        for (Pet pet : pets) {
            data.append(pet.toString()).append("\n");
        }
        return data.toString();
    }

    private void openManageCustomersFrame() {
        JFrame customersFrame = new JFrame("Manage Customers");
        customersFrame.setSize(300, 300);
        customersFrame.setLayout(new FlowLayout());
        customersFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel idLabel = new JLabel("Customer ID:");
        JTextField idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(10);
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField(10);
        JLabel petNameLabel = new JLabel("Pet Name:");
        JTextField petNameField = new JTextField(10);
        JLabel petTypeLabel = new JLabel("Pet Type:");
        JTextField petTypeField = new JTextField(10);

        JTextArea customersTextArea = new JTextArea(10, 25);
        customersTextArea.setEditable(false);

        JButton addCustomerButton = new JButton("Add Customer");
        addCustomerButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String email = emailField.getText();
                String phone = phoneField.getText();
                Customer customer = new Customer(id, name, email, phone);
                customers.add(customer);

                // Add Pet to customer
                String petName = petNameField.getText();
                String petType = petTypeField.getText();
                Pet pet = new Pet(id, petName, petType, name);
                customer.addPet(pet);

                updateTextArea(customersTextArea, getCustomersData());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(customersFrame, "Invalid ID.");
            }
        });

        customersFrame.add(idLabel);
        customersFrame.add(idField);
        customersFrame.add(nameLabel);
        customersFrame.add(nameField);
        customersFrame.add(emailLabel);
        customersFrame.add(emailField);
        customersFrame.add(phoneLabel);
        customersFrame.add(phoneField);
        customersFrame.add(petNameLabel);
        customersFrame.add(petNameField);
        customersFrame.add(petTypeLabel);
        customersFrame.add(petTypeField);
        customersFrame.add(addCustomerButton);
        customersFrame.add(new JScrollPane(customersTextArea));

        customersFrame.setVisible(true);
    }

    private String getCustomersData() {
        StringBuilder data = new StringBuilder("Current Customers:\n");
        for (Customer customer : customers) {
            data.append(customer.toString()).append("\n");
        }
        return data.toString();
    }

    private void openManageCategoriesFrame() {
        JFrame categoriesFrame = new JFrame("Manage Categories");
        categoriesFrame.setSize(300, 300);
        categoriesFrame.setLayout(new FlowLayout());
        categoriesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel idLabel = new JLabel("Category ID:");
        JTextField idField = new JTextField(10);
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(10);
        JLabel typeLabel = new JLabel("Type:");
        JTextField typeField = new JTextField(10);
        JLabel categoryNameLabel = new JLabel("Category Name:");
        JTextField categoryNameField = new JTextField(10);

        JTextArea categoriesTextArea = new JTextArea(10, 25);
        categoriesTextArea.setEditable(false);

        JButton addCategoryButton = new JButton("Add Category");
        addCategoryButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String type = typeField.getText();
                String categoryName = categoryNameField.getText();
                Category category = new Category(id, name, type, name, categoryName);
                categories.add(category);

                updateTextArea(categoriesTextArea, getCategoriesData());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(categoriesFrame, "Invalid ID.");
            }
        });

        categoriesFrame.add(idLabel);
        categoriesFrame.add(idField);
        categoriesFrame.add(nameLabel);
        categoriesFrame.add(nameField);
        categoriesFrame.add(typeLabel);
        categoriesFrame.add(typeField);
        categoriesFrame.add(categoryNameLabel);
        categoriesFrame.add(categoryNameField);
        categoriesFrame.add(addCategoryButton);
        categoriesFrame.add(new JScrollPane(categoriesTextArea));

        categoriesFrame.setVisible(true);
    }

    private String getCategoriesData() {
        StringBuilder data = new StringBuilder("Current Categories:\n");
        for (Category category : categories) {
            data.append(category.toString()).append("\n");
        }
        return data.toString();
    }

    private void openManageBillingFrame() {
        JFrame billingFrame = new JFrame("Manage Billing");
        billingFrame.setSize(300, 300);
        billingFrame.setLayout(new FlowLayout());
        billingFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel idLabel = new JLabel("Bill ID:");
        JTextField idField = new JTextField(10);
        JLabel customerLabel = new JLabel("Customer ID:");
        JTextField customerField = new JTextField(10);
        JLabel amountLabel = new JLabel("Amount:");
        JTextField amountField = new JTextField(10);
        JLabel dateLabel = new JLabel("Date:");
        JTextField dateField = new JTextField(10);

        JTextArea billingTextArea = new JTextArea(10, 25);
        billingTextArea.setEditable(false);

        JButton addBillButton = new JButton("Add Bill");
        addBillButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                int customerId = Integer.parseInt(customerField.getText());
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();

                // Find the customer by ID
                Customer customer = null;
                for (Customer c : customers) {
                    if (c.getId() == customerId) {
                        customer = c;
                        break;
                    }
                }

                if (customer != null) {
                    Bill bill = new Bill(id, customer, amount, date);
                    bills.add(bill);
                    updateTextArea(billingTextArea, getBillingData());
                } else {
                    JOptionPane.showMessageDialog(billingFrame, "Customer not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(billingFrame, "Invalid input.");
            }
        });

        billingFrame.add(idLabel);
        billingFrame.add(idField);
        billingFrame.add(customerLabel);
        billingFrame.add(customerField);
        billingFrame.add(amountLabel);
        billingFrame.add(amountField);
        billingFrame.add(dateLabel);
        billingFrame.add(dateField);
        billingFrame.add(addBillButton);
        billingFrame.add(new JScrollPane(billingTextArea));

        billingFrame.setVisible(true);
    }

    private String getBillingData() {
        StringBuilder data = new StringBuilder("Current Bills:\n");
        for (Bill bill : bills) {
            data.append(bill.toString()).append("\n");
        }
        return data.toString();
    }

    public static void main(String[] args) {
        new PetManagementSystem();
    }
}
