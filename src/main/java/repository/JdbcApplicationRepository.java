package repository;

import db.Database;
import model.Application;
import model.Status;
import service.ApplicationNotFoundException;

import javax.swing.text.html.Option;
import javax.xml.crypto.Data;
import java.awt.image.DataBuffer;
import java.sql.*;
import java.util.*;

public class JdbcApplicationRepository implements ApplicationRepository {
    @Override
    public Application save(String company, String position, long payment) {
        String sql = "INSERT INTO applications (company, position, payment, status)" +
                "VALUES (?, ?, ?, 'APPLIED')" +
                "RETURNING *";
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, company);
            ps.setString(2, position);
            ps.setLong(3, payment);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return mapRow(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error with db", e);
        }
    }

    @Override
    public Optional<Application> findById(long id) {
        String sql = "SELECT * FROM applications WHERE id = ?";
        try (Connection connn = Database.getConnection();
             PreparedStatement ps = connn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error with db", e);
        }
    }

    private Application mapRow(ResultSet rs) throws SQLException {
        return new Application(rs.getString("company"),
                rs.getLong("id"),
                Status.valueOf(rs.getString("status")),
                rs.getLong("payment"),
                rs.getString("position"),
                rs.getDate("created_at").toLocalDate()
        );


    }

    @Override
    public List<Application> findAll() {
        String sql = "SELECT * FROM applications";
        try (Connection conn = Database.getConnection()) {
            Statement s = conn.createStatement();
            try (ResultSet res = s.executeQuery(sql)) {
                List<Application> list = new ArrayList<>();
                while (res.next()) {
                    list.add(mapRow(res));
                }
                return list;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error with db", e);
        }
    }

    @Override
    public void updateStatus(long id, Status status) {
        String sql = "UPDATE applications SET status = ? WHERE id = ?";
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, status.toString());
            ps.setLong(2, id);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new ApplicationNotFoundException(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error with db", e);
        }
    }

    @Override
    public void deleteById(long id) {
        String sql = "DELETE FROM applications WHERE id = ?";
        try (Connection conn = Database.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            int updated = ps.executeUpdate();
            if (updated == 0) {
                throw new ApplicationNotFoundException(id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error with db", e);
        }
    }

    @Override
    public Map<Status, Long> getStatistic() {
        String sql = "SELECT status, COUNT(id) FROM applications GROUP BY status";
        try (Connection conn = Database.getConnection()) {
            Statement s = conn.createStatement();
            try (ResultSet res = s.executeQuery(sql)) {
                Map<Status, Long> resultMap = new HashMap<>();
                while (res.next()) {
                    resultMap.put(Status.valueOf(res.getString("status")), res.getLong("count"));
                }
                return resultMap;
            }
        } catch (SQLException e) {
           throw new RuntimeException("Db error " + e);
        }
    }
}
