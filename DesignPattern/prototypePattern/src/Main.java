import clone.Address;
import clone.Customer;

public class Main {

    public static void main(String[] args) {
        Customer c1 = new Customer();
        c1.setName("张三");
        Address address = new Address();
        address.setPostNum("001");
        address.setDepartment("西湖区");
        c1.setAddress(address);

        Customer c2 = c1.clone();
        System.out.println("c1: "+c1);
        System.out.println("c2: "+c2);
        System.out.println(c1==c2);
    }
}
