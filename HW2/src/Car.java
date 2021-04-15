public class Car {


    private CarModel model;
    private CarType type;

    public Car(CarModel model, CarType type) {
        this.model = model;
        this.type = type;
    }

    public Car() {

    }

    public CarModel getModel() {
        return model;
    }

    public void setModel(CarModel model) {
        this.model = model;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public static Car carGeneration() {
        int modelNumber = (int) (Math.random() * 10);
        int typeNumber = (int) (Math.random() * 7);
        return new Car(CarModel.generateModel(modelNumber), CarType.generateType(typeNumber));
    }

    @Override
    public String toString() {
        return "Car{" +
                "model=" + model +
                ", type=" + type +
                '}';
    }
}
