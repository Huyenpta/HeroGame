package dao;

import database.DBConnection;
import java.sql.*;

public class NationalDaoImpl {
    public void insertNational(String name) {
        String sql = "INSERT INTO National (NationalName) VALUES (?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            System.out.println("=> Thêm quốc gia thành công!");
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }

    public void deleteNational(int id) {
        String sql = "DELETE FROM National WHERE NationalId = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("=> Xóa quốc gia thành công!");
            else System.out.println("=> Không tìm thấy quốc gia ID: " + id);
        } catch (SQLException e) {
            System.out.println("=> Lỗi: Không thể xóa quốc gia này vì đang có Player thuộc quốc gia đó!");
        }
    }

    public void displayAll() {
        String sql = "SELECT * FROM National";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- DANH SÁCH QUỐC GIA ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("NationalId") + " | Tên: " + rs.getString("NationalName"));
            }
        } catch (SQLException e) { System.out.println(e.getMessage()); }
    }
}