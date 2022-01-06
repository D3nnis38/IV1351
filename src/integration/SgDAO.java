package integration;

import model.Instrument;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Author: DENNIS HADZIALIC
 * Date: 2022-01-06
 * Description:
 **/
public class SgDAO {
    private Connection connection;
    private final String DATABASE_URL = "jdbc:postgresql://localhost:5433/soundgood";

    private PreparedStatement getInstruments;

    public SgDAO() throws SgDBException {
        try {
            connect();
        } catch (Exception e) {
            throw new SgDBException("Connection to database failed", e);
        }
    }

    private void connect() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "postgres");
        connection = DriverManager.getConnection(DATABASE_URL, properties);
        connection.setAutoCommit(false);
        prepareStatements();
    }

    public ArrayList<Instrument> getInstruments(String type) throws SgDBException {
        ResultSet res = null;
        try {
            getInstruments.setString(1, type);
            res = getInstruments.executeQuery();
            ArrayList<Instrument> i = new ArrayList<Instrument>();
            while (res.next()) {
                i.add(new Instrument(
                        res.getString("instrument_id"),
                        res.getString("instrument"),
                        res.getString("brand"),
                        res.getDouble("price"),
                        res.getBoolean("is_rented"))
                );
            }
            connection.commit();
            return i;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void prepareStatements() throws SQLException {
        getInstruments = connection.prepareStatement(
                "SELECT * FROM instruments WHERE instrument = ? AND is_rented = FALSE"
        );
    }
}
