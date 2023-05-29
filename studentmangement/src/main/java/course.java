
public class course {
    protected int classId;
    protected String name;
    protected String lecture;
    protected int year;
    protected String notes;

    public course() {
    }

    public course(int id) {
        this.classId = id;
    }

    public course(String name, String lecture, int year, String notes) {
        this.name = name;
        this.lecture = lecture;
        this.year = year;
        this.notes = notes;
    }

    public course(int classId, String name, String lecture, int year, String notes) {
        this.classId = classId;
        this.lecture = lecture;
        this.name = name;
        this.year = year;
        this.notes = notes;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int id) {
        this.classId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public int getYear() {
        return year;
    }
    public int setYear() {
        return year;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}