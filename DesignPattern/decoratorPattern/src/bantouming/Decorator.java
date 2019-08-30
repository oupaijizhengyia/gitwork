package bantouming;

/**
 * FileName: Decorator
 * Author: yeyang
 * Date: 2018/7/2 9:47
 */
public  class Decorator implements Document {
    private Document document;

    public Decorator(Document document){
        this.document = document;
    }

    @Override
    public void display() {
        document.display();
    }
}
