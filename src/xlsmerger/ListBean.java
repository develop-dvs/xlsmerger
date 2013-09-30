package xlsmerger;
/**
 * Divasoft, inc.
 * @author Main Developer
 */
public class ListBean implements Comparable<ListBean>, IPrint {
    private String num;
    private String catGP;
    private String name;
    private String diametr;
    private String dlina;
    private String middleDav;
    private String sposob;
    private String material;
    private String naznach;
    private String AGRS;
    private String master;
    private String date="";
    private String numKv="";
    private String type="";
    private boolean fixed=false;
    private String findName="";
    
    public ListBean(String name, String catGP_NM, String diametr, String dlina, String sposob_NM, String date) {
        this.name=name;
        this.catGP=Util.davlToNum(catGP_NM);
        this.diametr=diametr;
        this.dlina=dlina;
        this.sposob=Util.oldSposobToBaseSposob(sposob_NM);
        this.date=date;
    }

    public ListBean(String num, String catGP, String name, String diametr, String dlina, String middleDav, String sposob, String material, String naznach, String AGRS, String master) {
        this.findName = Util.clearName(name);
        this.num = num;
        this.catGP = catGP;
        this.name = name.replace(";", "|");
        this.diametr = diametr;
        this.dlina = dlina;
        this.middleDav = middleDav;
        this.sposob = sposob;
        this.material = material;
        this.naznach = naznach;
        this.AGRS = AGRS;
        this.master = master;
    }

    public String getFindName() {
        return findName;
    }

    public void setFindName(String findName) {
        this.findName = findName;
    }
    

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAGRS() {
        return AGRS;
    }

    public void setAGRS(String AGRS) {
        this.AGRS = AGRS;
    }

    public String getCatGP() {
        return catGP;
    }

    public void setCatGP(String catGP) {
        this.catGP = catGP;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMiddleDav() {
        return middleDav;
    }

    public void setMiddleDav(String middleDav) {
        this.middleDav = middleDav;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNaznach() {
        return naznach;
    }

    public void setNaznach(String naznach) {
        this.naznach = naznach;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNumKv() {
        return numKv;
    }

    public void setNumKv(String numKv) {
        this.numKv = numKv;
    }

    public String getSposob() {
        return sposob;
    }

    public void setSposob(String sposob) {
        this.sposob = sposob;
    }

    @Override
    public int compareTo(ListBean o) {
        return this.getName().compareTo(o.getName());
    }
    
    @Override
    public String toCSVLine(String S) {
        //return getNum()+";"+getCatGP()+";"+getName()+";"+getDiametr()+";"+getDlina()+";"+getMiddleDav()+";"+getSposob()+";"+getMaterial()+";"+getNaznach()+";"+getAGRS()+";"+getMaster()+";"+getDate()+";"+getNumKv()+";";
        return getNum()+S+getCatGP()+S+getName()+S+getDiametr()+S+getDlina()+S+getMiddleDav()+S+getSposob()+S+getMaterial()+S+getNaznach()+S+getAGRS()+S+getMaster()+S+getDate()+S+getFindName()+S+getNumKv()+S;
    }

    @Override
    public boolean isPrint() {
        return true;
    }

}