/**
 * Created by PCUser on 2017/03/27.
 */
import java.util.ArrayList;
public class MessageManager {

    public boolean makeMessage(String message, int Number, byte[] TST
                              ) throws Exception{
        Message msg = new Message();
        MessageDAO MessageDAO = new MessageDAO();
        msg.setMessage(message);
        //msg.setNumber(number);//メッセージナンバーの引っ張り方わからず保留
        msg.setTST(TST);
        return MessageDAO.makeMessage(msg);

    }

    public boolean updateMessage (int messageID, String message, byte[] TST) throws Exception{
        Message msg = new Message();
        MessageDAO MessageDAO = new MessageDAO();
        msg.setMessageID(messageID);
        msg.setMessage(message);
        msg.setTST(TST);
        return MessageDAO.updateMessage(msg);
    }

    public ArrayList<Message> returnMessage(int number) throws Exception{
        MessageDAO MessageDAO = new MessageDAO();
        return MessageDAO.returnMessage(number);
    }

    public ArrayList<Message> MaliceReturn() throws Exception{
        MessageDAO MessageDAO = new MessageDAO();
        return MessageDAO.maliceReturn();
    }

    public void deleteMessage (int messageID) throws Exception{
        MessageDAO MessageDAO = new MessageDAO();
        MessageDAO.deleteMessage(messageID);
    }


}
