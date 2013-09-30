package xlsmerger;

/**
 * Divasoft, inc.
 * @author Main Developer
 */
public class BalansBean implements Comparable<BalansBean>, IPrint {
    private String address;
    private String diametr;
    private String dlina;
    private String davleniye;
    private String sposob;
    private String matherial;
    private boolean checked=true;

    public BalansBean(String address, String diametr, String dlina, String davleniye, String sposob, String matherial) {
        this.address = address;
        this.diametr = diametr;
        this.dlina = dlina;
        this.davleniye = davleniye;
        this.sposob = sposob;
        this.matherial = matherial;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getMatherial() {
        return matherial;
    }

    public void setMatherial(String matherial) {
        this.matherial = matherial;
    }

    public String getSposob() {
        return sposob;
    }

    public void setSposob(String sposob) {
        this.sposob = sposob;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    @Override
    public int compareTo(BalansBean o) {
        return this.getAddress().compareTo(o.getAddress());
    }

    @Override
    public String toCSVLine(String S) {
        return getAddress()+S+getDavleniye()+S+getDiametr()+S+getDlina()+S+getMatherial()+S+getSposob();
    }

    @Override
    public boolean isPrint() {
        return isChecked();
    }
    
}