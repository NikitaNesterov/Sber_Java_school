public enum CarType {

    SEDAN,
    UNIVERSAL,
    COUPE,
    HATCHBACK,
    SUV,
    MINIVAN,
    PICKUP;


    public static CarType generateType(int i) {
        switch (i) {
            case 1:
                return SEDAN;

            case 2:
                return UNIVERSAL;

            case 3:
                return COUPE;

            case 4:
                return HATCHBACK;

            case 5:
                return SUV;

            case 6:
                return MINIVAN;

            case 7:
                return PICKUP;

            default:
                return PICKUP;
        }
    }
}
