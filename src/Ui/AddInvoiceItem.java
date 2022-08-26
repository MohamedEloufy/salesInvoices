package Ui;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AddInvoiceItem {


    protected static Object[] addInvoice()  {
        JTextField xField = new JTextField();
        JTextField yField = new JTextField();
        JTextField zField = new JTextField();

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("invoice number:"));
        myPanel.add(xField);
        //myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("invoice date:"));
        myPanel.add(yField);
        // myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("customer name:"));
        myPanel.add(zField);


        JOptionPane.showMessageDialog(null, myPanel);

        Object[] newInvoice = {Integer.parseInt(xField.getText()),yField.getText(), zField.getText(), "0"};


        return newInvoice;
    }
    protected static Object[] addItem() {
        JTextField eField = new JTextField();
        JTextField xField = new JTextField();
        JTextField yField = new JTextField();
        JTextField zField = new JTextField();

        JPanel myPanel = new JPanel();
        myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.Y_AXIS));
        myPanel.add(new JLabel("No :"));
        myPanel.add(xField);
        //myPanel.add(Box.createHorizontalStrut(15)); // a spacer
        myPanel.add(new JLabel("item name"));
        myPanel.add(yField);
        // myPanel.add(Box.createHorizontalStrut(15));
        myPanel.add(new JLabel("item price"));
        myPanel.add(zField);
        myPanel.add(new JLabel("count"));
        myPanel.add(eField);


        JOptionPane.showMessageDialog(null, myPanel);


        Object[] newItem = {Integer.parseInt(xField.getText()), yField.getText(), Double.parseDouble(zField.getText()), Integer.parseInt(eField.getText())};


        return newItem;
    }

}
