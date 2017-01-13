package jaihind.gobblessamerica.watchrecyclerview;

/**
 * Created by nande on 1/11/2017.
 */

public class Watch {

    public long _id;
    public String name;
    public int price;
    public String os;
    public int img_id;

    public Watch(String name, int price, String os,int img_id) {
        this.name = name;
        this.price = price;
        this.os = os;
        this.img_id=img_id;

    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}
