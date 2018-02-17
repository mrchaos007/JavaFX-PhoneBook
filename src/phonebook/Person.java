
package phonebook;

import javafx.beans.property.SimpleStringProperty;

public class Person {
    
    private final SimpleStringProperty firtsName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty email;

    public Person() {
       this.firtsName = new SimpleStringProperty("");
       this.lastName = new SimpleStringProperty("");
       this.email = new SimpleStringProperty("");
    }

    public Person(String firtsName, String lastName, String email) {
        this.firtsName = new SimpleStringProperty(firtsName);
        this.lastName = new SimpleStringProperty(lastName);
        this.email = new SimpleStringProperty(email);
    }

    public SimpleStringProperty getFirtsName() {
        return firtsName;
    }

    public SimpleStringProperty getLastName() {
        return lastName;
    }

    public SimpleStringProperty getEmail() {
        return email;
    }
    
    
    
    
    
    
    
}
