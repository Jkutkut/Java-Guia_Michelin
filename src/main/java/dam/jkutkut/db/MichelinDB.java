package dam.jkutkut.db;

import dam.jkutkut.exception.InvalidDataException;
import dam.jkutkut.exception.SQLiteQueryException;
import dam.jkutkut.michelin.model.Restaurant;

import java.util.ArrayList;

public class MichelinDB extends AccessDB {
    private static final String DB_LOCATION = "db/GUIA_MICHELIN.db";

    private static final String TABLE_NAME = "RESTAURANTES";
    private static final String COLUMN_ID = "NOMBRE";

    public MichelinDB() {
        super(DB_LOCATION);
    }

    public ArrayList<Restaurant> getRestaurants() {
        // SELECT * FROM TABLE WHERE COLUMN_ID = '%s'
        String query = String.format(
                "SELECT * FROM %s;",
                TABLE_NAME,
                COLUMN_ID
        );

        ArrayList<Object[]> data = SQLiteQuery.get(
                this,
                10,
                query
        );

        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Restaurant r;
        for (Object[] row : data) {
            r = new Restaurant(
                (String) row[0],
                (String) row[1],
                (String) row[2],
                (Integer) row[3],
                (String) row[4],
                (Double) row[5],
                (Double) row[6],
                (String) row[7],
                (String) row[8],
                (String) row[9]
            );
            restaurants.add(r);
        }
        return restaurants;
    }

    public void addRestaurant(Restaurant r) {
        String query = String.format(
                "INSERT INTO %s VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);",
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
            r.getMaxPrice(),
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
    }
}
