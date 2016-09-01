package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
            @Column(name = "id")
    int id;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "firstname")
    private String firstname;
    @Expose
    @Column(name = "lastname")
    private String lastname;
    @Transient
    private String nickname;
    @Transient
    private String company;
    @Transient
    private String address;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Column(name ="work")
    @Type(type = "text")
    private String workPhone;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    private String email2;
    @Column(name = "email3")
    @Type(type = "text")
    private String email3;
    @Transient
    private String fullInfo;

    @Column(name = "photo")
    @Type(type = "text")
    private String photo = "";
    @Transient
    private String allEmails;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name ="address_in_groups" ,joinColumns = @JoinColumn(name="id"),inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();


    public String getAllEmails() {
        return allEmails;
    }




    public ContactData withFirstname(String firstname) {
        this.firstname = firstname;
        return this;
    }

    public ContactData withLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withCompany(String company) {
        this.company = company;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
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
    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }




    public ContactData(String firstname, String lastname, String nickname, String company, String address, String mobilePhone, String workPhone, String email, String group) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.company = company;
        this.address = address;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.email = email;

    }

    public ContactData() {
    }



    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    /*public ContactData(int id, String firstname, String lastname) {
          this.id = id;

          this.firstname = firstname;
          this.lastname = lastname;
        }
      */
    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() {
        return email;
    }



    public String getHomePhone() {
        return homePhone;
    }

    public ContactData withAllPhones(String phones) {
        this.allPhones = phones;
        return this;

    }

    public String getAllPhones() {
        return allPhones;
    }

    public ContactData withAllEmails(String emails) {
        this.allEmails = emails;
        return this;

    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public String getEmail2() {
        return email2;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public String getEmail3() {
        return email3;
    }

    public ContactData withFullInfo(String fullinfo) {
        this.fullInfo = fullinfo;
        return this;
    }

    public String getFullInfo() {
        return fullInfo;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData data = (ContactData) o;
        return id == data.id &&
                Objects.equals(firstname, data.firstname) &&
                Objects.equals(lastname, data.lastname) &&
                Objects.equals(mobilePhone, data.mobilePhone) &&
                Objects.equals(homePhone, data.homePhone) &&
                Objects.equals(email, data.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, mobilePhone, homePhone, email);
    }

    public File getPhoto() {
        if(!photo.isEmpty()){
        return new File(photo);}
        return null;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }
    public Groups getGroups(){
        return new Groups(groups);
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}

