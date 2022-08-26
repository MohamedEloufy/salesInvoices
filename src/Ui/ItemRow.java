package Ui;

public class ItemRow {
    int id;
    String name;
    double price;
    int count;
    double total;

    public ItemRow(int id, String name, double price, int count, double total) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String[] toStringArray() {
        return new String[]{String.valueOf(id), name, String.valueOf(price), String.valueOf(count), String.valueOf(total)};
    }
}
