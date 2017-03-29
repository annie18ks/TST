/**
 * Created by PCUser on 2017/03/29.
 */
public class GeneralManager {
    public boolean makeGeneralUser(String accountID, String accountPW, byte[] privateKey, byte[] publicKey
    ) throws Exception{
        GeneralUser Guser = new GeneralUser();
        GeneralDAO GeneralDAO = new GeneralDAO();
        Guser.setAccountID(accountID);
        Guser.setAccountPW(accountPW);
        Guser.setPrivateKey(privateKey);
        Guser.setPublicKey(publicKey);
        //msg.setNumber(number);//メッセージナンバーの引っ張り方わからず保留
        return GeneralDAO.makeGeneralUser(Guser);

    }

    public boolean updateGeneralUser(String accountID, String accountPW,int number
    ) throws Exception{
        GeneralUser Guser = new GeneralUser();
        GeneralDAO GeneralDAO = new GeneralDAO();
        Guser.setAccountID(accountID);
        Guser.setAccountPW(accountPW);
        Guser.setNumber(number);
        //msg.setNumber(number);//メッセージナンバーの引っ張り方わからず保留
        return GeneralDAO.updateGeneralUser(Guser);

    }
    public void deleteAccount (String accountID) throws Exception{
        GeneralDAO GeneralDAO = new GeneralDAO();
        GeneralDAO.deleteGeneralUser(accountID);
    }

    public GeneralUser getAccount (String accountID) throws Exception{
        GeneralDAO GeneralDAO = new GeneralDAO();
        return GeneralDAO.returnAccount(accountID);
    }
}
