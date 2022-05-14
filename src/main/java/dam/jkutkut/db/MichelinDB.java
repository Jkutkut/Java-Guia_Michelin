package dam.jkutkut.db;

import dam.jkutkut.exception.InvalidDataException;
import dam.jkutkut.exception.SQLiteQueryException;
import dam.jkutkut.michelin.model.Restaurant;

import java.util.ArrayList;

public class MichelinDB extends AccessDB {
    private static final String DB_LOCATION = "db/GUIA_MICHELIN.db";

    private static final String TABLE_NAME = "RESTAURANTES";
    private static final String COLUMN_ID = "RESTAURANTES";

    public MichelinDB() {
        super(DB_LOCATION);
    }

    public ArrayList<Restaurant> getRestaurants() {
        // SELECT * FROM TABLE WHERE COLUMN_ID = '%s'
        String query = String.format(
                "SELECT * FROM %s WHERE %s = ?;",
                TABLE_NAME,
                COLUMN_ID
        );

//        String errorReason = INVALID_USERNAME_OR_PASSWORD;
//
//        ArrayList<Object[]> data = SQLiteQuery.get(
//                this,
//                1,
//                query,
//                username
//        );
//
//        if (data.size() == 0)
//            throw new InvalidDataException(errorReason);
//
//        String passwd = (String) data.get(0)[0];
//
//        if (!passwd.equals(password))
//            throw new InvalidDataException(errorReason);
        return null;
    }

    public void addRestaurant(Restaurant r) {
        String query = String.format(
                "INSERT INTO %s VALUES (?, ?);",
                TABLE_NAME
        );

        int result = SQLiteQuery.execute(this, query /* TODO */);

        System.out.printf(
                "Added restaurant %s into table %s. Result status: %d\n",
                r.getName(),
                TABLE_NAME,
                result
        );
    }
}
