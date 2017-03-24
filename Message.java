/**
 * Created by PCUser on 2017/03/23.
 */
public class Message {
    private int messageID;
    private String message;
    private byte[] tst;
    private int number;

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getMessageID() {
        return messageID;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setTst(byte[] tst) {
        this.tst = tst;
    }

    public byte[] getTst() {
        return tst;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
