package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String nickName;
    private String who;
    private String company;
    private String companyAddress;
    private String homePhone;
    private String mobilePhone;
    private String email;
    private String bday;
    private String bmonth;
    private String byear;
    private String group;
    private String address;
    private String persPhone;
    private String notes;

    public ContactData(String firstName, String middleName, String lastName, String nickName, String who, String company, String companyAddress, String homePhone, String mobilePhone, String email, String bday, String bmonth, String byear, String group, String address, String persPhone, String notes) {
        this.id = Integer.MAX_VALUE;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.who = who;
        this.company = company;
        this.companyAddress = companyAddress;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
        this.group = group;
        this.address = address;
        this.persPhone = persPhone;
        this.notes = notes;
    }

    public ContactData(int id, String firstName, String middleName, String lastName, String nickName, String who, String company, String companyAddress, String homePhone, String mobilePhone, String email, String bday, String bmonth, String byear, String group, String address, String persPhone, String notes) {
        this.id = id;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.who = who;
        this.company = company;
        this.companyAddress = companyAddress;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.email = email;
        this.bday = bday;
        this.bmonth = bmonth;
        this.byear = byear;
        this.group = group;
        this.address = address;
        this.persPhone = persPhone;
        this.notes = notes;
    }

    public ContactData(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getWho() {
        return who;
    }

    public String getCompany() {
        return company;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
    }

    public String getGroup() {
        return group;
    }

    public String getAddress() {
        return address;
    }

    public String getPersPhone() {
        return persPhone;
    }

    public String getNotes() {
        return notes;
    }

    public void setId(int id) {
        this.id = id;
    }
}
