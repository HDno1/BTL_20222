package dz.phamtuanvan.btl_2022.model;

public class Student {
    private int id;
    private String name;
    private String MSV;
    private String score;

    private String password;


    public Student(int id, String name, String score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Student(String password) {
        this.password = password;
    }

    public Student(String MSV, String score, String name) {
        this.MSV = MSV;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMSV() {
        return MSV;
    }

    public void setMSV(String MSV) {
        this.MSV = MSV;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
