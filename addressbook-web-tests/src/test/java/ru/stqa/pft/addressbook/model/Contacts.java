package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by eshkuratova on 17.08.2016.
 */
public class Contacts extends ForwardingSet<ContactData> {

    public Set<ContactData> delegate;

    public Contacts(Contacts datas) {

        this.delegate = new HashSet<ContactData>(datas.delegate); //переложим set datas в новый set
    }

    public Contacts() {
        this.delegate = new HashSet<>();
    }

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }


    public Contacts withAdded(ContactData contact){

        Contacts contacts = new Contacts(this);
        contacts.add(contact);
        return contacts;

    }
    public Contacts without(ContactData contact){

        Contacts contacts = new Contacts(this);
        contacts.remove(contact);
        return contacts;

    }

}
