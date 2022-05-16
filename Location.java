
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Location {
    static int numDeRetour = 0;
    private Date dateDebut;
    private Date dateFin;
    int scootId;
    private int numR;

    // getter
    Date getDate(boolean x) {
        if (x) {
            return dateDebut;
        }
        return dateFin;
    }

    int getNum() {
        return numR;
    }

    // setter
    void setDatedeb(Date x) {
        dateDebut = x;
    }

    void setDatefin(Date x) {
        dateFin = x;
    }

    void setNum(int x) {
        numR = x;
    }

    Location() {

    }

    Location(Date dateDeb, Date dateEnd, int s) {
        dateDebut = dateDeb;
        dateFin = dateEnd;
        scootId = s;
        numDeRetour++;
        numR = numDeRetour;
        System.out.println(numR + "  " + s);
    }

    // prend une date de format jour/mois/année
    static Date stringToDate(String date) {
        if (date.trim().equals("")) {
            // date = ""-> null
            return null;
        } else {
            SimpleDateFormat dateFrt = new SimpleDateFormat("dd/MM/yyyy");
            // pas de clémence enleve les approximations
            dateFrt.setLenient(false);
            try {
                Date d = dateFrt.parse(date);
                return d;
            } catch (ParseException erreur) {
                // mauvaise date -> null
                return null;
            }
        }
    }

    static String dateToString(Date d) {
        SimpleDateFormat dateFrt = new SimpleDateFormat("dd/MM/yyyy");
        return dateFrt.format(d);
    }

    /*
     * test si une date est dans un autre intervalle de date.
     * Si elle y est retourne faux sinon vrai.
     */
    boolean dateInter(Date dateDeb, Date dateF) {
        if (dateF.before(dateDebut)) {
            return true;
        } else if (dateDeb.after(dateFin)) {
            return true;
        } else {
            return false;
        }
    }

    boolean dateTest(Date dat) {
        if (dat.after(dateDebut) && dat.before(dateFin)) {
            return true;
        } else {
            return false;
        }
    }

    void setDateFin(Date f) {
        this.dateFin = f;
    }
}
