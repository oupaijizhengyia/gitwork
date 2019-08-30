public class Smoke {
    private Integer price;

    private String name;

    public void sell(){
        System.out.println("香烟"+this.name+"这次卖的价格是"+this.price+"元");
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
