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

    public boolean makeMessage(Message message, int number) throws SQLException{ //メッセージ作成
        boolean result = true;
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);

            pstmt_I =connection.prepareStatement(INSERT_SQL);
            pstmt_I.setString(1, message.getMessage());
            pstmt_I.setInt(2, number);
            pstmt_I.setBytes(3, message.getTst());

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
            pstmt_U1.setInt(2,message.getMessageID());
            pstmt_U1.executeUpdate();

            pstmt_U2.setBytes(1,message.getTst());
            pstmt_U2.setInt(2,message.getMessageID());
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

    public void deleteMessage(int messageID) throws SQLException{ //メッセージ削除
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);
            pstmt_D = connection.prepareStatement(DELETE_SQL);
            pstmt_D.setInt(1, messageID);
            pstmt_D.executeUpdate();

            pstmt_D.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static Message showTable(ResultSet resultSet) throws Exception {
        Message message = new Message();
        while(resultSet.next()) {
            message.setMessageID(resultSet.getInt("messageID"));
            message.setMessage(resultSet.getString("message"));
            message.setNumber(resultSet.getInt("number"));
            message.setTst(resultSet.getBytes("TST"));
            resultSet.close();
        }
        return message;
    }

    static ArrayList<Message> showTables(ResultSet resultSet) throws Exception {
    ArrayList<Message> messages = new ArrayList<Message>();
    while(resultSet.next()) {
        Message message = new Message();
        message.setMessageID(resultSet.getInt("messageID"));
        message.setMessage(resultSet.getString("message"));
        message.setNumber(resultSet.getInt("number"));
        message.setTst(resultSet.getBytes("TST"));

        messages.add(message);
    }
    resultSet.close();
    return messages;
    }
}