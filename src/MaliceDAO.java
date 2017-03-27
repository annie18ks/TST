import java.sql.*;

public class MaliceDAO {
    final private static String driverClassName = "org.postgresql.Driver";
    final private static String dbname = "TST"; //データベース名前
    final private static String user = "tstuser"; //ユーザー名
    final private static String password = "hogehoge"; //パスワード
    final private static String url = "jdbc:postgresql://localhost/" + dbname;
    Connection connection;

    PreparedStatement pstmt_S; //SELECT用
    PreparedStatement pstmt_I; //INSERT用
    PreparedStatement pstmt_U1; //UPDATE用 accountid
    PreparedStatement pstmt_U2; //UPDATE用 password
    PreparedStatement pstmt_D; //DELETE用

    ResultSet resultSet;

    String SQL_S = "SELECT * FROM malice";
    String SELECT_SQL = "SELECT * FROM malice WHERE accountid = ?";
    String INSERT_SQL = "INSERT INTO malice (accountid, password) VALUES (?, ?)";
    String UPDATE_ACCOUNTID = "UPDATE malice SET accountid = ? WHERE number = ?";
    String UPDATE_PASSWORD = "UPDATE malice SET password = ? WHERE accountid = ?";
    String DELETE_SQL = "DELETE FROM malice WHERE accountid = ?";

    public MaliceUser returnAccount(String accountID) throws SQLException{ //悪意アカウント取得
        MaliceUser maliceuser = new MaliceUser();

        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);
            pstmt_S = connection.prepareStatement(SELECT_SQL);
            pstmt_S.setString(1, accountID);

            maliceuser = showTable(pstmt_S.executeQuery());

            pstmt_S.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return maliceuser;
    }

    public boolean makeMaliceUser(MaliceUser maliceuser) throws SQLException{ //悪意アカウント作成
        boolean result = true;
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);

            pstmt_I =connection.prepareStatement(INSERT_SQL);
            pstmt_I.setString(1, maliceuser.getAccountID());
            pstmt_I.setString(2, maliceuser.getPassword());

            pstmt_I.executeUpdate();

            pstmt_I.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateMaliceUser(MaliceUser maliceuser) throws SQLException{ //悪意アカウント情報更新
        boolean result = true;
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);

            pstmt_U1 = connection.prepareStatement(UPDATE_ACCOUNTID);
            pstmt_U2 = connection.prepareStatement(UPDATE_PASSWORD);

            pstmt_U1.setString(1,maliceuser.getAccountID());
            pstmt_U2.setInt(2,maliceuser.getNumber);
            pstmt_U1.executeUpdate();

            pstmt_U2.setString(1,maliceuser.getPassword());
            pstmt_U2.setString(2,maliceuser.getAccountID);
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

    public void deleteMaliceUser(String accountID) throws SQLException{ //悪意アカウント削除
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);
            pstmt_D = connection.prepareStatement(DELETE_SQL);
            pstmt_D.setString(1, accountID);
            pstmt_D.executeUpdate();

            pstmt_D.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    static MaliceUser showTable(ResultSet resultset) throws Exception {
        MaliceUser maliceuser = new MaliceUser();
        while(resultset.next()) {
            maliceuser.setNumber(resultset.getInt("number"));
            maliceuser.setAccountID(resultset.getString("accountID"));
            maliceuser.setPassword(resultset.getString("password"));
            resultset.close();
        }
        return maliceuser;
    }
}