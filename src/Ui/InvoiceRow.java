package Ui;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceRow {

    private int num;
    private Date date;
    private String customerName;
    private double Total;

    public InvoiceRow(int num, Date date, String customerName, double total) {
        this.num = num;
        this.date = date;
        this.customerName = customerName;
        Total = total;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public int getNum() {
        return num;
    }

    public Date getDate() {
        return date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getTotal() {
        return Total;
    }

    public Object[] getInvoiceRow() {
        return new Object[]{num,date,customerName,Total};
    }

    public void collectAllInvoices() {

        ArrayList<Object[]> invoiceList = new ArrayList<>();
        invoiceList.add(getInvoiceRow());
    }
}
