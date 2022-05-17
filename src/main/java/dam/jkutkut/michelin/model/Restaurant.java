package dam.jkutkut.michelin.model;

import dam.jkutkut.exception.InvalidDataException;

public class Restaurant {
    public static final String[] REGIONS = {
        "Andalucía",
        "Aragón",
        "Asturias",
        "Islas Baleares",
        "Canarias",
        "Cantabria",
        "Castilla - La Mancha",
        "Castilla y León",
        "Cataluña",
        "Ceuta",
        "Ciudad Real",
        "Comunidad Valenciana",
        "Extremadura",
        "Galicia",
        "La Rioja",
        "Madrid",
        "Murcia",
        "Navarra",
        "País Vasco",
        "Valencia",
        "Todas"
    };

    public static final String[] TYPES = {
        "Creativa",
        "Moderna",
        "Tradicional",
        "Regional",
        "Fusión"
    };

    public static final String[] DISTINCTIONS = {
        "1 star",
        "2 stars",
        "3 stars",
        "All stars"
    };

    public static final int MIN_DISTINCTION = 1;
    public static final int MAX_DISTINCTION = 3;

    private static final double NULL_PRICE = -1;

    private static final String PHONE_REGEX = "^(\\+\\d{2,3})? ?\\d{3} ?(\\d{3} ?\\d{3}|\\d{2} ?\\d{2} ?\\d{2})$";
    private static final String WEB_REGEX = "(http[s]?:\\/\\/)?(ww[w2]\\.)?(([a-zA-Z0-9\\-]+\\.)+([a-zA-Z\\-])+)";

    private static final String INVALID_NAME_MESSAGE = "Invalid name";
    private static final String INVALID_REGION_MESSAGE = "Invalid region";
    private static final String INVALID_CITY_MESSAGE = "Invalid city";
    private static final String INVALID_TYPE_MESSAGE = "Invalid kitchen type";
    private static final String INVALID_DISTINCTION_MESSAGE = "Invalid distinction: value must be in range [" + MIN_DISTINCTION + ", " + MAX_DISTINCTION + "]";
    private static final String INVALID_ADDRESS_MESSAGE = "Invalid address";
    private static final String INVALID_MIN_PRICE_MESSAGE = "Invalid min price";
    private static final String INVALID_MAX_PRICE_MESSAGE = "Invalid max price";
    private static final String INVALID_PRICE_RANGE_MESSAGE = "Minimum price must be less than maximum price";
    private static final String INVALID_PHONE_MESSAGE = "Invalid phone";
    private static final String INVALID_WEB_MESSAGE = "Invalid web";

    private String name;
    private String region;
    private String city;
    private int distinction;
    private String address;
    private double minPrice;
    private double maxPrice;
    private String type;
    private String phone;
    private String web;

    public Restaurant() {
        this.name = null;
        this.region = null;
        this.city = null;
        this.type = null;
        this.distinction = 0;
        this.address = null;
        this.minPrice = NULL_PRICE;
        this.maxPrice = NULL_PRICE;
        this.phone = null;
        this.web = null;
    }

    public Restaurant(String name, String region, String city, int distinction, String address, double minPrice, double maxPrice, String type, String phone, String web) {
        this.setName(name);
        this.setRegion(region);
        this.setCity(city);
        this.setDistinction(distinction);
        this.setAddress(address);
        this.minPrice = NULL_PRICE;
        this.maxPrice = NULL_PRICE;
        this.setMinPrice(minPrice);
        this.setMaxPrice(maxPrice);
        this.setType(type);
        this.setPhone(phone);
        this.setWeb(web);
    }

    // GETTERS
    public void validate() throws InvalidDataException {
        if (!isValidName(this.name))
            throw new InvalidDataException(INVALID_NAME_MESSAGE);
        if (!isValidRegion(this.region))
            throw new InvalidDataException(INVALID_REGION_MESSAGE);
        if (!isValidCity(this.city))
            throw new InvalidDataException(INVALID_CITY_MESSAGE);
        if (!isValidDistinction(this.distinction))
            throw new InvalidDataException(INVALID_DISTINCTION_MESSAGE);
        if (!isValidAddress(this.address))
            throw new InvalidDataException(INVALID_ADDRESS_MESSAGE);
        if (!isValidMinPrice(this.minPrice, this.maxPrice))
            throw new InvalidDataException(INVALID_MIN_PRICE_MESSAGE);
        if (!isValidMaxPrice(this.maxPrice, this.minPrice))
            throw new InvalidDataException(INVALID_PRICE_RANGE_MESSAGE);
        if (!isValidType(this.type))
            throw new InvalidDataException(INVALID_TYPE_MESSAGE);
        if (!isValidPhone(this.phone))
            throw new InvalidDataException(INVALID_PHONE_MESSAGE);
        if (!isValidWeb(this.web))
            throw new InvalidDataException(INVALID_WEB_MESSAGE);
    }

    public String getName() {
        return this.name;
    }

    public String getRegion() {
        return this.region;
    }

    public String getCity() {
        return this.city;
    }

    public int getDistinction() {
        return this.distinction;
    }

    public String getAddress() {
        return this.address;
    }

    public double getMinPrice() {
        return this.minPrice;
    }

    public double getMaxPrice() {
        return this.maxPrice;
    }

    public String getType() {
        return this.type;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getWeb() {
        return this.web;
    }

    public String[] toArray() {
        String distin = "";
        for (int i = 0; i < this.distinction; i++)
            distin += "*";
        String price;
        if (this.maxPrice == NULL_PRICE) {
            if (this.minPrice == NULL_PRICE)
                price = "??";
            else
                price = String.format("%.2f€", this.minPrice);
        }
        else
            price = String.format("%.2f€ - %.2f€", this.minPrice, this.maxPrice);

        return new String[] {
            this.getName(),
            this.getRegion(),
            this.getCity(),
            distin,
            this.getAddress(),
            price,
            this.getType(),
            this.getPhone(),
            this.getWeb()
        };
    }

    // SETTERS
    public void setName(String name) {
        if (!isValidName(name))
            throw new InvalidDataException(INVALID_NAME_MESSAGE);
        this.name = name;
    }

    public void setRegion(String region) {
        if (!isValidRegion(region))
            throw new InvalidDataException(INVALID_REGION_MESSAGE);
        this.region = region;
    }

    public void setCity(String city) {
        if (!isValidCity(city))
            throw new InvalidDataException(INVALID_CITY_MESSAGE);
        this.city = city;
    }

    public void setDistinction(int distinction) {
        if (!isValidDistinction(distinction))
            throw new InvalidDataException(INVALID_DISTINCTION_MESSAGE);
        this.distinction = distinction;
    }

    public void setAddress(String address) {
        if (!isValidAddress(address))
            throw new InvalidDataException(INVALID_ADDRESS_MESSAGE);
        this.address = address;
    }

    public void setMinPrice(double minPrice) {
        if (!isValidMinPrice(minPrice, this.maxPrice)) {
            if (this.maxPrice != NULL_PRICE)
                throw new InvalidDataException(INVALID_PRICE_RANGE_MESSAGE);
            throw new InvalidDataException(INVALID_MIN_PRICE_MESSAGE);
        }
        this.minPrice = minPrice;
    }

    public void setMaxPrice(double maxPrice) {
        if (!isValidMaxPrice(maxPrice, this.minPrice)) {
            if (this.minPrice != NULL_PRICE)
                throw new InvalidDataException(INVALID_PRICE_RANGE_MESSAGE);
            throw new InvalidDataException(INVALID_MAX_PRICE_MESSAGE);
        }
        this.maxPrice = maxPrice;
    }

    public void setType(String type) {
        if (!isValidType(type))
            throw new InvalidDataException(INVALID_TYPE_MESSAGE);
        this.type = type;
    }

    public void setPhone(String phone) {
        if (!isValidPhone(phone))
            throw new InvalidDataException(INVALID_PHONE_MESSAGE);
        this.phone = phone;
    }

    public void setWeb(String web) {
        if (!isValidWeb(web))
            throw new InvalidDataException(INVALID_WEB_MESSAGE);
        this.web = web;
    }

    // Checkers
    public static boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    public static boolean isValidRegion(String region) {
        if (region == null || region.isBlank())
            return false;
        for (int i = 0; i < REGIONS.length - 1; i++)
            if (REGIONS[i].equals(region))
                return true;
        return false;
    }

    public static boolean isValidCity(String city) {
        return city != null && !city.isBlank();
    }

    public static boolean isValidDistinction(int distinction) {
        return distinction >= MIN_DISTINCTION && distinction <= MAX_DISTINCTION;
    }

    public static boolean isValidAddress(String address) {
        return address != null && !address.isBlank();
    }

    public static boolean isValidMinPrice(double minPrice, double maxPrice) {
        if (maxPrice != NULL_PRICE)
            return minPrice <= maxPrice && minPrice > 0;
        return minPrice > 0;
    }

    public static boolean isValidMaxPrice(double maxPrice, double minPrice) {
        if (maxPrice != NULL_PRICE)
            return maxPrice >= minPrice;
        return true;
    }

    public static boolean isValidType(String type) {
        if (type == null || type.isBlank())
            return false;
        for (String t : TYPES)
            if (t.equals(type))
                return true;
        return false;
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches(PHONE_REGEX);
    }

    public static boolean isValidWeb(String web) {
        return web != null && web.matches(WEB_REGEX);
    }
}
