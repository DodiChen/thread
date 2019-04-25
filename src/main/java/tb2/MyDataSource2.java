package tb2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author chendong
 * @date 2019/4/23 20:55
 */
public class MyDataSource2 {

    private LinkedList<Connection> pool = new LinkedList<Connection>();

    private static final int MAX_CONNECTIONS = 10;

    private static final String DRIVER_CLASS = "";
    private static final String USER = "";
    private static final String PASSWORD = "";
    private static final String URL = "";

    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();

    static {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MyDataSource2(){
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
        lock.lock();
        try{
            while(pool.size() <= 0){
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(!pool.isEmpty()){
                result = pool.removeFirst();
            }
        }finally {
            lock.unlock();
        }
        return result;
    }

    public void release(Connection connection){
        if(connection != null){
            lock.lock();
            try {
                pool.addLast(connection);
                c1.signal();
            }finally {
                lock.unlock();
            }

        }
    }
}
