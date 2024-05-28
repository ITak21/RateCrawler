package ITak21.RateCrawler.dto;

public class FavoriteCompanyDTO {
    private String companyName;
    private String category;

    public FavoriteCompanyDTO(String companyName, String category) {
        this.companyName = companyName;
        this.category = category;
    }

    // Getters and Setters
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
