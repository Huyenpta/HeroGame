import dao.PlayerDaoImpl;
import entity.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlayerDaoImpl playerDao = new PlayerDaoImpl();
        int choice;

        do {
            System.out.println("\n===== HERO GAME MENU =====");
            System.out.println("1. Hiển thị tất cả người chơi (Display All)");
            System.out.println("2. Thêm người chơi mới (Insert Player)");
            System.out.println("3. Xóa người chơi (Delete Player)");
            System.out.println("4. Tìm kiếm người chơi theo tên (Find by Name)");
            System.out.println("5. Hiển thị Top 10 điểm cao nhất (Top 10 High Score)");
            System.out.println("0. Thoát chương trình");
            System.out.print("Vui lòng chọn chức năng (0-5): ");

            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    playerDao.displayAll();
                    break;
                case 2:
                    System.out.println("\n--- THÊM NGƯỜI CHƠI ---");
                    System.out.print("Nhập National ID (VD: 1 cho Vietnam, 2 cho USA): ");
                    int nationalId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nhập tên Player: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập High Score: ");
                    int score = Integer.parseInt(scanner.nextLine());
                    System.out.print("Nhập Level: ");
                    int level = Integer.parseInt(scanner.nextLine());

                    Player newPlayer = new Player(0, nationalId, name, score, level);
                    playerDao.insertPlayer(newPlayer);
                    break;
                case 3:
                    System.out.print("\nNhập ID người chơi cần xóa: ");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    playerDao.deletePlayer(deleteId);
                    break;
                case 4:
                    System.out.print("\nNhập tên người chơi cần tìm: ");
                    String searchName = scanner.nextLine();
                    playerDao.displayAllByPlayerName(searchName);
                    break;
                case 5:
                    playerDao.displayTop10();
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại!");
            }
        } while (choice != 0);

        scanner.close();
    }
}