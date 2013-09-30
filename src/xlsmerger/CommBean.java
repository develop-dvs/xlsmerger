package xlsmerger;

/**
 * Divasoft, inc.
 * @author Main Developer
 */
public class CommBean implements Comparable<CommBean>, IPrint  {
    private String name;
    private String INN;
    private String date;
    private String date_in;
    private String dlina;
    private String diametr;
    private String davleniye;
    private boolean checked=true;

    public CommBean(String name, String INN, String date, String date_in, String dlina, String diametr, String davleniye) {
        this.name = name;
        this.INN = INN;
        this.date = date;
        this.date_in = date_in;
        this.dlina = Util.dotTo(dlina);
        this.diametr = Util.dotTo(diametr);
        this.davleniye = Util.normalizeDigitDavleniye(Util.dotTo(davleniye));
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate_in() {
        return date_in;
    }

    public void setDate_in(String date_in) {
        this.date_in = date_in;
    }

    public String getDavleniye() {
        return davleniye;
    }

    public void setDavleniye(String davleniye) {
        this.davleniye = davleniye;
    }

    public String getDiametr() {
        return diametr;
    }

    public void setDiametr(String diametr) {
        this.diametr = diametr;
    }

    public String getDlina() {
        return dlina;
    }

    public void setDlina(String dlina) {
        this.dlina = dlina;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(CommBean o) {
        return this.getName().compareTo(o.getName());
    }

    @Override
    public String toCSVLine(String S) {
        return "";
    }

    @Override
    public boolean isPrint() {
        return isChecked();
    }
    
    
}
