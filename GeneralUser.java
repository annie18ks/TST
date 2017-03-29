public class GeneralUser {
    private int Number;
    private String accountID;
    private String accountPW;
    private byte[] publicKey;
    private byte[] privateKey;

    public void setNumber(int Number) {
        this.Number = Number;
    }

    public int getNumber() {
        return Number;
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

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }
}
