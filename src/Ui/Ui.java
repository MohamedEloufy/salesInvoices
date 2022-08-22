package Ui;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Ui extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem loadMenuItem;
    private JMenuItem saveMenuItem;

    private JPanel leftPanel;


    private JPanel rightPanel;
    protected JTable invoicesTable;

    protected JTable invoiceDataTable;

    private JButton btn1;

    private JButton btn2;
    private JButton btn3;
    private JButton btn4;

    private JTextField InVoiceData;
    private JTextField customerName;
    private JTextField invoiceNumber;
    private JTextField invoiceTotal;

    DefaultTableModel dtm;
    DefaultTableModel dtm2;


    public Ui() {
        super("Design preview [NewJFrame]");
        //setLayout(new GridLayout(1,2));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        menuBar = new JMenuBar();
        fileMenu = new JMenu("file");
        loadMenuItem = new JMenuItem("load file");
        loadMenuItem.addActionListener(this);
        loadMenuItem.setActionCommand("load");
        saveMenuItem = new JMenuItem("save file");
        saveMenuItem.addActionListener(this);
        saveMenuItem.setActionCommand("save data");
        menuBar.add(fileMenu);
        fileMenu.add(loadMenuItem);
        fileMenu.add(saveMenuItem);
        setJMenuBar(menuBar);


        // table
        String[] tblHead = {"No", "Data", "Customer Name", "Total"};
        dtm = new DefaultTableModel(tblHead, 0);
        invoicesTable = new JTable(dtm);
        invoicesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (invoicesTable.getSelectedRow() > -1) {
                    invoiceDataTable.setVisible(true);
                    String No = invoicesTable.getValueAt(invoicesTable.getSelectedRow(), 0).toString();
                    String Date = invoicesTable.getValueAt(invoicesTable.getSelectedRow(), 1).toString();
                    String customer_Name = invoicesTable.getValueAt(invoicesTable.getSelectedRow(), 2).toString();
                    String total = invoicesTable.getValueAt(invoicesTable.getSelectedRow(), 3).toString();


                    invoiceNumber.setText(No);
                    InVoiceData.setText(Date);
                    customerName.setText(customer_Name);
                    invoiceTotal.setText(total);

                    List<ItemRow> itemsList = getItemsByInvoiceId(Integer.parseInt(No));
                    populateItemsList(itemsList);

                } else {
                    invoiceNumber.setText("");
                    InVoiceData.setText("");
                    customerName.setText("");
                }


            }
        });
        invoicesTable.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 12));
        invoicesTable.setGridColor(Color.black);
        invoicesTable.setLocation(20, 20);
        invoicesTable.setDefaultEditor(Object.class, null);


        // buttons
        btn1 = new JButton("Create New Invoices");
        btn1.addActionListener(this);
        btn1.setActionCommand("new invoice");
        btn2 = new JButton("Delete Invoices");
        btn2.addActionListener(this);
        btn2.setActionCommand("delete invoice");


        JPanel leftOnePanel = new JPanel();
        leftOnePanel.setLayout(new BoxLayout(leftOnePanel, BoxLayout.Y_AXIS));
        leftOnePanel.add(new JLabel("Invoice Table"));
        leftOnePanel.add(new JScrollPane(invoicesTable));


        JPanel leftTwoPanel = new JPanel();
        leftTwoPanel.setLayout(new BoxLayout(leftTwoPanel, BoxLayout.X_AXIS));
        leftTwoPanel.add(btn1);
        leftTwoPanel.add(btn2);

        // left panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(leftOnePanel);
        leftPanel.add(leftTwoPanel);


        this.add(leftPanel);


// right side panel

        invoiceNumber = new JTextField();
        InVoiceData = new JTextField(20);
        customerName = new JTextField(20);
        invoiceTotal = new JTextField();


        String[] tbl2Head = {"No", "Item Name", "Item price", "count", "Item Total"};
        dtm2 = new DefaultTableModel(tbl2Head, 0);
        invoiceDataTable = new JTable(dtm2);
        invoiceDataTable.getTableHeader().setFont(new Font("SansSerif", Font.ITALIC, 12));
        invoiceDataTable.setGridColor(Color.black);
        invoiceDataTable.setLocation(20, 20);
        invoiceDataTable.setCellSelectionEnabled(true);


        btn3 = new JButton("Save");
        btn3.addActionListener(this);
        btn3.setActionCommand("save BTN");
        btn4 = new JButton("Cancel");
        add(btn3);
        add(btn4);


        JPanel right0nePanel = new JPanel();
        right0nePanel.setLayout(new BoxLayout(right0nePanel, BoxLayout.X_AXIS));
        right0nePanel.add(new JLabel("Invoice Number: "));
        right0nePanel.add(invoiceNumber);

        JPanel rightTwoPanel = new JPanel();
        rightTwoPanel.setLayout(new BoxLayout(rightTwoPanel, BoxLayout.X_AXIS));
        rightTwoPanel.add(new JLabel("InVoice Data:     "));
        rightTwoPanel.add(InVoiceData);

        JPanel rightThreePanel = new JPanel();
        rightThreePanel.setLayout(new BoxLayout(rightThreePanel, BoxLayout.X_AXIS));
        rightThreePanel.add(new JLabel("Customer Name:"));
        rightThreePanel.add(customerName);

        JPanel rightFourPanel = new JPanel();
        rightFourPanel.setLayout(new BoxLayout(rightFourPanel, BoxLayout.X_AXIS));
        rightFourPanel.add(new JLabel("Invoice Total:    "));
        rightFourPanel.add(invoiceTotal);

        JPanel rightFivePanel = new JPanel();
        rightFivePanel.setLayout(new BoxLayout(rightFivePanel, BoxLayout.Y_AXIS));
        rightFivePanel.setBorder(BorderFactory.createTitledBorder("Invoices Items"));
        rightFivePanel.add(invoiceDataTable);
        rightFivePanel.add(new JScrollPane(invoiceDataTable));

        JPanel rightSixPanel = new JPanel();
        rightSixPanel.setLayout(new BoxLayout(rightSixPanel, BoxLayout.X_AXIS));
        rightSixPanel.add(btn3);
        rightSixPanel.add(btn4);


        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.add(right0nePanel);
        rightPanel.add(rightTwoPanel);
        rightPanel.add(rightThreePanel);
        rightPanel.add(rightFourPanel);
        rightPanel.add(new JSeparator());
        rightPanel.add(rightFivePanel);
        rightPanel.add(rightSixPanel);


        this.add(rightPanel);


    }

    private void populateItemsList(List<ItemRow> itemsList) {
        clearItemsTable();
        for (ItemRow itemRow : itemsList) {
            dtm2.addRow(itemRow.toStringArray());

        }
    }

    private void clearItemsTable() {
        if (dtm2.getRowCount() > 0) {
            for (int i = dtm2.getRowCount() - 1; i > -1; i--) {
                dtm2.removeRow(i);
            }
        }
    }

    private List<ItemRow> getItemsByInvoiceId(int invoiceId) {
        List<ItemRow> subList = new ArrayList<>();
        for (ItemRow itemRow : itemsList) {
            if (itemRow.id == invoiceId) {
                subList.add(itemRow);
            }
        }
        return subList;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "load":
                try {

                    ReadFiles();
                   calculateTotalValue();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "save data":
                ExportFiles export=new ExportFiles();
                export.exportToCSV(invoicesTable,System.getProperty("user.dir")+"//downloadedFolder//invoices.csv" );
                export.exportToCSV(invoiceDataTable,System.getProperty("user.dir")+"//downloadedFolder//invoicesItem.csv");
                JOptionPane.showMessageDialog(this,"saved","confirm Saving",1);
                break;
            case "new invoice":
                dtm.addRow(AddInvoice.addInvoice());
                calculateTotalValue();
                break;
            case "delete invoice":
                if (invoicesTable.getSelectedRow() > -1) {
                    dtm.removeRow(invoicesTable.getSelectedRow());
                    invoiceTotal.setText("");
                }
                    if (invoicesTable.getSelectedRow() == -1)
                    {
                        invoiceDataTable.setVisible(false);
                    }else {
                        invoiceDataTable.setVisible(true);
                    }

                        break;


                    }
        }


    private ArrayList<String[]> invoiceList = new ArrayList<>();
    private ArrayList<ItemRow> itemsList = new ArrayList<>();

    public void ReadFiles() throws IOException {


        JFileChooser chooseFile = new JFileChooser();
        chooseFile.setMultiSelectionEnabled(true);
        int result = chooseFile.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File[] files = chooseFile.getSelectedFiles();

            BufferedReader reader = null;
            BufferedReader reader2 = null;
            String line ;
            String linee;

            try {
                reader = new BufferedReader(new FileReader(files[0]));
                while ((line = reader.readLine()) != null) {
                    String[] row = line.split(",");
                    invoiceList.add(row);
                    dtm.addRow(row);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                reader2 = new BufferedReader(new FileReader(files[1]));
                while ((linee = reader2.readLine()) != null) {
                    String[] rowTable2 = linee.split(",");
                    ItemRow itemRow = new ItemRow(
                            Integer.parseInt(rowTable2[0]),
                            rowTable2[1],
                            Integer.parseInt(rowTable2[2]),
                            Integer.parseInt(rowTable2[3]),
                            Integer.parseInt(rowTable2[2]) * Integer.parseInt(rowTable2[3])
                    );
                    itemsList.add(itemRow);
                    populateItemsList(itemsList);
                }

            } catch (Exception e) {
                e.printStackTrace();

            } /*finally {
                reader2.close();
            }*/

        }


    }

    public int calculateTotalValue() {
        int value;
        int sum = 0;
        for (int j = 0; j < invoicesTable.getRowCount(); j++) {
            for (int i = 0; i < invoiceDataTable.getRowCount(); i++) {

                if (invoiceDataTable.getValueAt(i, 0).equals(invoicesTable.getValueAt(j, 0))) {
                    value = Integer.parseInt((String) invoiceDataTable.getValueAt(i, 4));
                    sum += value;
                    invoicesTable.setValueAt(sum, j, 3);
                } else
                    sum = 0;
            }

        }
        return sum;
    }



}
