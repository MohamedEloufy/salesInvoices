package Ui;

import javax.swing.*;

public class AddInvoice {

    protected static String[] addInvoice() {
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


        String[] newRow = {xField.getText(), yField.getText(), zField.getText(), "0"};


        return newRow;
    }
}
