package dam.jkutkut.michelin.model;

import dam.jkutkut.exception.InvalidDataException;

public class Restaurant {
    public static final String[] REGIONS = {
        "Andalucia",
        "Aragon",
        "Asturias",
        "Baleares",
        "Canarias",
        "Cantabria",
        "Castilla-La-Mancha",
        "Castilla-y-Leon",
        "Cataluna",
        "Ceuta",
        "Ciudad-Real",
        "Comunidad-Valenciana",
        "Extremadura",
        "Galicia",
        "La-Rioja",
        "Madrid",
        "Murcia",
        "Navarra",
        "Pais-Vasco",
        "Valencia"
    };

    public static final String[] TYPES = {
        "Creative",
        "Modern",
        "Traditional",
        "Regional",
        "Fusion"
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

    private static final String PHONE_REGEX = "^(\\+\\d{2,3})? ?\\d{3} ?\\d{3} ?\\d{3}$";
    private static final String WEB_REGEX = "(http[s]?:\\/\\/)?(ww[w2]\\.)?(([a-zA-Z0-9\\-]+\\.)+([a-zA-Z\\-])+)";

    private String name;
    private String region;
    private String type;
    private int distinction;
    private double minPrice;
    private double maxPrice;
    private String phone;
    private String web;

    public Restaurant() {
        this.name = null;
        this.region = null;
        this.type = null;
        this.distinction = 0;
        this.minPrice = NULL_PRICE;
        this.maxPrice = NULL_PRICE;
        this.phone = null;
        this.web = null;
    }

    public Restaurant(String name, String region, String type, int distinction, double minPrice, double maxPrice, String phone, String web) {
        this.setName(name);
        this.setRegion(region);
        this.setType(type);
        this.setDistinction(distinction);
        this.setMinPrice(minPrice);
        this.setMaxPrice(maxPrice);
        this.setPhone(phone);
        this.setWeb(web);

    }

    // GETTERS
    public void validate() throws InvalidDataException {
        if (!isValidName(this.name))
            throw new InvalidDataException("Invalid name");
        if (!isValidRegion(this.region))
            throw new InvalidDataException("Invalid region");
        if (!isValidType(this.type))
            throw new InvalidDataException("Invalid type");
        if (!isValidDistinction(this.distinction))
            throw new InvalidDataException("Invalid distinction");
        if (!isValidMinPrice(this.minPrice, this.maxPrice))
            throw new InvalidDataException("Invalid min price");
        if (!isValidMaxPrice(this.maxPrice, this.minPrice))
            throw new InvalidDataException("Invalid max price");
        if (!isValidPhone(this.phone))
            throw new InvalidDataException("Invalid phone");
        if (!isValidWeb(this.web))
            throw new InvalidDataException("Invalid web");
    }

    // SETTERS
    public void setName(String name) {
        if (!isValidName(name))
            throw new InvalidDataException("Invalid name");
        this.name = name;
    }

    public void setRegion(String region) {
        if (!isValidRegion(region))
            throw new InvalidDataException("Invalid region");
        this.region = region;
    }

    public void setType(String type) {
        if (!isValidType(type))
            throw new InvalidDataException("Invalid type");
        this.type = type;
    }

    public void setDistinction(int distinction) {
        if (!isValidDistinction(distinction))
            throw new InvalidDataException("Invalid distinction: value must be in range", MIN_DISTINCTION, MAX_DISTINCTION);
        this.distinction = distinction;
    }

    public void setMinPrice(double minPrice) {
        if (!isValidMinPrice(minPrice, this.maxPrice))
            throw new InvalidDataException("Invalid minimum price");
        this.minPrice = minPrice;
    }

    public void setMaxPrice(double maxPrice) {
        if (!isValidMaxPrice(maxPrice, this.minPrice))
            throw new InvalidDataException("Invalid maximum price");
        this.maxPrice = maxPrice;
    }

    public void setPhone(String phone) {
        if (!isValidPhone(phone))
            throw new InvalidDataException("Invalid phone");
        this.phone = phone;
    }

    public void setWeb(String web) {
        if (!isValidWeb(web))
            throw new InvalidDataException("Invalid web");
        this.web = web;
    }

    // Checkers
    public static boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    public static boolean isValidRegion(String region) {
        if (region == null || region.isBlank())
            return false;
        for (String r : REGIONS)
            if (r.equals(region))
                return true;
        return false;
    }

    public static boolean isValidType(String type) {
        if (type == null || type.isBlank())
            return false;
        for (String t : TYPES)
            if (t.equals(type))
                return true;
        return false;
    }

    public static boolean isValidDistinction(int distinction) {
        return distinction >= MIN_DISTINCTION && distinction <= MAX_DISTINCTION;
    }

    public static boolean isValidMinPrice(double minPrice, double maxPrice) {
        if (maxPrice != NULL_PRICE)
            return minPrice <= maxPrice && minPrice > 0;
        return minPrice > 0;
    }

    public static boolean isValidMaxPrice(double maxPrice, double minPrice) {
        if (minPrice != NULL_PRICE)
            return maxPrice >= minPrice && maxPrice > 0;
        return maxPrice > 0;
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches(PHONE_REGEX);
    }

    public static boolean isValidWeb(String web) {
        return web != null && web.matches(WEB_REGEX);
    }
}
