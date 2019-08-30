package clone;

/**
 * FileName: Customer
 * Author: yeyang
 * Date: 2018/6/5 10:39
 */
public class Customer implements Cloneable{
    private String name;
    private Address address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }

    @Override
    public Customer clone(){
        Customer a = null;
        try {
            a = (Customer) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return a;
    }
}
