import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame 
{
    private static JPanel sidePanel;
    private static List<Sale> salesList = new ArrayList<>();
    private static List<Customer> customersList = new ArrayList<>();
    private static List<Vehicle> vehiclesList = new ArrayList<>();

    public static void main(String[] args) 
    {
        // Create the main frame
        JFrame frame = new JFrame("Sales System");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the menu panel
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(200, 225, 255));
        menuPanel.setLayout(new GridLayout(7, 1, 0, 20));
        String[] options = {"Add Sale", "Add Customer", "Add Vehicle", "View Sales", "View Customers", "View Vehicles", "Exit"};

        // Add buttons to the menu panel
        for (String option : options) 
        {
            JButton button = new JButton(option);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setForeground(Color.WHITE);
            button.setBackground(new Color(50, 120, 200));
            button.setBorder(new EmptyBorder(10, 30, 10, 30));
            button.addActionListener(e -> handleButtonClick(button.getText()));
            menuPanel.add(button);
        }

        // Create the side panel
        sidePanel = new JPanel();
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Create the image label
        ImageIcon icon = new ImageIcon("sales-image.jpg");
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        // Add components to the frame
        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(sidePanel, BorderLayout.CENTER);
        frame.add(imageLabel, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private static void handleButtonClick(String buttonLabel) 
    {
        sidePanel.removeAll();

        // Handle button clicks based on the button label
        if (buttonLabel.equals("Add Sale")) 
        {
            addSale();
        } else if (buttonLabel.equals("Add Customer"))
        {
            addCustomer();
        } else if (buttonLabel.equals("Add Vehicle"))
        {
            addVehicle();
        } else if (buttonLabel.equals("View Sales")) 
        {
            viewSales();
        } else if (buttonLabel.equals("View Customers")) 
        {
            viewCustomers();
        } else if (buttonLabel.equals("View Vehicles"))
        {
            viewVehicles();
        } else if (buttonLabel.equals("Exit")) 
        {
            System.exit(0);
        }

        // Refresh the side panel
        sidePanel.revalidate();
        sidePanel.repaint();
    }


    private static Sale addSale()
    {
        // Prompt the user for sale details
        String item = JOptionPane.showInputDialog("Enter car name:");
        int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter quantity:"));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));

        // Create a Sale object and add it to the sales list
        Sale sale = new Sale(item, quantity, price);
        salesList.add(sale);

        // Display success message
        JOptionPane.showMessageDialog(null, "Sale added successfully.");

        // Return the created Sale object
        return sale;  
    }

    private static void addCustomer() 
    {
        // Prompt the user for customer details
        String firstName = JOptionPane.showInputDialog("Enter first name:");
        String lastName = JOptionPane.showInputDialog("Enter last name:");
        String email = JOptionPane.showInputDialog("Enter email:");
        String phoneNumber = JOptionPane.showInputDialog("Enter phone number:");

        // Create a Customer object and add it to the customers list
        Customer customer = new Customer(firstName, lastName, email, phoneNumber);
        customersList.add(customer);

        // Display success message
        JOptionPane.showMessageDialog(null, "Customer added successfully.");
    }

    private static void addVehicle() 
    {
        // Prompt the user for vehicle details
        String make = JOptionPane.showInputDialog("Enter Car Brand:");
        String model = JOptionPane.showInputDialog("Enter model:");
        int year = Integer.parseInt(JOptionPane.showInputDialog("Enter year:"));
        double price = Double.parseDouble(JOptionPane.showInputDialog("Enter price:"));

        // Create a Vehicle object (Car in this case) and add it to the vehicles list
        Vehicle vehicle = new Car(make, model, year, price, ""); // Empty VIN for now
        vehiclesList.add(vehicle);

        // Display success message
        JOptionPane.showMessageDialog(null, "Vehicle added successfully.");
    }

    private static void viewSales() 
    {
        // Check if sales list is empty
        if (salesList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No sales available.");
        } else 
        {
            // Create columns and data arrays for the sales table
            String[] columns = {"Item", "Quantity", "Price"};
            Object[][] data = new Object[salesList.size()][3];
            int i = 0;
            for (Sale sale : salesList) 
            {
                // Populate the data array with sale information
                data[i][0] = sale.getName();
                data[i][1] = sale.getQuantity();
                data[i][2] = String.format("$%.2f", sale.getPrice());
                i++;
            }
            
            // Create a JTable with sales data and add it to a scroll pane
            JTable salesTable = new JTable(data, columns);
            JScrollPane salesScrollPane = new JScrollPane(salesTable);

            // Calculate total sales
            double totalSalesAmount = 0;
            for (Sale s : salesList) 
            {
                totalSalesAmount += s.getQuantity() * s.getPrice();
            }

            // Format the total sales amount
            String formattedTotalSales = String.format("$%.2f", totalSalesAmount);

            // Create a label to display the total sales amount
            JLabel totalSalesLabel = new JLabel("Total Sales: " + formattedTotalSales);
            totalSalesLabel.setFont(new Font("Arial", Font.BOLD, 16));

            // Create a panel to hold the table and total sales label
            JPanel salesTablePanel = new JPanel(new BorderLayout());
            salesTablePanel.add(salesScrollPane, BorderLayout.CENTER);
            salesTablePanel.add(totalSalesLabel, BorderLayout.SOUTH);

            // Refresh the side panel with the sales table panel
            sidePanel.removeAll();
            sidePanel.add(salesTablePanel, BorderLayout.CENTER);
            sidePanel.revalidate();
            sidePanel.repaint();
        }
    }

    private static void viewCustomers() 
    {
        // Check if customers list is empty
        if (customersList.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "No customers available.");
        } else 
        {
            // Create columns and data arrays for the customers table
            String[] columns = {"First Name", "Last Name", "Email", "Phone Number"};
            Object[][] data = new Object[customersList.size()][4];
            int i = 0;
            for (Customer customer : customersList) 
            {
                // Populate the data array with customer information
                data[i][0] = customer.getFirstName();
                data[i][1] = customer.getLastName();
                data[i][2] = customer.getEmail();
                data[i][3] = customer.getPhone();
                i++;
            }

            // Create a JTable with customers data and add it to a scroll pane
            JTable customersTable = new JTable(data, columns);
            JScrollPane customersScrollPane = new JScrollPane(customersTable);
            sidePanel.add(customersScrollPane);
        }
    }

    private static void viewVehicles() 
    {
        // Check if vehicles list is empty
        if (vehiclesList.isEmpty()) 
        {
            JOptionPane.showMessageDialog(null, "No vehicles available.");
        } else {
            // Create columns and data arrays for the vehicles table
            String[] columns = {"Make", "Model", "Year", "Price"};
            Object[][] data = new Object[vehiclesList.size()][4];
            int i = 0;
            for (Vehicle vehicle : vehiclesList) 
            {
                // Populate the data array with vehicle information
                data[i][0] = vehicle.getMake();
                data[i][1] = vehicle.getModel();
                data[i][2] = vehicle.getYear();
                data[i][3] = "$" + formatPrice(vehicle.getPrice());
                i++;
            }

            // Create a JTable with vehicles data and add it to a scroll pane
            JTable vehiclesTable = new JTable(data, columns);
            JScrollPane vehiclesScrollPane = new JScrollPane(vehiclesTable);
            sidePanel.add(vehiclesScrollPane);
        }
    }

    private static String formatPrice(double price) 
    {
        if (price % 1 == 0) 
        {
            // Price is a whole number, remove decimal places and trailing zeros
            return String.format("%.0f", price);
        } else {
            // Price has decimal places, display with two decimal places
            return String.format("%.2f", price);
        }
    }
}