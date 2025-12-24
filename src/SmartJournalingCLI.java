import java.util.List;
import java.util.Scanner;

/**
 * 智能记事本命令行交互系统
 */
public class SmartJournalingCLI {

    private Scanner scanner;
    private data.NoteManager noteManager;//这里是一个特殊的数据结构

    /**
     * 构造函数
     */
    public SmartJournalingCLI() {
        this.scanner = new Scanner(System.in);
        this.noteManager = new data.NoteManager();
    }

    /**
     * 启动程序主循环
     */
    public void start() {
        while (true) {
            showMenu();
            int choice = getUserChoice();
            handleChoice(choice);
        }
    }

    /**
     * 显示菜单选项
     */
    private void showMenu() {
        System.out.println("=== 智能记事本 ===");
        System.out.println("1. 创建新笔记");
        System.out.println("2. 查看所有笔记");
        System.out.println("3. 搜索笔记");
        System.out.println("4. 退出");
        System.out.print("请选择操作: ");
    }

    /**
     * 获取用户选择
     * @return 用户输入的数字选择
     */
    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("请输入有效数字");
            return -1;
        }
    }

    /**
     * 处理用户选择
     * @param choice 用户选择的选项
     */
    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createNote();
                break;
            case 2:
                viewAllNotes();
                break;
            case 3:
                searchNotes();
                break;
            case 4:
                System.out.println("程序退出");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("无效选择，请重新输入");
        }
    }

    /**
     * 创建新笔记
     */
    private void createNote() {
        System.out.print("请输入笔记标题: ");
        String title = scanner.nextLine();

        System.out.print("请输入笔记内容: ");
        String content = scanner.nextLine();

        data.Note note = new data.Note(title, content);
        noteManager.addNote(note);
        System.out.println("笔记创建成功！");
    }

    /**
     * 查看所有笔记
     */
    private void viewAllNotes() {
        List<data.Note> notes = noteManager.getAllNotes();
        if (notes.isEmpty()) {
            System.out.println("暂无笔记");
            return;
        }

        System.out.println("=== 所有笔记 ===");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + ". " + notes.get(i).getTitle());
        }
    }

    /**
     * 搜索笔记
     */
    private void searchNotes() {
        System.out.print("请输入搜索关键词: ");
        String keyword = scanner.nextLine();

        List<data.Note> results = noteManager.searchNotes(keyword);
        if (results.isEmpty()) {
            System.out.println("未找到相关笔记");
            return;
        }

        System.out.println("=== 搜索结果 ===");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getTitle());
        }
    }

    /**
     * 主方法
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        SmartJournalingCLI cli = new SmartJournalingCLI();
        cli.start();
    }
}
