/**
 * Speicher für die Informationen einer Mail. Dies umfasst den Textkörper und
 * einige der Header-Daten, sowie die Nummer, die der Mail auf dem Server
 * zugeordnet ist.
 */
public class Mail {

    /**
     * Laufende Nummer auf dem Server.
     */
    private int number;

    /**
     * Empfangsdatum der Mail.
     * <p>
     * Wird aus dem Header-Feld "Date:" gelesen.
     */
    private String date;

    /**
     * Sender der Mail.
     * <p>
     * Wird aus dem Header-Feld "From:" gelesen.
     */
    private String sender;

    /**
     * Betreff der Mail.
     * <p>
     * Wird aus dem Header-Feld "Subject:" gelesen.
     */
    private String subject;

    /**
     * Textkörper der Mail ohen Header-Felder.
     */
    private String text;

    /**
     * Erstellt ein neues Mail-Objekt mit allen nötigen Werten.
     *
     * @param pNumber
     * @param pDate
     * @param pSender
     * @param pSubject
     * @param pText
     */
    public Mail( int pNumber, String pDate, String pSender, String pSubject, String pText ) {
        this.number = pNumber;
        this.date = pDate;
        this.sender = pSender;
        this.subject = pSubject;
        this.text = pText;
    }

    /**
     * Gibt die laufende Nummer der Mail, die diese beim Laden auf dem
     * POP3-Server hatte, zurück.
     * <p>
     * Die laufende Nummer kann sich seit dem Laden verändert haben, falls noch
     * andere Mail-Clients zugriff auf den Server haben und in der Zwischenzeit
     * Mails gelöscht haben.
     *
     * @return Laufende Nummer der Mail beginnend ab {@code 1}.
     */
    public int getNumber() {
        return number;
    }

    /**
     * Datum der Mail als String.
     * <p>
     * In zukünftigen Versionen könnte das Datum als {@link
     * java.util.Date}-Objekt gespeichert werden, um mit {@link
     * java.text.DateFormat} das Datumsformat anpassen zu können.
     *
     * @return Emfangsdatum der Mail aus dem "Date:" Header
     */
    public String getDate() {
        return date;
    }

    /**
     * Absender der Mail.
     * <p>
     * Die Absenderdaten können in unterschiedlichen Formaten vorliegen (siehe
     * dazu die Spezifikation in RFC2822 "Internet Message Format", Section
     * 3.4). Die Absender Information könnte in zukünftigen Versionen in Name
     * und Mail-Adresse geparsed werden.
     *
     * @return Absender der Mail aus dem "From:" Header
     * @url https://datatracker.ietf.org/doc/html/rfc2822#section-3.4
     */
    public String getSender() {
        return sender;
    }

    /**
     * Der Betreff der Mail.
     *
     * @return Betreff der Mail aus dem "Subject:" Header
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Der Textkörper der Mail.
     *
     * @return Textkörper der Mail
     */
    public String getText() {
        return text;
    }

}
