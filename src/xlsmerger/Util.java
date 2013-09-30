package xlsmerger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Divasoft, inc.
 * @author Main Developer
 */
public class Util {
    
    public static int curYear = Calendar.getInstance().get(Calendar.YEAR);
    public static HashMap<String,String> map = new HashMap<>();
    public static final String R_LENIN="лен";
    public static final String R_OCT="окт";
    public static final String R_ZGD="ж/д";
    public static final String R_PERV="перв";
    public static final String [] A_R = {R_LENIN,R_OCT,R_PERV,R_ZGD};
    public static final String [] FILES_R = {"R_LENIN","R_OCT","R_PERV","R_ZGD"};
    public static List<DistBean> districts = new ArrayList<>();
    public static Util instance = new Util();
    
    public Util() {
        districts.add(new DistBean(R_LENIN, Util.getFileContent("r/R_LENIN.txt")));
        districts.add(new DistBean(R_OCT, Util.getFileContent("r/R_OCT.txt")));
        districts.add(new DistBean(R_ZGD, Util.getFileContent("r/R_ZGD.txt")));
        districts.add(new DistBean(R_PERV, Util.getFileContent("r/R_PERV.txt")));
    }
    
    /**
     * Парсим XLS
     * @param xlsPath - Full/Alt path to *.xls File
     * @return Collection Table[rows/cols]
     */
    public static Vector getXLScontent(String xlsPath) {
        InputStream is = null;
        POIFSFileSystem fs = null;
        Vector<Vector> v_rows_all = new Vector<Vector>();
        int NumOfSh = 0;
        try {
            is = new FileInputStream(xlsPath);
            fs = new POIFSFileSystem(is);
            HSSFWorkbook workBook = new HSSFWorkbook(fs);
            
            NumOfSh = workBook.getNumberOfSheets();
            for (int ir = 0; ir < NumOfSh; ir++) {
                Vector<Vector> v_rows = new Vector<Vector>();
                HSSFSheet sheet = workBook.getSheetAt(ir);
                Iterator<Row> rows = sheet.rowIterator();
                while (rows.hasNext()) {
                    Row row = rows.next();
                    Vector<String> v_cells = new Vector<String>();
                    Iterator<Cell> cells = row.cellIterator();
                    int cel_cnt=0;
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        cel_cnt++;
                        switch (cell.getCellType()) {
                            //case HSSFCell.CELL_TYPE_BLANK: break;
                            case HSSFCell.CELL_TYPE_FORMULA:
                                v_cells.add((cell.getCellFormula().replaceAll("\n", " ").replaceAll("\r", " ")).trim());
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    DateFormat dtf = new SimpleDateFormat("dd.MM.YYYY"); //dd-MM-YYYY"
                                    Date tt = new Date(cell.getDateCellValue().getTime());
                                    String datestr = dtf.format(tt);
                                    v_cells.add(datestr);
                                } else {
                                    if (cel_cnt==0 || cel_cnt==1 || cel_cnt==2) {
                                        DecimalFormat myFormatter = new DecimalFormat("");
                                        myFormatter.setGroupingSize(999);
                                        v_cells.add(myFormatter.format((cell.getNumericCellValue())));
                                    } else v_cells.add((Double.toString(cell.getNumericCellValue()).replaceAll("\n", " ").replaceAll("\r", " ")).trim());
                                }
                                break;
                            case HSSFCell.CELL_TYPE_STRING:
                                v_cells.add((cell.getStringCellValue().replaceAll("\n", " ").replaceAll("\r", " ").replaceAll("null", "n/a")).trim());
                                break;
                            default:
                                v_cells.add("n/a");
                                break;
                        }
                    }
                    cel_cnt=0;
                    v_rows.add(v_cells);
                }
                v_rows_all.add(v_rows);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Пересохраните эксель файл (в 2003), после выгрузки он немного битый\n"+ex.getMessage());
        }
        System.out.println(xlsPath);
        return v_rows_all;
    }

    /**
     * Вывод в файл
     * @param base
     * @param fname 
     */
    public static void createCSV_AB(List<ArchBean> base, String fname) {
        List<IPrint> baset = new ArrayList<>();
        baset.addAll(base);
        createCSV(baset, fname, "\t", true);
    }
    
    /**
     * Вывод в файл
     * @param base
     * @param fname
     * @param sep
     * @param num 
     */
    public static void createCSV(List<IPrint> base, String fname, String sep, boolean num) {
        try {
            PrintStream ps = new PrintStream(fname);
            int i = 0;
            for (Iterator<IPrint> it = base.iterator(); it.hasNext();) {
                IPrint iPrint = it.next();
                if (iPrint.isPrint()) {
                    ps.print(((num) ? ++i + sep : "") + iPrint.toCSVLine(sep) + "\n");
                }
            }
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Нормализация давления
     * @param name
     * @return 
     */
    public static String normalizeDavleniye(String name) {
        if (name.toLowerCase().equals("низкое")) {
            return "0,005";
        } else if (name.toLowerCase().equals("среднее")) {
            return "0,3";
        } else if (name.toLowerCase().equals("высокое")) {
            return "0,6";
        } else {
            return name;
        }
    }
    
    /**
     * Нормализация цифр давления
     * @param name
     * @return 
     */
    public static String normalizeDigitDavleniye(String name) {
        if (name.equals("0,003")) {
            return "0,005";
        } else if (name.equals("0,28")) {
            return "0,3";
        } else if (name.equals("0,5")) {
            return "0,6";
        } else {
            return name;
        }
    }
    
    /**
     *  Сопоставление давления
     * @param name
     * @return 
     */
    public static String davlToNum(String name) {
        if (name.equals("Низкое")) {
            return "4";
        } else if (name.startsWith("Среднее")) {
            return "3";
        } else if (name.equals("Высокое 2 категории")) {
            return "2";
        } else if (name.equals("Высокое 1 категории")) {
            return "1";
        } else {
            return "0";
        }
    }
    
    /**
     * Сопоставление способов прокладки газопровода
     * @param name
     * @return 
     */
    public static String oldSposobToBaseSposob(String name) {
        if (name.equals("Надземная")) {
            return "Надземный";
        } else if (name.equals("Подземная")) {
            return "Подземный";
        } else if (name.equals("Наземная")) {
            return "Надземный";
        } else if (name.equals("Подводная")) {
            return "Подводный";
        } else {
            return "0";
        }
    }
    
    /**
     * Сопоставление способов прокладки газопровода
     * @param name
     * @return 
     */
    public static String archSposobToBaseSposob(String name) {
        if (name.toLowerCase().equals("надземная")) {
            return "надземный";
        } else if (name.toLowerCase().equals("подземная")) {
            return "подземный";
        } else if (name.toLowerCase().equals("наземная")) {
            return "падземный";
        } else if (name.toLowerCase().equals("подводная")) {
            return "подводный";
        } else {
            return " ";
        }
    }
    
    /**
     * Очистка города
     * @param name
     * @return 
     */
    public static String clearName(String name) {
        String ret = name.split(";")[0].trim();
        ret = ret.replaceAll("г\\. ПЕНЗА микр-он", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА просп ", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА а/д", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА с ", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА с\\.", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА п ", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА п\\.", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА ул ", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА ул\\.", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА пос ", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА пос\\.", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА мкр ", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА мкр\\.", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА пер ", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА пер\\.", "").trim();
        ret = ret.replaceAll("г\\. ПЕНЗА ", "").trim();
        ret = ret.replaceAll("Жилой дом", "").trim();
        ret = ret.replaceAll("Жилые дома", "").trim();
        ret = ret.split(" ")[0].trim();
        return ret;
    }

    /**
     * Очистка адреса
     * @param name
     * @return 
     */
    public static List<String> clearExName(String name) {
        List<String> streets = new ArrayList<>();
        String ret = name.toLowerCase().trim();
        //ret = ret.replaceAll("пр ", "").trim();
        //ret = ret.replaceAll("просп ", "").trim();
        /*ret = ret.replaceAll("а/д", "").trim();
        ret = ret.replaceAll("с ", "").trim();
        ret = ret.replaceAll("с\\.", "").trim();
        ret = ret.replaceAll("п ", "").trim();
        ret = ret.replaceAll("п\\.", "").trim();
        ret = ret.replaceAll("пер ", "").trim();*/
        //ret = ret.replaceAll("ул\\.", "").trim();
        /*ret = ret.replaceAll("пос ", "").trim();
        ret = ret.replaceAll("пос\\.", "").trim();
        ret = ret.replaceAll("мкр ", "").trim();
        ret = ret.replaceAll("мкр\\.", "").trim();
        ret = ret.replaceAll("пер ", "").trim();
        ret = ret.replaceAll("пер\\.", "").trim();*/
        String[] ret_ss = ret.split(";");
        for (String string : ret_ss) {
            String[] ret1 = string.split("ул |ул\\.|\n");
            for (String string1 : ret1) {
                String[] ret2 = string1.split("пр |пр\\.|проспект |просп |с |с\\.|п |п\\.|пер |пос |пос\\.|мкр |пер |пер\\.|р-н |мкн\\.|м-н");
                for (String string2 : ret2) {
                    String[] ret3 = string2.split(",");
                    for (String string3 : ret3) {

                        String [] ret4 = string3.split("   ");
                        /*
                         * if (findBal==488) "(1-й пр. Жданова) пр Придорожный
                         * 1-й;"
                            System.out.println("!");
                         */
                        for (String string4 : ret4) {
                        if (string4.isEmpty()) {
                            continue;
                        }
                        //String[] ret_s = string4.split("кв");
                        //if (ret_s.length==0) continue;
                        //String rets=ret_s[0].trim();
                        //ret=ret.replace("литер", "").replace("почт", "").replace("  ", "").trim();
                        String to_add = string4.replace("  ", "").replace(".", "").replace("/", "").replace(")", "").replace("(", "").replace("очередь", "").replace("2-й", "").replace("1-й", "").trim();
                        if (to_add.isEmpty()) {
                            continue;
                        }
                        //if (!checkIfNumber(to_add)) {
                            streets.add(to_add);
                        }
                        //}

                    }
                }
            }
        }

        //ret = ret.split(" ")[0].trim();
        return streets;
    }

    /**
     * Проверка на Integer
     * @param in
     * @return 
     */
    public static boolean checkIfNumber(String in) {
        try {
            Integer.parseInt(in);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
    
    /**
     * Поиск "чистых" улиц
     * @param street
     * @param street_in
     * @return 
     */
    public static String findClearedStreet(List<String> street, String street_in) {
        street_in=street_in.toLowerCase();
        for (Iterator<String> it = street.iterator(); it.hasNext();) {
            String string = it.next();
            if (street_in.contains(string)) {
                return string;
            }
        }
        return "";
    }
    
    /**
     * Очистка названий домов
     * @param home
     * @param home_in
     * @return 
     */
    public static boolean clearExHome(String home, String home_in) {
        List<String> homes = new ArrayList<>();
        if (home.trim().isEmpty()) {
            return true;
        }

        home = home.toLowerCase().replaceAll("стр", "").trim();
        String[] ret0 = home.split(";");
        for (String string : ret0) {
            String[] ret1 = string.split("\\.");
            for (String string1 : ret1) {
                String[] ret2 = string1.split("\\)");
                for (String string2 : ret2) {
                    String[] ret3 = string2.split("\\(");
                    for (String string3 : ret3) {

                        String[] ret4 = string3.split("-");
                        /*
                         * if (findBal==488) 
                            System.out.println("!");
                         */
                        for (String string4 : ret4) {
                            if (string4.isEmpty()) {
                                continue;
                            }
                            String[] ret_s = string4.split("кв");
                            if (ret_s.length == 0) {
                                continue;
                            }
                            String ret = ret_s[0].trim();
                            ret = ret.replace("литер", "").replace("почт", "").replace("стр", "").replace(" ", "").trim();
                            homes.add(ret);
                        }

                    }
                }
            }
        }
        home_in=home_in.toLowerCase();
        for (Iterator<String> it = homes.iterator(); it.hasNext();) {
            String string = it.next();
            if (home_in.contains(string)) {
                return true;
            }
        }

        return false;
    }
    
    /**
     * Заполнение строк
     * @param str
     * @return 
     */
    public static String clrNA(String str) {
        return (str.equals("n/a")) ? " " : str.trim();
    }
    
    /**
     * Очистка района
     * @param str
     * @return 
     */
    public static String clearDistrict(String str) {
        String[] dits = str.split(";");
        HashSet<String> hs = new HashSet();
        hs.addAll(Arrays.asList(dits));
        
        String ret="";
        for (String e : hs) {
            if (e.trim().isEmpty()) continue;
            ret+=e+";";
        }
        if (ret.isEmpty()) return "";
        return ret.substring(0, ret.length()-1);
    }
    
    /**
     * Поиск района
     * @param street
     * @return 
     */
    public static String findDistrict(String street) {
        List<String> names = new ArrayList<>(clearExName(street));
        String ret="";
        if (names.isEmpty()) return ret;
        
        String clrStr = findClearedStreet(names, street);
        if (!clrStr.isEmpty() && clrStr.length()>3) {
            for (DistBean dist : districts) {
                if (dist.getContent().contains(clrStr)) {
                    ret+=dist.getName()+";";
                }
            }
            
        }
        
        return clearDistrict(ret);
    }
    
    public static int findNoMasterC = 0;
    public static List<NoMasterBean> noMasterBase = new ArrayList<>();
    /**
     * Поиск безхозяйных
     * @param bean
     * @return
     * @deprecated
     */
    @Deprecated
    public static String findNoMaster(ArchBean bean) {
        List<String> name = new ArrayList<>(clearExName(bean.getStreet()));
        for (Iterator<NoMasterBean> it = noMasterBase.iterator(); it.hasNext();) {
            NoMasterBean noMasterBean = it.next();
            //if (!balansBean.isChecked()) continue;
            if (name.isEmpty()) {
                break; // || bean.getHome().trim().isEmpty()
            }
            String clrStr = findClearedStreet(name, noMasterBean.getAddress());
            if (checkIfNumber(clrStr)) continue;
            /*if (balansBean.getAddress().contains("Ул. Красная (от ул. Чкалова до ул. Свердлова)") && bean.getStreet().contains("Красная") && bean.getHome().trim().isEmpty() && bean.getDavleniye().equals("0,3"))
                System.out.println("test!");*/
            if (!clrStr.isEmpty() && clrStr.length()>3 && clearExHome(bean.getHome(), noMasterBean.getAddress())) {
                if (checkDlina(noMasterBean.getDlina(),bean.getDALL().replace("-", ""))) {
                        if (noMasterBean.getDavleniye().equals(bean.getDavleniye())) //if (balansBean.getSposob().equals(bean.getSposob())) 
                        {
                            noMasterBean.setChecked(false);
                            String out="";
                            if (!noMasterBean.getDiametr().equals(bean.getDiametr(noMasterBean.getDiametr()))) {out+="?"; System.out.print("DV! ");}
                            System.out.println("NO:"+findNoMasterC++ + "[" + bean.getStreet() + ":" + bean.getHome() + "] (=" + clrStr + "=) [" + noMasterBean.getAddress() + "]");
                            return out+="Объекты бесхозяйные";
                        }
                    
                }
            }
        }
        return "" + bean.getHostMaster();
    }
    
    public static int findComm = 0;
    public static List<CommBean> commBase = new ArrayList<>();
    /**
     * Поиск ИНН
     * @param bean
     * @return
     * @deprecated
     */
    @Deprecated
    public static String findINN(ArchBean bean) {
        List<String> name = new ArrayList<>(clearExName(bean.getStreet()));
        for (Iterator<CommBean> it = commBase.iterator(); it.hasNext();) {
            CommBean commBean = it.next();
            //if (!balansBean.isChecked()) continue;
            if (name.isEmpty()) {
                break; // || bean.getHome().trim().isEmpty()
            }
            String clrStr = findClearedStreet(name, commBean.getName());
            if (checkIfNumber(clrStr)) continue;
            /*if (balansBean.getAddress().contains("Ул. Красная (от ул. Чкалова до ул. Свердлова)") && bean.getStreet().contains("Красная") && bean.getHome().trim().isEmpty() && bean.getDavleniye().equals("0,3"))
                System.out.println("test!");*/
            if (!clrStr.isEmpty() && clrStr.length()>3 && clearExHome(bean.getHome(), commBean.getName())) {
                if (checkDlina(commBean.getDlina(),bean.getDALL().replace("-", ""))) {
                        if (commBean.getDavleniye().equals(bean.getDavleniye())) //if (balansBean.getSposob().equals(bean.getSposob())) 
                        {
                            commBean.setChecked(false);
                            String out="";
                            if (!commBean.getDiametr().equals(bean.getDiametr(commBean.getDiametr()))) {out+="?"; System.out.print("DV! ");}
                            System.out.println("IN:"+findComm++ + "[" + bean.getStreet() + ":" + bean.getHome() + "] (=" + clrStr + "=) [" + commBean.getName() + "]");
                            return out+=commBean.getINN().trim();
                        }
                    
                }
            }
        }
        return "?";
    }
    
    
    public static int findBal = 0;
    public static List<BalansBean> balansBase = new ArrayList<>();
    /**
     * Поиск балансового номера
     * @param bean
     * @return 
     */
    @Deprecated
    public static String findBalans(ArchBean bean) {
        List<String> name = new ArrayList<>(clearExName(bean.getStreet()));
        for (Iterator<BalansBean> it = balansBase.iterator(); it.hasNext();) {
            BalansBean balansBean = it.next();
            //if (!balansBean.isChecked()) continue;
            if (name.isEmpty()) {
                break; // || bean.getHome().trim().isEmpty()
            }
            String clrStr = findClearedStreet(name, balansBean.getAddress());
            if (checkIfNumber(clrStr)) continue;
            /*if (balansBean.getAddress().contains("Ул. Красная (от ул. Чкалова до ул. Свердлова)") && bean.getStreet().contains("Красная") && bean.getHome().trim().isEmpty() && bean.getDavleniye().equals("0,3"))
                System.out.println("test!");*/
            if (!clrStr.isEmpty() && clrStr.length()>3 && clearExHome(bean.getHome(), balansBean.getAddress())) {
                if (checkDlina(balansBean.getDlina(),bean.getDALL().replace("-", ""))) {
                        if (balansBean.getDavleniye().equals(bean.getDavleniye())) //if (balansBean.getSposob().equals(bean.getSposob())) 
                        {
                            balansBean.setChecked(false);
                            String out="";
                            if (!balansBean.getDiametr().equals(bean.getDiametr(balansBean.getDiametr()))) {out+="?"; System.out.print("DV! ");}
                            System.out.println("BL:"+findBal++ + "[" + bean.getStreet() + ":" + bean.getHome() + "] (=" + clrStr + "=) [" + balansBean.getAddress() + "]");
                            return out+="Объекты на балансе";
                        }
                    
                }
            }
        }
        return "!" + bean.getHostMaster();
        //return "Объекты на балансе";
    }
    
    /**
     * Сравнить длины
     * @param dl0
     * @param dl1
     * @return 
     */
    public static boolean checkDlina(String dl0, String dl1) {
        if (dl0.trim().isEmpty() || dl1.trim().isEmpty()) return false;
        try {
            Double d0= Double.parseDouble(dl0.replace(",", "."));
            Double d1= Double.parseDouble(dl1.replace(",", "."));
            return d0.equals(d1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    /**
     * Заменить . на ,
     * @param string
     * @return 
     */
    public static String dotTo(String string) {
        return string.replace(".", ",");
    }

    /**
     * Сравнить даты
     * @param date0
     * @param date1
     * @return 
     */
    public static int compareDate(String date0, String date1) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date dt0 = sdf.parse(date0);
            Date dt1 = sdf.parse(date1);
            return dt0.compareTo(dt1);
        } catch (Exception ex) {
            ex.printStackTrace();
            return 1;
        }
    }
    
    /**
     * Очистка SInteger
     * @param str
     * @return 
     */
    public static String toPInt(String str) {
        if (str.equals("0.0")) return " ";
        else return str.replace(".0", "");
    }
    
    /**
    * Загрузить записи из файла
    */
    public static String getFileContent(String fName) {
        File f = new File(fName);
        String ret="";
        if (!f.exists()) {
            return ret;
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String l;
            while ((l = br.readLine()) != null) {
                ret+=l;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }
    
    /**
     * Обновляем элементы базы
     * @param base 
     */
    public static void postUpdate(List<ArchBean> base) {
        for (Iterator<ArchBean> it = base.iterator(); it.hasNext();) {
            ArchBean archBean = it.next();
            if (archBean.getRayon().isEmpty() && !archBean.getStreet().isEmpty()) {
                archBean.setRayon(Util.findDistrict(archBean.getStreet()));
            } else {
                archBean.setRayon((Util.clearDistrict(archBean.getRayon())));
            }
            
            if (archBean.getIznos().isEmpty()) {
                archBean.setIznos(archBean.calcIznos());
            }
        }
    }
    
    /**
     * Фильтр элементов по дате
     * @param archBase
     * @param dateFrom
     * @param dateTo
     * @return 
     */
    public static List<ArchBean> getBaseFromToDate(List<ArchBean> archBase, String dateFrom, String dateTo) {
        List<ArchBean> ret = new ArrayList<>();
        for (Iterator<ArchBean> it = archBase.iterator(); it.hasNext();) {
            ArchBean archBean = it.next();
            if (Util.compareDate(archBean.getDateMod(), dateFrom)>=0 && Util.compareDate(archBean.getDateMod(), dateTo)<=0) { // 
                ret.add(archBean);
            }
        }
        return ret;
    }
    /**
     * Заполняем коллекцию базы
     * @param v
     * @return 
     */
    public static List<ArchBean> parseArchBase(Vector v) {
        List<ArchBean> beans = new ArrayList<>();
        beans.clear();

        Vector firstList = (Vector) v.get(0);
        for (int i = 1; i < firstList.size(); i++) {
            Vector<String> elem = (Vector) firstList.get(i);
            
            for (int j = 0; j < elem.size(); j++) {
                elem.setElementAt(Util.clrNA(elem.get(j)), j);
                
            }
            // Я не помню зачем это надо было
            // if (map.containsKey(elem.get(0)+"|"+elem.get(1))) continue;
            
            if (elem.size() > 39) {
                    // Заполняем дату из предыдущего элемента, если пустая.
                    String date=((elem.get(11).equals("29.12.1899")||elem.get(11).trim().isEmpty()))?beans.get(beans.size()-1).getDate():elem.get(11);
                    beans.add(new ArchBean( elem.get(0), elem.get(1), elem.get(3),
                            elem.get(4), elem.get(5), elem.get(6), elem.get(7), elem.get(8), elem.get(9), elem.get(11)/*date*/, elem.get(12),
                            elem.get(13), elem.get(14), elem.get(15), elem.get(16), elem.get(17), elem.get(18), elem.get(19), elem.get(20), elem.get(21), elem.get(22),
                            elem.get(23), elem.get(24), elem.get(25), elem.get(26), elem.get(27), elem.get(28), elem.get(29), elem.get(30), elem.get(31), elem.get(32),
                            elem.get(33), elem.get(34), elem.get(35), elem.get(36), elem.get(37), elem.get(38), "n"));
            }
        }
        Collections.sort(beans);
        //Util.createCSV_AB(archBase, "arch_out.txt");
        return beans;
    }
}
