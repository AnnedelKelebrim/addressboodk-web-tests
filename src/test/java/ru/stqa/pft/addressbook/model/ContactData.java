package ru.stqa.pft.addressbook.model;

public class ContactData {
    private int id;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String who;
    private final String company;
    private final String companyAddress;
    private final String homePhone;
    private final String mobilePhone;
    private final String email;
    private final String bday;
    private final String bmonth;
    private final String byear;
    private final String group;
    private final String address;
    private final String persPhone;
    private final String notes;

    public ContactData(String firstName, String middleName, String lastName, String nickName, String who, String company, String companyAddress, String homePhone, String mobilePhone, String email, String bday, String bmonth, String byear, String group, String address, String persPhone, String notes) {
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
