
import Ui.Ui;


import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Ui frame = new Ui();
        frame.setVisible(true);
        frame.readDataInvoiceHeaderTable();
        frame.readDataInvoiceItemTable();
        frame.calculateInvoiceTableTotals();
        frame.invoiceDataTable.setVisible(false);


    }

}