/**
 * Created by PCUser on 2017/03/24.
 */
public class GeneralUser {
    private int geNumber;
    private String accountID;
    private String accountPW;
    private byte[] publickKey;
    private byte[] privateKey;

    public void setGeNumber(int geNumber) {
        this.geNumber = geNumber;
    }

    public int getGeNumber() {
        return geNumber;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountPW(String accountPW) {
        this.accountPW = accountPW;
    }

    public String getAccountPW() {
        return accountPW;
    }

    public void setPublickKey(byte[] publickKey) {
        this.publickKey = publickKey;
    }

    public byte[] getPublickKey() {
        return publickKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }
}
