import java.util.List;
import java.util.Scanner;

/**
 * 智能记事本命令行交互系统
 */
public class SmartJournalingCLI {
    private Scanner scanner;
    private UserService userService;
    private NoteService noteService;

    public SmartJournalingCLI() {
        this.scanner = new Scanner(System.in);
        this.userService = new UserService();
        this.noteService = new NoteService();
    }

    public void start() {
        // 添加问候语功能
        showGreeting();

        while (true) {
            showMenu();
            int choice = getUserChoice();
            handleChoice(choice);
        }
    }

    // 新增方法：显示问候语
    private void showGreeting() {
        // 获取当前时间（GMT+8）
        java.time.LocalDateTime now = java.time.LocalDateTime.now(java.time.ZoneId.of("Asia/Shanghai"));

        int hour = now.getHour();

        String greeting;
        if (hour >= 0 && hour <= 11) {
            greeting = "早上好 (Good Morning)";
        } else if (hour >= 12 && hour <= 16) {
            greeting = "下午好 (Good Afternoon)";
        } else { // hour >= 17 && hour <= 23
            greeting = "晚上好 (Good Evening)";
        }

        System.out.println("=== " + greeting + " ===");
    }

    private void showMenu() {
        System.out.println("=== 智能记事本 ===");
        System.out.println("1. 登录");
        System.out.println("2. 注册");
        System.out.println("3. 退出");
        System.out.print("请选择操作: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("请输入有效数字");
            return -1;
        }
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
            case 3:
                System.out.println("程序退出");
                scanner.close();
                System.exit(0);
            default:
                System.out.println("无效选择，请重新输入");
        }
    }

    private void login() {
        System.out.print("请输入邮箱: ");
        String email = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        if (userService.login(email, password)) {
            System.out.println("登录成功！");
            showNoteMenu();
        } else {
            System.out.println("登录失败，用户不存在或密码错误");
        }
    }

    private void register() {
        System.out.print("请输入用户名: ");
        String name = scanner.nextLine();
        System.out.print("请输入邮箱: ");
        String email = scanner.nextLine();
        System.out.print("请输入密码: ");
        String password = scanner.nextLine();

        if (userService.register(name, email, password)) {
            System.out.println("注册成功！");
        } else {
            System.out.println("注册失败");
        }
    }

    private void showNoteMenu() {
        while (true) {
            System.out.println("\n=== 笔记服务 ===");
            System.out.println("1. 创建新笔记");
            System.out.println("2. 查看所有笔记");
            System.out.println("3. 搜索笔记");
            System.out.println("4. 返回主菜单");
            System.out.print("请选择操作: ");

            int choice = getUserChoice();
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
                    return;
                default:
                    System.out.println("无效选择，请重新输入");
            }
        }
    }

    private void createNote() {
        System.out.print("请输入笔记标题: ");
        String title = scanner.nextLine();

        System.out.print("请输入笔记内容: ");
        String content = scanner.nextLine();

        Note note = new Note(title, content);
        noteService.createNote(note);
        System.out.println("笔记创建成功！");
    }

    private void viewAllNotes() {
        List<Note> notes = noteService.getAllNotes();
        if (notes.isEmpty()) {
            System.out.println("暂无笔记");
            return;
        }

        System.out.println("=== 所有笔记 ===");
        for (int i = 0; i < notes.size(); i++) {
            System.out.println((i + 1) + ". " + notes.get(i).getTitle());
        }

        // 添加选择查看笔记内容的功能
        System.out.print("请输入要查看的笔记编号: ");
        int choice = getUserChoice();

        if (choice >= 1 && choice <= notes.size()) {
            Note selectedNote = notes.get(choice - 1);
            showNoteContent(selectedNote);
        } else {
            System.out.println("无效选择");
        }
    }
    private void showNoteContent(Note note) {
        System.out.println("\n=== 笔记内容 ===");
        System.out.println("标题: " + note.getTitle());
        System.out.println("创建时间: " + note.getCreatedAt());
        System.out.println("更新时间: " + note.getUpdatedAt());
        System.out.println("内容:");
        System.out.println(note.getContent());
        System.out.println("==================\n");
    }

    private void searchNotes() {
        System.out.print("请输入搜索关键词: ");
        String keyword = scanner.nextLine();

        List<Note> results = noteService.searchNotes(keyword);
        if (results.isEmpty()) {
            System.out.println("未找到相关笔记");
            return;
        }

        System.out.println("=== 搜索结果 ===");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i + 1) + ". " + results.get(i).getTitle());
        }
    }
}

