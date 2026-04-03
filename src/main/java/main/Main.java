package main;
import dao.NationalDaoImpl;
import dao.PlayerDaoImpl;
import entity.Player;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PlayerDaoImpl playerDao = new PlayerDaoImpl();
        NationalDaoImpl nationalDao = new NationalDaoImpl();
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n========= HERO GAME MANAGEMENT =========");
            System.out.println("1. Hiển thị danh sách Player (Table 1)");
            System.out.println("2. Thêm Player mới");
            System.out.println("3. Xóa Player");
            System.out.println("4. Tìm Player theo tên");
            System.out.println("5. Hiển thị Top 10 High Score");
            System.out.println("6. Quản lý Quốc gia (Thêm/Xóa National)");
            System.out.println("0. Thoát");
            System.out.print("Lựa chọn của bạn: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        playerDao.displayAll();
                        break;
                    case 2:
                        nationalDao.displayAll();
                        System.out.print("Nhập ID quốc gia: ");
                        int nid = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nhập tên người chơi: ");
                        String name = scanner.nextLine();
                        System.out.print("Nhập High Score: ");
                        int score = Integer.parseInt(scanner.nextLine());
                        System.out.print("Nhập Level: ");
                        int level = Integer.parseInt(scanner.nextLine());
                        playerDao.insertPlayer(new Player(0, nid, name, score, level));
                        break;
                    case 3:
                        System.out.print("Nhập ID Player cần xóa: ");
                        int pid = Integer.parseInt(scanner.nextLine());
                        playerDao.deletePlayer(pid);
                        break;
                    case 4:
                        System.out.print("Nhập tên cần tìm: ");
                        String sName = scanner.nextLine();
                        playerDao.displayAllByPlayerName(sName);
                        break;
                    case 5:
                        playerDao.displayTop10();
                        break;
                    case 6:
                        System.out.println("6a. Thêm quốc gia | 6b. Xóa quốc gia");
                        System.out.print("Chọn (a/b): ");
                        String sub = scanner.nextLine();
                        if (sub.equals("a")) {
                            System.out.print("Tên quốc gia mới: ");
                            nationalDao.insertNational(scanner.nextLine());
                        } else {
                            nationalDao.displayAll();
                            System.out.print("Nhập ID quốc gia cần xóa: ");
                            nationalDao.deleteNational(Integer.parseInt(scanner.nextLine()));
                        }
                        break;
                    case 0:
                        System.out.println("Tạm biệt!");
                        break;
                }
            } catch (Exception e) {
                System.out.println("=> [LỖI] Vui lòng chỉ nhập số và không để trống thông tin!");
            }
        }
    }
}