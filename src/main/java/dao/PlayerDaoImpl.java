package dao;

import database.DBConnection;
import entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PlayerDaoImpl {


    public void insertPlayer(Player player) {
        String sql = "INSERT INTO Player (NationalId, PlayerName, HighScore, Level) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, player.getNationalId());
            pstmt.setString(2, player.getPlayerName());
            pstmt.setInt(3, player.getHighScore());
            pstmt.setInt(4, player.getLevel());

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("=> Thêm người chơi thành công!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void deletePlayer(int playerId) {
        String sql = "DELETE FROM Player WHERE PlayerId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, playerId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("=> Xóa người chơi thành công!");
            } else {
                System.out.println("=> Không tìm thấy người chơi với ID này.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void displayAll() {
        String sql = "SELECT p.PlayerId, p.PlayerName, p.HighScore, p.Level, n.NationalName " +
                "FROM Player p JOIN National n ON p.NationalId = n.NationalId";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- DANH SÁCH TẤT CẢ NGƯỜI CHƠI ---");
            System.out.printf("%-10s | %-15s | %-10s | %-6s | %-15s\n", "Player Id", "Player name", "High Score", "Level", "National");
            System.out.println("-----------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10d | %-15s | %-10d | %-6d | %-15s\n",
                        rs.getInt("PlayerId"),
                        rs.getString("PlayerName"),
                        rs.getInt("HighScore"),
                        rs.getInt("Level"),
                        rs.getString("NationalName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void displayAllByPlayerName(String name) {
        String sql = "SELECT p.PlayerId, p.PlayerName, p.HighScore, p.Level, n.NationalName " +
                "FROM Player p JOIN National n ON p.NationalId = n.NationalId " +
                "WHERE p.PlayerName LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + name + "%");
            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- KẾT QUẢ TÌM KIẾM CHO: '" + name + "' ---");
            System.out.printf("%-10s | %-15s | %-10s | %-6s | %-15s\n", "Player Id", "Player name", "High Score", "Level", "National");
            System.out.println("-----------------------------------------------------------------------");

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("%-10d | %-15s | %-10d | %-6d | %-15s\n",
                        rs.getInt("PlayerId"),
                        rs.getString("PlayerName"),
                        rs.getInt("HighScore"),
                        rs.getInt("Level"),
                        rs.getString("NationalName"));
            }
            if (!found) {
                System.out.println("Không tìm thấy người chơi nào phù hợp.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void displayTop10() {
        String sql = "SELECT p.PlayerId, p.PlayerName, p.HighScore, p.Level, n.NationalName " +
                "FROM Player p JOIN National n ON p.NationalId = n.NationalId " +
                "ORDER BY p.HighScore DESC LIMIT 10";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- TOP 10 NGƯỜI CHƠI ĐIỂM CAO NHẤT ---");
            System.out.printf("%-10s | %-15s | %-10s | %-6s | %-15s\n", "Player Id", "Player name", "High Score", "Level", "National");
            System.out.println("-----------------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%-10d | %-15s | %-10d | %-6d | %-15s\n",
                        rs.getInt("PlayerId"),
                        rs.getString("PlayerName"),
                        rs.getInt("HighScore"),
                        rs.getInt("Level"),
                        rs.getString("NationalName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}