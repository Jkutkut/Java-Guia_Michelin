package dam.jkutkut.db;

import dam.jkutkut.exception.InvalidDataException;
import dam.jkutkut.exception.SQLiteQueryException;
import dam.jkutkut.michelin.model.Restaurant;

import java.util.ArrayList;

public class MichelinDB extends AccessDB {
    private static final String DB_LOCATION = "db/GUIA_MICHELIN.db";

    private static final String TABLE_NAME = "RESTAURANTES";
    private static final String COLUMN_ID = "NOMBRE";

    public static final String[] ATRIBUTES = {
        COLUMN_ID,
        "REGION",
        "CIUDAD",
        "DISTINCION",
        "DIRECCION",
        "PRECIO_MIN",
        "PRECIO_MAX",
        "COCINA",
        "TELEFONO",
        "WEB"
    };

    public static final String[] TABLE_ATRIBUTES = {
        "Nombre",
        "Región",
        "Ciudad",
        "Distinción",
        "Dirección",
        "Precio",
        "Cocina",
        "Teléfono",
        "Web"
    };

    public MichelinDB() {
        super(DB_LOCATION);
    }

    public ArrayList<Restaurant> getRestaurants() throws SQLiteQueryException, InvalidDataException {
        // SELECT * FROM TABLE
        String query = String.format(
                "SELECT * FROM %s;",
                TABLE_NAME,
                COLUMN_ID
        );

        ArrayList<Object[]> data = SQLiteQuery.get(
                this,
                11,
                query
        );

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Restaurant r;
        for (Object[] row : data) {
            r = new Restaurant();
            r.setName((String) row[1]);
            r.setRegion((String) row[2]);
            r.setCity((String) row[3]);
            r.setDistinction((Integer) row[4]);
            r.setAddress((String) row[5]);
            if (row[6] != null)
                r.setMinPrice((Double) row[6]);
            if (row[7] != null)
                r.setMaxPrice((Double) row[7]);
            r.setType((String) row[8]);
            r.setPhone((String) row[9]);
            r.setWeb((String) row[10]);
            restaurants.add(r);
        }
        return restaurants;
    }

    public int addRestaurant(Restaurant r) throws SQLiteQueryException, InvalidDataException {
        String query = String.format(
                "INSERT INTO %s VALUES ((SELECT MAX(%s) FROM %s) + 1, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
                TABLE_NAME,
                "ID",
                TABLE_NAME
        );

        int result = SQLiteQuery.execute(
            this, query,
            r.getName(),
            r.getRegion(),
            r.getCity(),
            r.getDistinction(),
            r.getAddress(),
            r.getMinPrice(),
            (r.getMaxPrice() == Restaurant.NULL_PRICE) ? null : r.getMaxPrice(),
            r.getType(),
            r.getPhone(),
            r.getWeb()
        );

        System.out.printf(
                "Added restaurant %s into table %s. Result status: %d\n",
                r.getName(),
                TABLE_NAME,
                result
        );
        return result;
    }

    public int removeRestaurant(Restaurant r) throws SQLiteQueryException, InvalidDataException {
        String query = String.format(
                "DELETE FROM %s WHERE %s = ?;",
                TABLE_NAME,
                COLUMN_ID
        );

        int result = SQLiteQuery.execute(
            this, query,
            r.getName()
        );

        System.out.printf(
                "Deleted restaurant %s from table %s. Result status: %d\n",
                r.getName(),
                TABLE_NAME,
                result
        );
        return result;
    }

    public Restaurant getRestaurant(String name) throws SQLiteQueryException, InvalidDataException {
//        String query = String.format(
//                "SELECT * FROM %s WHERE %s LIKE %s;",
//                TABLE_NAME,
//                COLUMN_ID,
//                "'%?%'"
//        );
        String query = String.format(
                "SELECT * FROM %s WHERE %s LIKE '%%?%%';",
                TABLE_NAME,
                COLUMN_ID
        );

        System.out.println(query);

        ArrayList<Object[]> data = SQLiteQuery.get(
                this,
                11,
                query,
                name
        );

        if (data.size() == 0)
            return null;
        Restaurant r = new Restaurant();

        r.setName((String) data.get(0)[1]);
        r.setRegion((String) data.get(0)[2]);
        r.setCity((String) data.get(0)[3]);
        r.setDistinction((Integer) data.get(0)[4]);
        r.setAddress((String) data.get(0)[5]);
        r.setMinPrice((Double) data.get(0)[6]);
        if (data.get(0)[7] != null)
            r.setMaxPrice((Double) data.get(0)[7]);
        r.setType((String) data.get(0)[8]);
        r.setPhone((String) data.get(0)[9]);
        r.setWeb((String) data.get(0)[10]);
        return r;
    }
}
