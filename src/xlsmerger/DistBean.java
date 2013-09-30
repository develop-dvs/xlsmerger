package xlsmerger;

/**
 * Divasoft, inc.
 * @author Main Developer
 */
public class DistBean {
    private String name;
    private String content;

    public DistBean(String name, String content) {
        this.name = name;
        this.content = content.toLowerCase().trim();
    }

    public DistBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    
}
