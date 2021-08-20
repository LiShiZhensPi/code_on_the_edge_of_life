package Test8_JDBC;
import java.sql.*;

public class JDBC_Test {
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/Studentinfo?characterEncoding=UTF-8&serverTimezone=UTC";
    private Connection c;
    private Statement s;
    JDBC_Test(){
        try{
            c = DriverManager.getConnection(URL,"root","19891217");
            s = c.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    void Insert(String name,String sex,int age) throws SQLException{
        String sql = String.format("insert into student values('%s','%s',%d)",name,sex,age);
        s.execute(sql);
    }

    void Result(String condition) throws SQLException{
        ResultSet rs;
        String sql;
        if(condition == null)
            sql = "select * from student";
        else
            sql = "select * from student where "+condition;
        rs = s.executeQuery(sql);
        while (rs.next()){
            String name = rs.getString("name");
            String sex = rs.getString("sex");
            int age = rs.getInt("age");
            System.out.println(name + "," + sex + "," +age);
        }


    }

    public static void main(String[] args) throws SQLException{
        JDBC_Test jdbc_test = new JDBC_Test();
        jdbc_test.Insert("Duke","男",16);
        jdbc_test.Insert("ThrShy","男",19);
        jdbc_test.Insert("Uzi","男",25);
        jdbc_test.Insert("Faker","男",26);
        jdbc_test.Insert("Jenny","女",14);
        System.out.println("all:");
        jdbc_test.Result(null);
        System.out.println("age>18");
        jdbc_test.Result("age>18");
    }



}
