public class Person {

    private final boolean man;
    private final String name;
    private Person spouse;

    public Person(boolean man, String name) {
        this.man = man;
        this.name = name;
    }

    /**
     * This method checks gender of persons. If genders are not equal - tries to marry.
     * If one of them has another spouse - execute divorce(sets spouse = null for husband and wife. Example: if both persons have spouses - then divorce will set 4 spouse to null) and then executes marry().
     *
     * @param person - new husband/wife for this person.
     * @return - returns true if this person has another gender than passed person and they are not husband and wife, false otherwise
     */
    public boolean marry(Person person) throws CloneNotSupportedException {
        if (person.man != Person.this.man && !person.equals(Person.this.spouse)) {
            if (Person.this.spouse != null) {
                Person.this.spouse.divorce();
                Person.this.divorce();
            } else {
                if (person.spouse != null) {
                    person.divorce();
                    person.spouse.divorce();
                }
                Person.this.spouse = person;
            }
            return true;
        } else return false;
    }

    /**
     * Sets spouse = null if spouse is not null
     *
     * @return true - if person status has been changed
     */
    public boolean divorce() {
        if (Person.this.spouse != null) {
            Person.this.spouse = null;
        } return true;
    }
}
