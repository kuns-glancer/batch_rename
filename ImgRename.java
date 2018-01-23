import java.io.File;
import java.util.UUID;

/**
 * @author: kun
 * @version: 1.0
 */
public class ImgRename {

    private static String BASE_PATH = null;

    private static Boolean SAVE_DIR_NAME = Boolean.FALSE;

    public static void main(String[] args) {
        if (0 == args.length) {
            throw new RuntimeException("please enter the file path!");
        } else if (1 == args.length) {
            BASE_PATH = args[0];
        } else if (2 == args.length) {
            BASE_PATH = args[0];
	    SAVE_DIR_NAME = args[1].equals("True") || args[1].equals("true") || args[1].equals("1");
        } else {
            throw new RuntimeException("the length of args can't more than two");
        }
        File dir = new File("/home/kun/Pictures/test");
        renameImg(dir, Boolean.TRUE);

    }

    /**
     * 批量重命名图片
     */
    public static void renameImg(File dir, Boolean FIRST) {

        if (!dir.exists()) {
            throw new RuntimeException(BASE_PATH + "is not exists,please make sure of you have a correct path.");
        }
        if (!dir.isDirectory()) {
            throw new RuntimeException(BASE_PATH + "is not a directory!");
        }
        for (File file : dir.listFiles()) {
            if (file.isFile()) {
                // 获取文件所在的文件夹
                String parent_path = file.getParent();
                // 获取文件的后缀名
                String file_name = file.getName();
                int point_idx = file_name.lastIndexOf(".");
                String file_type = point_idx > 0 ? file.getName().substring(point_idx) : "";
                // 生成随机的文件名
                String new_name = UUID.randomUUID().toString() + file_type;
                if (!FIRST && SAVE_DIR_NAME) {
                    String parent_name = file.getParentFile().getName();
                    new_name = parent_name + "_" + UUID.randomUUID().toString() + file_type;
                }
                // 得到新的文件
                File new_file = new File(Path.join(parent_path, new_name));
                // 重命名
                file.renameTo(new_file);
            } else {
                renameImg(file, Boolean.FALSE);
            }
        }
    }
}
