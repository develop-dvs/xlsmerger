package xlsmerger;
/**
 * Divasoft, inc.
 * @author Main Developer
 */
public class ArchBean implements Comparable<ArchBean>, IPrint {
    private String id_gas;
    private String id_pipe;
    private String master;
    private String dateMod;
    private String city;
    private String street;
    private String home;
    private String rayon;
    private String hostMaster;
    private String date;
    private String object;
    private String invNum;
    private String davleniye;
    private String sposob;
    private String material;
    private String d32;
    private String d40;
    private String d57;
    private String d76;
    private String d89;
    private String d106;
    private String d114;
    private String d133;
    private String d159;
    private String d219;
    private String d273;
    private String d325;
    private String d426;
    private String d530;
    private String d630;
    private String katod;
    private String proektor;
    private String drenazg;
    private String izolSosed;
    private String otklUstrKolod;
    private String otklUstrKovere;
    private String otklUstrNadZeml;
    private String inn="";
    private String type;
    
    private String iznos="";

    public ArchBean(String id_gas, String id_pipe, String city, String rayon, String street, String home,  String hostMaster, String date, String object, String invNum, String davleniye, String sposob, String material, String d32, String d40, String d57, String d76, String d89, String d106, String d114, String d133, String d159, String d219, String d273, String d325, String d426, String d530, String d630, String katod, String proektor, String drenazg, String izolSosed, String otklUstrKolod, String otklUstrKovere, String otklUstrNadZeml, String master) {
        this.city = city;
        this.rayon = rayon.trim().replace(" ", "").replace(",", ";");
        this.street = street.trim();
        this.home = home;
        this.hostMaster = hostMaster;
        this.date = date;
        this.object = object;
        this.invNum = invNum;
        this.davleniye = davleniye;
        this.sposob = Util.archSposobToBaseSposob(sposob);
        this.material = material.toLowerCase();
        this.d32 = Util.dotTo(d32);
        this.d40 = Util.dotTo(d40);
        this.d57 = Util.dotTo(d57);
        this.d76 = Util.dotTo(d76);
        this.d89 = Util.dotTo(d89);
        this.d106 = Util.dotTo(d106);
        this.d114 = Util.dotTo(d114);
        this.d133 = Util.dotTo(d133);
        this.d159 = Util.dotTo(d159);
        this.d219 = Util.dotTo(d219);
        this.d273 = Util.dotTo(d273);
        this.d325 = Util.dotTo(d325);
        this.d426 = Util.dotTo(d426);
        this.d530 = Util.dotTo(d530);
        this.d630 = Util.dotTo(d630);
        this.katod = katod;
        this.proektor = proektor;
        this.drenazg = drenazg;
        this.izolSosed = Util.toPInt(izolSosed);
        this.otklUstrKolod = Util.toPInt(otklUstrKolod);
        this.otklUstrKovere = Util.toPInt(otklUstrKovere);
        this.otklUstrNadZeml = Util.toPInt(otklUstrNadZeml);
        this.id_gas=id_gas;
        this.id_pipe=id_pipe;
        this.master=master.trim().replaceAll("\t", "");
    }

    public ArchBean(String id_gas, String id_pipe, String master, String dateMod, String city, String rayon, String street, String home,String hostMaster, String date, String object, String invNum, String davleniye, String sposob, String material, String d32, String d40, String d57, String d76, String d89, String d106, String d114, String d133, String d159, String d219, String d273, String d325, String d426, String d530, String d630, String katod, String proektor, String drenazg, String izolSosed, String otklUstrKolod, String otklUstrKovere, String otklUstrNadZeml, String type) {
        this(id_gas, id_pipe, city, rayon, street, home, hostMaster, date, object, invNum, davleniye, sposob, material, d32, d40, d57, d76, d89, d106, d114, d133, d159, d219, d273, d325, d426, d530, d630, katod, proektor, drenazg, izolSosed, otklUstrKolod, otklUstrKovere, otklUstrNadZeml, master);
        this.dateMod = dateMod;
        this.type=type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }
    
    public String[] getDiams() {
        return diams;
    }

    public void setDiams(String[] diams) {
        this.diams = diams;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getDateMod() {
        return dateMod;
    }

    public void setDateMod(String dateMod) {
        this.dateMod = dateMod;
    }

    public String getD159() {
        return d159;
    }

    public void setD159(String d159) {
        this.d159 = d159;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getD106() {
        return d106;
    }

    public void setD106(String d106) {
        this.d106 = d106;
    }

    public String getD114() {
        return d114;
    }

    public void setD114(String d114) {
        this.d114 = d114;
    }

    public String getD133() {
        return d133;
    }

    public void setD133(String d133) {
        this.d133 = d133;
    }

    public String getD219() {
        return d219;
    }

    public void setD219(String d219) {
        this.d219 = d219;
    }

    public String getD273() {
        return d273;
    }

    public void setD273(String d273) {
        this.d273 = d273;
    }

    public String getD32() {
        return d32;
    }

    public void setD32(String d32) {
        this.d32 = d32;
    }

    public String getD325() {
        return d325;
    }

    public void setD325(String d325) {
        this.d325 = d325;
    }

    public String getD40() {
        return d40;
    }

    public void setD40(String d40) {
        this.d40 = d40;
    }

    public String getD426() {
        return d426;
    }

    public void setD426(String d426) {
        this.d426 = d426;
    }

    public String getD530() {
        return d530;
    }

    public void setD530(String d530) {
        this.d530 = d530;
    }

    public String getD57() {
        return d57;
    }

    public void setD57(String d57) {
        this.d57 = d57;
    }

    public String getD630() {
        return d630;
    }

    public void setD630(String d630) {
        this.d630 = d630;
    }

    public String getD76() {
        return d76;
    }

    public void setD76(String d76) {
        this.d76 = d76;
    }

    public String getD89() {
        return d89;
    }

    public void setD89(String d89) {
        this.d89 = d89;
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

    public String getDrenazg() {
        return drenazg;
    }

    public void setDrenazg(String drenazg) {
        this.drenazg = drenazg;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getHostMaster() {
        return hostMaster;
    }

    public void setHostMaster(String hostMaster) {
        this.hostMaster = hostMaster;
    }

    public String getInvNum() {
        return invNum;
    }

    public void setInvNum(String invNum) {
        this.invNum = invNum;
    }

    public String getIzolSosed() {
        return izolSosed;
    }

    public void setIzolSosed(String izolSosed) {
        this.izolSosed = izolSosed;
    }

    public String getKatod() {
        return katod;
    }

    public void setKatod(String katod) {
        this.katod = katod;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getOtklUstrKolod() {
        return otklUstrKolod;
    }

    public void setOtklUstrKolod(String otklUstrKolod) {
        this.otklUstrKolod = otklUstrKolod;
    }

    public String getOtklUstrKovere() {
        return otklUstrKovere;
    }

    public void setOtklUstrKovere(String otklUstrKovere) {
        this.otklUstrKovere = otklUstrKovere;
    }

    public String getOtklUstrNadZeml() {
        return otklUstrNadZeml;
    }

    public void setOtklUstrNadZeml(String otklUstrNadZeml) {
        this.otklUstrNadZeml = otklUstrNadZeml;
    }

    public String getProektor() {
        return proektor;
    }

    public void setProektor(String proektor) {
        this.proektor = proektor;
    }

    public String getSposob() {
        return sposob;
    }

    public void setSposob(String sposob) {
        this.sposob = sposob;
    }

    public String getStreet() {
        if (street.trim().isEmpty()) return "";
        return (street.endsWith(";")?street:street+";");
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getId_gas() {
        return id_gas;
    }

    public void setId_gas(String id_gas) {
        this.id_gas = id_gas;
    }

    public String getId_pipe() {
        return id_pipe;
    }

    public void setId_pipe(String id_pipe) {
        this.id_pipe = id_pipe;
    }

    public String getRayon() {
        return rayon;
    }

    public void setRayon(String rayon) {
        this.rayon = rayon;
    }
    
    @Override
    public int compareTo(ArchBean o) {
        return this.getStreet().compareTo(o.getStreet());
    }

    @Override
    public String toCSVLine(String S) {
        return  getId_gas()+S+getId_pipe()+S+getType()+S+getDateMod()+S+getHostMasterTo()+S+getHostMasterDo()+S+"да"+S+getDate()+S+(getCity()+" "+getStreet()+" "+getHome().trim()+" ;"+getObject().trim())+S+
                getRayon()+S+getIznos()+S+
                (getInn().equals("?")?Util.findINN(this):"?")+S+getDavleniye()+S+
                getSposob()+S+getMaterial()+S+getD32()+S+getD40()+S+getD57()+S+getD76()+S+getD89()+S+getD106()+S+getD114()+S+
                getD133()+S+getD159()+S+getD219()+S+getD273()+S+getD325()+S+getD426()+S+getD530()+S+getD630()+S+getDALL()+S+
                " "+S+" "+S+" "+S+getKatod()+S+getProektor()+S+getDrenazg()+S+getIzolSosed()+S+getOtklUstrKolod()+S+getOtklUstrKovere()+S+
                getOtklUstrNadZeml()+S+" ";
        //return  getId_gas()+S+getId_pipe()+S+getType();
        //return getDate();
        //return city+"\t"+street+"\t"+home+"\t"+hostMaster+"\t"+date+"\t"+object+"\t"+invNum+"\t"+davleniye+"\t"+sposob+"\t"+material+"\t"+d32+"\t"+d40+"\t"+d57+"\t"+d76+"\t"+d89+"\t"+d106+"\t"+d114+"\t"+d133+"\t"+d159+"\t"+d219+"\t"+d273+"\t"+d325+"\t"+d426+"\t"+d530+"\t"+d630+"\t"+katod+"\t"+proektor+"\t"+drenazg+"\t"+izolSosed+"\t"+otklUstrKolod+"\t"+otklUstrKovere+"\t"+otklUstrNadZeml+"\t";
        //return city+";"+street+";"+home+";"+hostMaster+";"+date+";"+object+";"+invNum+";"+davleniye+";"+sposob+";"+material+";"+d32+";"+d40+";"+d57+";"+d76+";"+d89+";"+d106+";"+d114+";"+d133+";"+d159+";"+d219+";"+d273+";"+d325+";"+d426+";"+d530+";"+d630+";"+katod+";"+proektor+";"+drenazg+";"+izolSosed+";"+otklUstrKolod+";"+otklUstrKovere+";"+otklUstrNadZeml+";";
    }

    private String getHostMasterTo() {
        //return getHostMaster();
        String tmp_host = getHostMaster();
        if (tmp_host.equals("Бесхозяйный") || tmp_host.equals("На тех. обслуживании по договорам") || tmp_host.equals("Прочие") || tmp_host.equals("Частный сектор")) {setInn("?"); return getMaster();}
        else if (tmp_host.equals("В собственности") || tmp_host.equals("На балансе") || tmp_host.equals("На балансе Метана") ) return "ОАО \"Метан\"";
        else if (tmp_host.equals("аренда- ОАО\"Газпромрегионгаз\"")) return "ОАО \"Газпромрегионгаз\"";
        else if (tmp_host.equals("аренда- Мингосимущество")) return "Мингосимущество";
        else if (tmp_host.equals("аренда- КУМИ")) return "КУМИ";
        else if (tmp_host.equals("Арендуемый")) return "Прочие";
        return getMaster();
    }
    
    private String getHostMasterDo() {
        //return getHostMaster();
        String tmp_host = getHostMaster();
        if (tmp_host.equals("Арендуемый")) return "";
        else if (tmp_host.equals("Прочие") || tmp_host.equals("Частный сектор") || tmp_host.equals("На балансе") || tmp_host.equals("На балансе Метана")) { String rt=Util.findBalans(this); if (rt.startsWith("!")) {return Util.findNoMaster(this);} else return rt;}
        
        else if (tmp_host.equals("На тех. обслуживании по договорам")) return "Объекты  по договарам технической эксплуатации (в рамках единого оператора).";
        else if (tmp_host.equals("Бесхозяйный")) return Util.findNoMaster(this);
        else if (tmp_host.equals("В собственности")) return "Объекты на балансе";
        else if (tmp_host.equals("аренда- ОАО\"Газпромрегионгаз\"")) return "Объекты арендованные у ГПРГ";
        else if (tmp_host.equals("аренда- Мингосимущество")) return "Объекты арендованные у Субъект РФ";
        else if (tmp_host.equals("аренда- КУМИ")) return "Прочие";
        return "";
    }

    public String getDALL() {
        return (getD32()+getD40()+getD57()+getD76()+getD89()+getD106()+getD114()+
                getD133()+getD159()+getD219()+getD273()+getD325()+getD426()+getD530()+getD630()).trim();
    }
    
    private String[] diams = {"32","40","57","76","89","106","114","133","159","219","273","325","426","530","630"};
    public String getDiametr(String diam_in) {
        for (String string : diams) {
            if (isDiametr(string, diam_in)) return string+",0";
        }
        return diam_in;
    }
    
    public String getDavleniyeEq() {
        return davleniye;
    }
    
    
    private boolean isDiametr(String diam, String diam_in) {
        if (diam_in.trim().isEmpty()) return false;
        double diametr = Double.parseDouble(diam);
        double diametr_in = Double.parseDouble(diam_in.trim().replace(",", "."));
        if ( diametr>=(diametr_in-3) && diametr<=(diametr_in+3) ) return true;
        else return false;
    }

    @Override
    public boolean isPrint() {
        return true;
    }

    public String getIznos() {
        return iznos;
    }

    public void setIznos(String iznos) {
        this.iznos = iznos;
    }
    
    public String calcIznos() {
        if (date.length()>4) {
            String yearStr = date.substring(date.length()-4, date.length());
            int year = Integer.parseInt(yearStr);
            year = (Util.curYear-year>40)?40:Util.curYear-year;
            iznos = ((int)(((year*0.7)/40) * 100))+"";
        }
        return iznos;
    }
    
}