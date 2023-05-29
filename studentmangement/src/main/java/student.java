

/**
 * student.java
 * This is a model class represents a student entity
 * 
 * @author www.codejava.net
 *
 */
public class student {
    protected int id;
    protected String name;
    protected String address;
    protected String birthday;
    protected String grade;
    protected String notes;

    public student() {
    }

    public student(int id) {
        this.id = id;
    }

    public student(String name, String grade, String birthday, String address, String notes) {
        this.name = name;
        this.grade = grade;
        this.birthday = birthday;
        this.address = address;
        this.notes = notes;
    }

    public student(int id, String name, String grade, String birthday, String address, String notes) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.birthday = birthday;
        this.address = address;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrades(String grade) {
        this.grade = grade;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setAuthor(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}