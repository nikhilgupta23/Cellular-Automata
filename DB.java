package se;

import java.sql.*;

/**
 *
 * @author acer
 */
class DB {
    Connection c;
    Statement stmt;
    void connDB()
    {
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:UserInfo.db");
    } catch ( Exception e ) {
      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0);
    }
        System.out.println("Opened database successfully");
    }

    boolean createTable()
    {
      try {
      stmt = c.createStatement();
      String sql = "CREATE TABLE UInfo (" +
                   " ID             CHAR(10) PRIMARY KEY     NOT NULL," +
                   " NAME           TEXT   , " +
                   " Password       CHAR(10)     NOT NULL, " +
                   " HighScore      INT          DEFAULT(0)," +
                   " CaveColor      INT check(CaveColor < 5 AND CaveColor > 0),"+
                   " CharColor      INT check(CharColor < 5 AND CharColor > 0))";
      stmt.executeUpdate(sql);
      //stmt.close();
      return true;
      } catch (SQLException E)
        {   System.out.println("Create Table Failed");
            return false; }
    }

    boolean insertDB(String user, String name, String pass)
    {
      try {
      stmt = c.createStatement();
      String sql = "INSERT INTO UInfo(ID, NAME, Password)"+
             "VALUES('"+user+"','"+name+"','"+pass+"')";
      stmt.executeUpdate(sql);
      //stmt.close();
      //c.close();
      return true;
      } catch (SQLException E)
        {   System.out.println("Insert Failed");
            return false; }
    }

    boolean chkPass(String user, String pass)
    {
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT Password FROM UInfo where ID = '"+user+"'" );
            String passD = rs.getString("Password");
            if (passD.equals(pass))
                return true;
            else
                return false;
        } catch (SQLException E)
        {
            System.out.println("SQLException-chk Pass failed.");
            return false;
        }
    }

    int viewHS(String user)
    {
        try {
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT HighScore FROM UInfo where ID = '"+user+"'" );
            return (rs.getInt("HighScore"));
        } catch (Exception E)
        {
            System.out.println("Exception- view failed.");
            return -1;
        }
    }

    boolean updateHS(String user, int hs)
    {
        int HS = 0;
        try {
       stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM UInfo where ID like '"+user+"'" );

      while ( rs.next() ) {
         HS = rs.getInt("HighScore");
      }
       }
      catch (SQLException E)
          { System.out.println("Update Failed");
            return false; }
      //System.out.println(HS);
      if (hs > HS) {
      //stmt = c.createStatement();
      String sql = "UPDATE UInfo set HighScore = "+hs+" where ID = '"+user+"'";
      try {
      stmt.executeUpdate(sql);
      } catch (SQLException E)
          {   System.out.println("Update Failed1");
            return false; }
      //stmt.close();
      return true;
      }
      return false;
      //c.close();


    }

    boolean updateColor(String user, int CaC, int ChC)
    {
        try
        {
            stmt = c.createStatement();
            String sql = "UPDATE UInfo set CaveColor = "+CaC+" where ID = '"+user+"';";
            String sql1 = "UPDATE UInfo set CharColor = "+ChC+" where ID = '"+user+"';";
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql1);
            //stmt.close();
            //c.commit();
            System.out.println("Successful Color update");
            return true;
        } catch (SQLException E)
        {
            System.out.println(E);
            return false;
        }
    }

    short CacSelect(String user)
    {
        try {
        stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT Cavecolor FROM UInfo where ID = '"+user+"'" );
      short Cac  = rs.getShort("Cavecolor");
      rs.close();
      //stmt.close();
      if (Cac == 0)
          return -1;
      return Cac;
      } catch (SQLException E)
      {     System.out.println("Select Failed");
            return -1;                    }
    }

    short ChcSelect(String user)
    {
        try {
        stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT Charcolor FROM UInfo where ID = '"+user+"'" );
      short Cac  = rs.getShort("Charcolor");
      rs.close();
      //stmt.close();
      if (Cac == 0)
          return -1;
      return Cac;
      } catch (SQLException E)
      {     System.out.println("Select Failed");
            return -1;                    }
    }

    void selectInfo(String user)
    {
      try {
        stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT ID, Name, HighScore FROM UInfo" );
      while ( rs.next() ) {
         String id = rs.getString("ID");
         String  name = rs.getString("Name");
         int hs  = rs.getInt("HighScore");
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "HIGH SCORE = " + hs );
         System.out.println();
      }
      rs.close();
      //stmt.close();
      } catch (SQLException E)
      {     System.out.println("Select Failed");   }
    }

    void selectAll(String user)
    {
        try {
        stmt = c.createStatement();
      ResultSet rs = stmt.executeQuery( "SELECT * FROM UInfo" );
      while ( rs.next() ) {
         String id = rs.getString("ID");
         String  name = rs.getString("Name");
         String pass = rs.getString("Password");
         int hs  = rs.getInt("HighScore");
         int  CaC = rs.getInt("CaveColor");
         int ChC  = rs.getInt("CharColor");
         System.out.println( "ID = " + id );
         System.out.println( "NAME = " + name );
         System.out.println( "HIGH SCORE = " + hs );
         System.out.println( "CaveColor = " + CaC );
         System.out.println( "CharColor = " + ChC );
         System.out.println();
      }
      //rs.close();
      //stmt.close();
      //c.close();
      } catch (SQLException E)
      {     System.out.println("Select Failed");   }
    }

    void closeDB()
    {
        try {
        c.close();
        } catch (SQLException E)
        { System.out.println(E); }
    }

    public static void main(String Args[])
    {
        DB D = new DB();
        D.connDB();
        D.createTable();
        boolean test; //= D.insertDB("admin", "Admin", "1234");
        D.selectInfo("admin");
        test = D.updateHS("admin", 20);
        System.out.println(test);
        D.selectInfo("admin");
        //test = D.updateColor("admin", 1, 1);
        D.selectAll("admin");
        test = D.chkPass("admin", "878");
        System.out.println(test);
        D.closeDB();
    }
}
