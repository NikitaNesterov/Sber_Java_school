public enum CarModel {

    LADA,
    MERSEDES,
    BMW,
    AUDI,
    KIA,
    HONDA,
    FORD,
    CHEVROLET,
    FIAT,
    TOYOTA;


    public static CarModel generateModel(int i) {
        switch (i) {
            case 1:
                return LADA;

            case 2:
                return MERSEDES;

            case 3:
                return BMW;

            case 4:
                return AUDI;

            case 5:
                return KIA;

            case 6:
                return HONDA;

            case 7:
                return FORD;

            case 8:
                return CHEVROLET;

            case 9:
                return FIAT;

            default:
                return TOYOTA;
        }
    }
}
