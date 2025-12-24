import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 用户服务类
 */
public class UserService {
    private Map<String, User> users;
    private static final String USER_FILE = "users.json";

    public UserService() {
        this.users = new HashMap<>();
        loadUsers();
    }

    /**
     * 用户注册
     * @param name 用户名
     * @param email 邮箱
     * @param password 密码
     * @return 注册是否成功
     */
    public boolean register(String name, String email, String password) {
        if (users.containsKey(email)) {
            return false;
        }

        User user = new User(name, email, password, "");
        users.put(email, user);
        saveUsers();
        return true;
    }

    /**
     * 用户登录
     * @param email 邮箱
     * @param password 密码
     * @return 登录是否成功
     */
    public boolean login(String email, String password) {
        User user = users.get(email);
        if (user == null) {
            return false;
        }
        return user.getPassword().equals(password);
    }

    /**
     * 保存用户数据到文件
     */
    private void saveUsers() {
        try (FileWriter writer = new FileWriter(USER_FILE)) {
            // 使用Gson或其他JSON库序列化对象
            writer.write("{\"users\":[");

            boolean first = true;
            for (User user : users.values()) {
                if (!first) {
                    writer.write(",");
                }
                first = false;

                writer.write("{\"name\":\"" + user.getName() + "\",\"email\":\"" +
                        user.getEmail() + "\",\"password\":\"" + user.getPassword() +
                        "\",\"phone\":\"" + user.getPhone() + "\"}");
            }

            writer.write("]}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从文件加载用户数据
     */
    private void loadUsers() {
        File file = new File(USER_FILE);
        if (!file.exists()) {
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            // 使用Gson或其他JSON库反序列化对象
            // 这里简化处理，实际需要使用JSON解析库
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据邮箱获取用户
     * @param email 邮箱
     * @return 用户对象
     */
    public User getUserByEmail(String email) {
        return users.get(email);
    }

    /**
     * 获取所有用户
     * @return 用户列表
     */
    public Map<String, User> getAllUsers() {
        return new HashMap<>(users);
    }
}
