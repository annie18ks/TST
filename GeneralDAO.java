import java.sql.*;

public class GeneralDAO {
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
    String UPDATE_ACCOUNTID = "UPDATE general SET accountid = ? WHERE number = ?";
    String UPDATE_PASSWORD = "UPDATE general SET password = ? WHERE accountid = ?";
    String DELETE_SQL = "DELETE FROM general WHERE accountid = ?";

    public GeneralUser returnAccount(String accountID) throws SQLException{ //一般アカウント取得
        GeneralUser generaluser = new GeneralUser();

        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);
            pstmt_S = connection.prepareStatement(SELECT_SQL);
            pstmt_S.setString(1, accountID);

            generaluser = showTable(pstmt_S.executeQuery());

            pstmt_S.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return generaluser;
    }

    public boolean makeGeneralUser(GeneralUser generaluser) throws SQLException{ //一般アカウント作成
        boolean result = true;
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);

            pstmt_I =connection.prepareStatement(INSERT_SQL);
            pstmt_I.setString(1, generaluser.getAccountID());
            pstmt_I.setString(2, generaluser.getAccountPW());
            pstmt_I.setBytes(3, generaluser.getPrivateKey());
            pstmt_I.setBytes(4, generaluser.getPublicKey());

            pstmt_I.executeUpdate();

            pstmt_I.close();
            connection.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean updateGeneralUser(GeneralUser generaluser) throws SQLException{ //一般アカウント情報更新
        boolean result = true;
        try{
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, user, password);

            pstmt_U1 = connection.prepareStatement(UPDATE_ACCOUNTID);
            pstmt_U2 = connection.prepareStatement(UPDATE_PASSWORD);

            pstmt_U1.setString(1,generaluser.getAccountID());
            pstmt_U1.setInt(2,generaluser.getNumber());
            pstmt_U1.executeUpdate();

            pstmt_U2.setString(1,generaluser.getAccountPW());
            pstmt_U2.setString(2,generaluser.getAccountID());
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

    public void deleteGeneralUser(String accountID) throws SQLException{ //一般アカウント削除
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

    static GeneralUser showTable(ResultSet resultset) throws Exception {
        GeneralUser generaluser = new GeneralUser();
        while(resultset.next()) {
            generaluser.setNumber(resultset.getInt("number"));
            generaluser.setAccountID(resultset.getString("accountID"));
            generaluser.setAccountPW(resultset.getString("password"));
            generaluser.setPrivateKey(resultset.getBytes("privatekey"));
            generaluser.setPublicKey(resultset.getBytes("publickey"));
            resultset.close();
        }
        return generaluser;
    }
}