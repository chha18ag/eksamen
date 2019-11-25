package view;

import com.google.gson.JsonElement;
import controller.CustomerController;
import cyclops.control.Trampoline;
import model.Customer;

import java.io.IOException;
import java.util.List;

/**
 * The main view of the application showing main menu etc.
 */
public class MainView extends View {

    private CustomerController customerController;

    public MainView(CustomerController controller) {
        this.customerController = controller;
    }

    /**
     * For returning to the main menu.
     * @return String to be allowed to return to another method. See readme.md note, to learn more (optional).
     */
    public Trampoline<Void> returnToMainMenu() {
        try {
            System.out.println("Press any key to return to main menu");
            getNextStringValue();
            return Trampoline.more(this::MainMenu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Trampoline.more(this::MainMenu);
    }

    /**
     * Shows a customer.
     * @param customer The customer to show.
     * @return String to be allowed to return back to view. See readme.md note, to learn more (optional).
     */
    public Trampoline<Void> ShowCustomer(Customer customer)
    {
        System.out.println(customer);
        return Trampoline.more(this::returnToMainMenu);
    }

    /**
     * Shows all customers.
     * @param customers The customers to be shown.
     * @return String to be allowed to return back to view. See readme.md note, to learn more (optional).
     */
    public Trampoline<Void> ShowAllCustomers(List<Customer> customers)
    {
        for(Customer c : customers) {
            System.out.println(c);
        }
        return Trampoline.more(this::returnToMainMenu);
    }

    /**
     * Shows a single message, before returning to main menu.
     * @param message The message to show.
     * @return String to be allowed to return back to view. See readme.md note, to learn more (optional).
     */
    public Trampoline<Void> ShowMessage(String message)
    {
        System.out.println(message);
        return Trampoline.more(this::returnToMainMenu);
    }

    /**
     * The main menu.
     * @return String to be allowed to return back to view. See readme.md note, to learn more (optional).
     */
    public Trampoline<Void> MainMenu()
    {
        while (true)
        {
            clearConsole();
            System.out.println("Main menu:");
            System.out.println("1. Get all customers");
            System.out.println("2. Get customer");
            System.out.println("3. Create a new customer");
            System.out.println("4. Update a customer's information");
            System.out.println("");
            System.out.print("Please select an option from 1-n\r\n");

            try {
                switch (getNextPosIntValue()) {
                    case 1:
                        return Trampoline.more(() -> customerController.getAllCustomers());
                    case 2:
                        System.out.println("Enter user id");
                        try {
                            int id = Integer.parseInt(br.readLine());
                            if(id == 0)
                                System.out.println("No user by that id");
                            return Trampoline.more(() -> customerController.getCustomer(id));
                        } catch (Exception e) {
                            System.out.println("invalid input\nReturning to main menu");
                        };
                    case 3:
                        System.out.println("Enter user name");
                        String name = br.readLine();
                        return Trampoline.more(() -> customerController.createCustomer(name));
                    case 4:
                        System.out.println("Enter user id");
                        int id = Integer.parseInt(br.readLine());
                        System.out.println("Enter user name");
                        String username = br.readLine();
                        return Trampoline.more(() -> customerController.updateCustomer(id, username));
                    default:
                        System.out.println("invalid selection. Returning to main menu\n");
                }
            } catch (IOException ioe) {
                System.out.println("IO error trying to read your input!\r\n");
            }
        }
    }

}
