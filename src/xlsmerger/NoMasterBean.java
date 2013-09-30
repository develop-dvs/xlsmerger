package xlsmerger;

/**
 * Divasoft, inc.
 * @author Main Developer
 */
public class NoMasterBean implements Comparable<NoMasterBean>, IPrint {
    private String address;
    private String date;
    private String davleniye;
    private String diametr;
    private String dlina;
    private boolean checked=true;
    
    public NoMasterBean(String address, String date, String davleniye, String diametr, String dlina) {
        this.address = address;
        this.date = date;
        this.davleniye = Util.normalizeDavleniye(davleniye);
        this.diametr = Util.dotTo(diametr);
        this.dlina = Util.dotTo(dlina);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    @Override
    public int compareTo(NoMasterBean o) {
        return this.getAddress().compareTo(o.getAddress());
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
