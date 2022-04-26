package dz.phamtuanvan.btl_2022.model;

public class StudentAccount {
    private  int id;
    private String MSV;
    private String name;
    private String sex;
    private String password;

    public StudentAccount(int id, String MSV, String name, String sex, String password) {
        this.id = id;
        this.MSV = MSV;
        this.name = name;
        this.sex = sex;
        this.password = password;
    }

    public StudentAccount(int id, String MSV) {
        this.id = id;
        this.MSV = MSV;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
