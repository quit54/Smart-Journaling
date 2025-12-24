/**
 * 主函数 - 启动智能记事本应用
 */
public class Main {
    public static void main(String[] args) {
        // 创建智能记事本命令行交互系统实例
        SmartJournalingCLI cli = new SmartJournalingCLI();

        // 启动程序
        cli.start();
    }
}
