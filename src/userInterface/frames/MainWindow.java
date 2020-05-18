package userInterface.frames;

import userInterface.panels.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {
    private JMenuBar menuBar;
    private JMenu infos, addNew, upDate, displayAll, search;
    private JMenuItem home, about, exitItem,
            addCustomer, addEmployee, addCoffee,
            updateCustomer, updateCoffee,
            allCustomers, allEmployees, allCoffees,
            searchOrders, searchAdvantages, searchServices;

    private MainPanel mainPanel;
    private Container windowContainer;

    public MainWindow() {
        super("CoffIG");
        this.setBounds(100, 50, 800, 600);
        this.addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent e) { System.exit(0); } } );
        this.setResizable(false);

        ImageIcon cupIcon = new ImageIcon(ClassLoader.getSystemResource("coffeeBean.png"));
        Image cupImage = cupIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        ImageIcon coffeeCup = new ImageIcon(cupImage);
        this.setIconImage(coffeeCup.getImage());

        windowContainer = this.getContentPane();
        windowContainer.setLayout(new BorderLayout());
        mainPanel = new MainPanel(this);
        windowContainer.add(mainPanel, BorderLayout.CENTER);

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);


        infos = new JMenu();
        ImageIcon beanIcon = new ImageIcon(ClassLoader.getSystemResource("coffeeCup.png"));
        Image beanImage = beanIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        ImageIcon coffeeBean = new ImageIcon(beanImage);
        infos.setIcon(coffeeBean);
        menuBar.add(infos);

        home = new JMenuItem("Accueil");
        infos.add(home);
        home.addActionListener(new HomeListener());

        about = new JMenuItem("À propos");
        infos.add(about);

        exitItem = new JMenuItem("Quitter");
        infos.add(exitItem);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
        exitItem.addActionListener(new ExitListener());



        addNew = new JMenu("Ajouter");
        menuBar.add(addNew);

        addCustomer = new JMenuItem("Ajouter un client");
        addNew.add(addCustomer);
        addCustomer.addActionListener(new AddCustomerListener());

        addEmployee = new JMenuItem("Ajouter un employé");
        addNew.add(addEmployee);
        addEmployee.addActionListener(new AddEmployeeListener());

        addCoffee = new JMenuItem("Ajouter un café");
        addNew.add(addCoffee);
        addCoffee.addActionListener(new AddCoffeeListener());

        upDate = new JMenu("Modifier");
        menuBar.add(upDate);

        updateCustomer = new JMenuItem("Modifier un client");
        upDate.add(updateCustomer);
        updateCustomer.addActionListener(new UpdateCustomerListener());

        updateCoffee = new JMenuItem("Modifier un café");
        upDate.add(updateCoffee);
        updateCoffee.addActionListener(new UpdateCoffeeListener());


        displayAll = new JMenu("Afficher");
        menuBar.add(displayAll);

        allCustomers = new JMenuItem("Afficher tous les clients");
        displayAll.add(allCustomers);
        allCustomers.addActionListener(new AllCustomerListener());

        allEmployees = new JMenuItem("Afficher tous les employés");
        displayAll.add(allEmployees);

        allCoffees = new JMenuItem("Afficher tous les cafés");
        displayAll.add(allCoffees);
        allCoffees.addActionListener(new AllCoffeesListener());


        search = new JMenu("Rechercher");
        menuBar.add(search);

        searchOrders = new JMenuItem("Rechercher les anciennes commandes d'un client");
        search.add(searchOrders);
        searchOrders.addActionListener(new SearchOrdersListener());

        searchAdvantages = new JMenuItem("Rechercher les avantages d'un client");
        search.add(searchAdvantages);

        searchServices = new JMenuItem("Rechercher les services d'un employé");
        search.add(searchServices);
        searchServices.addActionListener(new SearchServicesListener());

        setVisible(true);
    }

    public Container getWindowContainer() {
        return windowContainer;
    }

    public void resetSize() {
        if (getHeight() != 800)
            setSize(800, 600);
    }

    private class HomeListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            windowContainer.removeAll();
            windowContainer.add(new MainPanel(MainWindow.this));
            windowContainer.repaint();
            MainWindow.this.setVisible(true);
            resetSize();
        }
    }

    private class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            System.exit(0);
            /*try {
                SingletonConnection.getInstance().close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }*/
        }
    }

    private class AddCustomerListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            windowContainer.removeAll();
            UserForm user = new UserForm(null);
            CustomerForm customerForm = new CustomerForm(user, null);
            windowContainer.add(user, BorderLayout.NORTH);
            windowContainer.add(customerForm, BorderLayout.CENTER);
            windowContainer.add(new ButtonsAddUserForm(MainWindow.this, customerForm), BorderLayout.SOUTH);
            windowContainer.repaint();
            MainWindow.this.setVisible(true);

            resetSize();
        }
    }

    private class AddEmployeeListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            windowContainer.removeAll();
            UserForm user = new UserForm(null);
            EmployeeForm employeeForm = new EmployeeForm(user);
            windowContainer.add(user, BorderLayout.NORTH);
            windowContainer.add(employeeForm, BorderLayout.CENTER);
            windowContainer.add(new ButtonsAddUserForm(MainWindow.this, employeeForm), BorderLayout.SOUTH);
            windowContainer.repaint();
            MainWindow.this.setVisible(true);
        }
    }

    private class AddCoffeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            windowContainer.removeAll();
            windowContainer.add(new CoffeeForm(MainWindow.this, null), BorderLayout.CENTER);
            windowContainer.add(new ButtonsAddCoffeeForm(MainWindow.this), BorderLayout.SOUTH);
            windowContainer.repaint();
            MainWindow.this.setVisible(true);

            resetSize();
        }
    }

    private class UpdateCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            UpdateCustomersFrame updateCustomersFrame = new UpdateCustomersFrame(MainWindow.this);
        }
    }

    private class UpdateCoffeeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            UpdateCoffeesFrame updateCoffeesFrame = new UpdateCoffeesFrame(MainWindow.this);
        }
    }

    private class AllCustomerListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            AllCustomersFrame allCustomerFrame = new AllCustomersFrame(MainWindow.this);
            resetSize();
        }
    }

    private class AllCoffeesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AllCoffeesFrame frame = new AllCoffeesFrame();
            windowContainer.removeAll();
            windowContainer.add(new MainPanel(MainWindow.this));
            windowContainer.repaint();
            MainWindow.this.setVisible(true);
        }
    }

    private class SearchOrdersListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            windowContainer.removeAll();
            windowContainer.add(new SearchOldOrdersPanel());
            windowContainer.repaint();
            MainWindow.this.setVisible(true);
            setSize(getWidth(), 200);
        }
    }

    private class SearchServicesListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            windowContainer.removeAll();
            windowContainer.add(new SearchAssignmentsPanel());
            windowContainer.repaint();
            MainWindow.this.setVisible(true);
            setSize(getWidth(), 160);
        }
    }

    public void goBackHome(){
        windowContainer.removeAll();
        windowContainer.add(new MainPanel(MainWindow.this));
        windowContainer.repaint();
        setVisible(true);
    }

}
