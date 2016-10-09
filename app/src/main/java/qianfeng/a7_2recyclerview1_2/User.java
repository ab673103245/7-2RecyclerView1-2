package qianfeng.a7_2recyclerview1_2;

/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class User {

    private int userface;
    private String username;

    public User(int userface, String username) {
        this.userface = userface;
        this.username = username;
    }

    public User() {
    }

    public int getUserface() {
        return userface;
    }

    public void setUserface(int userface) {
        this.userface = userface;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
