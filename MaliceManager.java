/**
 * Created by PCUser on 2017/03/29.
 */
public class MaliceManager {
    public boolean makeMaliceUser(String accountID, String accountPW
    ) throws Exception{
        MaliceUser Muser = new MaliceUser();
        MaliceDAO MaliceDAO = new MaliceDAO();
        Muser.setAccountID(accountID);
        Muser.setAccountPW(accountPW);
        //msg.setNumber(number);//メッセージナンバーの引っ張り方わからず保留
        return MaliceDAO.makeMaliceUser(Muser);

    }

    public boolean updateMaliceUser(String accountID, String accountPW,int number
    ) throws Exception{
        MaliceUser Muser = new MaliceUser();
        MaliceDAO MaliceDAO = new MaliceDAO();
        Muser.setAccountID(accountID);
        Muser.setAccountPW(accountPW);
        Muser.setNumber(number);
        //msg.setNumber(number);//メッセージナンバーの引っ張り方わからず保留
        return MaliceDAO.updateMaliceUser(Muser);

    }
    public void deleteAccount (String accountID) throws Exception{
        MaliceDAO MaliceDAO = new MaliceDAO();
        MaliceDAO.deleteMaliceUser(accountID);
    }

    public MaliceUser getAccount (String accountID) throws Exception{
        MaliceDAO MaliceDAO = new MaliceDAO();
        return MaliceDAO.returnAccount(accountID);
    }
}
