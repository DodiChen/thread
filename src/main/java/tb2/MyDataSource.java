package tb2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * @author chendong
 * @date 2019/4/23 20:42
 */
public class MyDataSource {

    private LinkedList<Connection> pool = new LinkedList<Connection>();

    private static final int MAX_CONNECTIONS = 10;

    private static final String DRIVER_CLASS = "";
    private static final String USER = "";
    private static final String PASSWORD = "";
    private static final String URL = "";

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSource(){
        for(int i = 0; i <MAX_CONNECTIONS; i++){
            try {
                Connection coon = DriverManager.getConnection(URL, USER, PASSWORD);
                pool.addLast(coon);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection(){
        Connection result = null;
        synchronized (pool){
            while(pool.size() <= 0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(!pool.isEmpty()){
                result = pool.removeFirst();
            }
        }
        return result;
    }

    public void release(Connection connection){
        if(connection != null){
            synchronized (pool){
                pool.addLast(connection);
                notifyAll();
            }
        }
    }

}
