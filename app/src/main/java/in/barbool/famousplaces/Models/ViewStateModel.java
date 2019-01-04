package in.barbool.famousplaces.Models;

import java.security.PrivateKey;

public class ViewStateModel {

    private String Image;
    private String Image1;
    private String Image2;
    private String Image3;
    private String Title;
    private String Desc;
    private String Address;
    private String Website;
    private String PhoneNumber;
    private String Star;
    private String Reviews;


    public ViewStateModel(){

    }

    public ViewStateModel(String image, String image1, String image2, String image3, String title, String desc, String address, String website, String phoneNumber, String star, String reviews) {
        Image = image;
        Image1 = image1;
        Image2 = image2;
        Image3 = image3;
        Title = title;
        Desc = desc;
        Address = address;
        Website = website;
        PhoneNumber = phoneNumber;
        Star = star;
        Reviews = reviews;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getWebsite() {
        return Website;
    }

    public void setWebsite(String website) {
        Website = website;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getImage1() {
        return Image1;
    }

    public void setImage1(String image1) {
        Image1 = image1;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String image2) {
        Image2 = image2;
    }

    public String getImage3() {
        return Image3;
    }

    public void setImage3(String image3) {
        Image3 = image3;
    }

    public String getStar() {
        return Star;
    }

    public void setStar(String star) {
        Star = star;
    }

    public String getReviews() {
        return Reviews;
    }

    public void setReviews(String reviews) {
        Reviews = reviews;
    }
}
