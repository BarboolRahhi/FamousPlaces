package in.barbool.famousplaces.Models;

public class ReviewsModel {
    private String userName;
    private String review;
    private String date;
    private String star;

    public ReviewsModel(){}

    public ReviewsModel(String userName, String review, String date, String star) {
        this.userName = userName;
        this.review = review;
        this.date = date;
        this.star = star;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
