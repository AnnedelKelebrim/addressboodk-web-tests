package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.Objects;

@XStreamAlias(value = "contacts")//этот заголовок заменяем на указанный"contacts"
@Entity
@Table(name = "addressbook")
public class ContactData {
    @XStreamOmitField//ОЗНАЧАЕТ, ЧТО ЭТО ПОЛЕ НЕ НАДО ПИСАТЬ В ХМЛ
    @Id
    private int id = Integer.MAX_VALUE;
    @Expose
    private String firstName;
    @Expose
    private String middleName;
    @Expose
    private String lastName;
    @Expose
    private String nickName;
    @Expose
    @Column(name = "title")
    private String who;
    @Expose
    private String company;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String firstAddress;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Expose
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String firstEmail;
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String secondEmail;
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String thirdEmail;
    @Expose
    @Transient
    private String allEmails;
    @Expose
    @Type(type = "byte")
    private String bday;
    @Expose
    private String bmonth;
    @Expose
    private String byear;
    @Expose
    @Transient
    private String group;
    @Expose
    @Column(name = "address2")
    @Type(type = "text")
    private String secondAddress;
    @Expose
    @Type(type = "text")
    private String notes;
    @Expose
    @Type(type = "text")
    private String photo;


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

    public String getFirstAddress() {
        return firstAddress;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getFirstEmail() {
        return firstEmail;
    }

    public String getSecondEmail() {
        return secondEmail;
    }

    public String getThirdEmail() {
        return thirdEmail;
    }

    public String getAllEmails() {
        return allEmails;
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

    public String getSecondAddress() {
        return secondAddress;
    }

    public String getNotes() {
        return notes;
    }

    public File getPhoto() {
        return new File(photo);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withWho(String who) {
        this.who = who;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withFirstAddress(String address) {
        this.firstAddress = address;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withFirstEmail(String email) {
        this.firstEmail = email;
        return this;
    }

    public ContactData withSecondEmail(String email) {
        this.secondEmail = email;
        return this;
    }

    public ContactData withThirdEmail(String email) {
        this.thirdEmail = email;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withBday(String bday) {
        this.bday = bday.;
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withSecondAddress(String address) {
        this.secondAddress = address;
        return this;
    }

    public ContactData withNotes(String notes) {
        this.notes = notes;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}
