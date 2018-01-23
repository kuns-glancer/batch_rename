/**
 * @author: kun
 * @version: 1.0
 */
public class Path {

    public static String join(String parent_path, String child_path) {
        // 获取操作系统类型
        String os_name = System.getProperty("os.name");
        if (os_name.indexOf("Linux") >= 0) {
            return parent_path + "/" + child_path;
        } else if (os_name.indexOf("Windows") >= 0) {
            return parent_path + "\\" + child_path;
        } else {
            throw new RuntimeException("the os is not supperted!");
        }
    }

}
