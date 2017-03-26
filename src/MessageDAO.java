import java.sql.*;
import java.util.ArrayList;

public class MessageDAO {
    final private static String driverClassName = "org.postgresql.Driver";
    final private static String dbname = "TST"; //データベース名前
    final private static String user = "tstuser"; //ユーザー名
    final private static String password = "hogehoge"; //パスワード
    final private static String url = "jdbc:postgresql://localhost/" + dbname;
    Connection connection;

    PreparedStatement pstmt_S; //SELECT用
    PreparedStatement pstmt_SM; //SELECT用 悪意ユーザ用?
    PreparedStatement pstmt_I; //INSERT用
    PreparedStatement pstmt_U1; //UPDATE用 message
    PreparedStatement pstmt_U2; //UPDATE用 TST
    PreparedStatement pstmt_D; //DELETE用

    ResultSet resultSet;

    String SELECT_MALICE = "SELECT * FROM message";
    String SELECT_SQL = "SELECT * FROM message WHERE number = ?";
    String INSERT_SQL = "INSERT INTO message (message, number, tst) VALUES (?, ?, ?)";
    String UPDATE_MESSAGE = "UPDATE message SET message = ? WHERE messageid = ?";
    String UPDATE_TST = "UPDATE message SET TST = ? WHERE messageid = ?";
    String DELETE_SQL = "DELETE FROM message WHERE messageid = ?";

    public ArrayList<Message> returnMessage(int number) throws SQLException{ //メッセージ取得(一般ユーザ用?
        ArrayList<Message> messages = new ArrayList<Message>();

        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);
            pstmt_S = connection.prepareStatement(SELECT_SQL);
            pstmt_S.setInt(1, number);

            messages = showTables(pstmt_S.executeQuery());

            pstmt_S.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public ArrayList<Message> maliceReturn() throws SQLException{ //メッセージ取得(悪意ユーザ用?
        ArrayList<Message> messages = new ArrayList<Message>();

        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);
            pstmt_SM = connection.prepareStatement(SELECT_MALICE);

            messages = showTables(pstmt_SM.executeQuery());

            pstmt_SM.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return messages;
    }

    public boolean makeMessage(Message message) throws SQLException{ //メッセージ作成
        boolean result = true;
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);

            pstmt_I =connection.prepareStatement(INSERT_SQL);
            pstmt_I.setString(1, message.getMessage());
            //pstmt_I.setInt(2, generaluser.getNumber);
            pstmt_I.setByte(3, message.getTST());

            pstmt_I.executeUpdate();

            pstmt_I.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateMessage(Message message) throws SQLException{ //メッセージ更新
        boolean result = true;
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);

            pstmt_U1 = connection.prepareStatement(UPDATE_MESSAGE);
            pstmt_U2 = connection.prepareStatement(UPDATE_TST);

            pstmt_U1.setString(1,message.getMessage());
            pstmt_U2.setInt(2,message.getMessageID);
            pstmt_U1.executeUpdate();

            pstmt_U2.setString(1,message.getTST());
            pstmt_U2.setString(2,message.getMessageID);
            pstmt_U2.executeUpdate();

            pstmt_U1.close();
            pstmt_U2.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void deleteMessage(String messageID) throws SQLException{ //メッセージ削除
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);
            pstmt_D = connection.prepareStatement(DELETE_SQL);
            pstmt_D.setString(1, messageID);
            pstmt_D.executeUpdate();

            pstmt_D.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static Message showTable(ResultSet resultset) throws Exception {
        Message message = new Message();
        while(resultset.next()) {
            message.setMessageID(resultset.getInt("messageID"));
            message.setMessage(resultset.getString("message"));
            message.setNumber(resultset.getInt("number"));
            message.setTST(resultset.getByte("TST"));
            resultset.close();
        }
        return message;
    }

    static ArrayList<Message> showTables(ResultSet resultSet) throws Exception {
    ArrayList<Message> messages = new ArrayList<Message>();
    while(resultSet.next()) {
        Message message = new Message();
        message.setMessageID(resultset.getInt("messageID"));
        message.setMessage(resultset.getString("message"));
        message.setNumber(resultset.getInt("number"));
        message.setTST(resultset.getByte("TST"));

        messages.add(message);
    }
    resultset.close();
    return messages;
    }
}