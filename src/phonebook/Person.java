
package phonebook;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;
    private final SimpleStringProperty id;

    public Person() {
       this.firstName = new SimpleStringProperty("");
       this.lastName = new SimpleStringProperty("");
       this.email = new SimpleStringProperty("");
       this.id = new SimpleStringProperty("");
    }

    public Person(String lastName, String firstName, String email) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
        this.id = new SimpleStringProperty("");
    }

    public String getFirstName() {
        return firstName.get();
    }
    
    void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }
    
    void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getEmail() {
        return email.get();
    }
    
    void setEmail(String email) {
        this.email.set(email);
    }
    
    public String getId() {
        return email.get();
    }
    
    void setId(String id) {
        this.id.set(id);
    }
    
}
