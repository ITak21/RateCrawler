package ITak21.RateCrawler.dto;

public class CompanyInfoDTO {
    private String star;
    private String link;
    private String category;
    private String location;
    private String about;
    private String error;

    // Getters and Setters
    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
