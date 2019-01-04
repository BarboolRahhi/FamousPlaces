package in.barbool.famousplaces.Models;

public class BookHotelModel {

    private String bookImage;
    private String bookWebsite;
    private String bookPrice;
    private String bookWebAddress;

    public BookHotelModel(){

    }

    public BookHotelModel(String bookImage, String bookWebsite, String bookPrice, String bookWebAddress) {
        this.bookImage = bookImage;
        this.bookWebsite = bookWebsite;
        this.bookPrice = bookPrice;
        this.bookWebAddress = bookWebAddress;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookWebsite() {
        return bookWebsite;
    }

    public void setBookWebsite(String bookWebsite) {
        this.bookWebsite = bookWebsite;
    }

    public String getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(String bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookWebAddress() {
        return bookWebAddress;
    }

    public void setBookWebAddress(String bookWebAddress) {
        this.bookWebAddress = bookWebAddress;
    }
}
