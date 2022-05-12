package dam.jkutkut.db;

import dam.jkutkut.exception.SQLiteQueryException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SQLiteQuery {
    public static ArrayList<Object[]> get(AccessDB db, int outputLength, String query, Object... input) throws SQLiteQueryException {
        ArrayList<Object[]> output = new ArrayList<>();

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rslt = null;

        try {
            con = db.getConnection();

            pstmt = con.prepareStatement(query);
            for (int i = 0; i < input.length; i++) {
                pstmt.setObject(i + 1, input[i]);
            }

            rslt = pstmt.executeQuery();

            while (rslt.next()) {
                Object[] row = new Object[outputLength];
                for (int i = 0; i < outputLength; i++) {
                    row[i] = rslt.getObject(i + 1);
                }
                output.add(row);
            }
        }
        catch (ClassNotFoundException e) {
            throw new SQLiteQueryException("El driver indicado no es correcto:\n" + e.getMessage());
        }
        catch (SQLException e) {
            throw new SQLiteQueryException("Error en la base de datos:\n" + e.getMessage());
        }
        finally {
            try {
                if (rslt != null) rslt.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                throw new SQLiteQueryException("Error al cerrar las conexiones:\n" + e.getMessage());
            }
        }
        return output;
    }

    public static int execute(AccessDB db, String query, Object... input) throws SQLiteQueryException {
        int result;

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = db.getConnection();

            pstmt = con.prepareStatement(query);

            for (int i = 0; i < input.length; i++) {
                pstmt.setObject(i + 1, input[i]);
            }

            result = pstmt.executeUpdate();
        }
        catch (ClassNotFoundException e) {
            throw new SQLiteQueryException("El driver indicado no es correcto:\n" + e.getMessage());
        }
        catch (SQLException e) {
            throw new SQLiteQueryException("Error en la base de datos:\n" + e.getMessage());
        }
        finally {
            try {
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();
            }
            catch (SQLException e) {
                throw new SQLiteQueryException("Error al cerrar las conexiones:\n" + e.getMessage());
            }
        }

        return result;
    }
}
