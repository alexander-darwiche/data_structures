package datastructures;

// import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact implements Comparable<Contact> {
    private String lastName;
    private final String firstName;
    private final List<String> emailAddresses;

    /***
     * initialize a contact object for a person
     * @param lastName last name of the person
     * @param firstName first name of the person
     */
    public Contact(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.emailAddresses = new ArrayList<>();
    }



    /***
     * This method adds email to the emailAddresses list and avoids adding duplicate email addresses
     * @param email email to add
     */
    public void addEmail(String email) {
        if (!this.contains(email)){
            this.emailAddresses.add(email);
        }
    }

    /***
     * Remove the first instance of an email
     * @param email email to remove
     * @return true if email is removed, false is email is not found
     */
    public boolean removeEmail(String email) {
        for (String address : this.emailAddresses) {
            if (Objects.equals(address, email)) {
                this.emailAddresses.remove(email);
                return true;
            }
        }
        return false;
    }

    // Getter and Setter for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter
    public String getFirstName() {
        return firstName;
    }
    // public void setFirstName(String firstName) { this.firstName = firstName; }

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    /***
     * Method to merge emails into an existing contact
     * @param emailAddresses emails to merge in
     */
    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses.clear();
        if (emailAddresses != null) {
            for (String email : emailAddresses) {
                addEmail(email);
            }
        }
    }

    /***
     *
     * @param other the object to be compared.
     * @return if last names are not equal, return a positive number if Other is
     */
    @Override
    public int compareTo(Contact other) {
        int lastNameComp = this.lastName.compareTo(other.lastName);
        if (lastNameComp != 0) {
            return lastNameComp;
        }
        return this.firstName.compareTo(other.firstName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contact contact = (Contact) obj;
        return lastName.equals(contact.lastName) &&
                firstName.equals(contact.firstName);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", emailAddresses=" + emailAddresses +
                '}';
    }

    public boolean contains(String value){
        boolean flag = false;
        for (String address: this.emailAddresses){
            if (Objects.equals(address,value)){
                flag = true;
                break;
            }
        }
        return(flag);
    }
}

