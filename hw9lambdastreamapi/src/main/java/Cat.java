public class Cat{

    private Integer age;
    private CatColor color;

    public Cat(Integer age, CatColor color) {
        this.age = age;
        this.color = color;
    }

    public Cat() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public CatColor getColor() {
        return color;
    }

    public void setColor(CatColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "age=" + age +
                ", color=" + color +
                '}';
    }
}
