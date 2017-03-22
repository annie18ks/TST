import java.sql.*;

public class AccountDAO {
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

    String SQL_S = "SELECT * FROM general";
    String SELECT_SQL = "SELECT * FROM general WHERE accountid = ?";
    String INSERT_SQL = "INSERT INTO general (accountid, password, privatekey, publickey) VALUES (?, ?, ?, ?)";
    String UPDATE_ACCOUNTID = "UPDATE general SET accountid = ? WHERE accountid = ?";
    String UPDATE_PASSWORD = "UPDATE general SET password = ? WHERE accountid = ?";
    String DELETE_SQL = "DELETE FROM general WHERE accountid = ?";

    public Account returnAccount(String accountID) throws SQLException{ //アカウント取得
        Account account = new Account();

        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);
            pstmt_S = connection.prepareStatement(SELECT_SQL);
            pstmt_S.setString(1, accountID);

            account = showTable(pstmt_S.executeQuery());

            Pstmt_S.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return account;
    }

    public boolean makeAccount(Account account) throws SQLException{ //アカウント作成
        boolean result = true;
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);

            pstmt_I =connection.prepareStatement(INSERT_SQL);
            pstmt_I.setString(1, account.getAccountID());
            pstmt_I.setString(2, account.getPassword());
            pstmt_I.setString(3, account.getPrivateKey());
            pstmt_I.setString(4, account.getPublicKey());

            pstmt_I.executeUpdate();

            pstmt_I.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateAccount(Account account) throws SQLException{ //アカウント情報更新
        boolean result = true;
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);

            pstmt_U1 = connection.prepareStatement(UPDATE_ACCOUNTID);
            pstmt_U2 = connection.prepareStatement(UPDATE_PASSWORD);

            pstmt_U1.setString(1,account.getAccountID());
            pstmt_U2.setString(2,accountID);
            pstmt_U1.executeUpdate();

            pstmt_U2.setString(1,account.getPassword());
            pstmt_U2.setString(2,accountID);
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

    public void deleteAccount(String accoundID) throws SQLException{ //アカウント削除
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

    static Account showTable(ResultSet resultset) throws Exception {
        Account account = new Account();
        while(resultset.next()) {
            account.setAccountID(resultset.getString("accountid"));
            account.setPassword(resultset.getString("password"));
            resultset.close();
        }
        return account;
    }
}