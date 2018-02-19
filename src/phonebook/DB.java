
package phonebook;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DB {
    //final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    final String URL = "jdbc:derby:sampleDB;create=true";
    final String USERNAME = "";
    final String PASSWORD = "";
    
    // Létrehozzuk a kapcsolatot (hidat)
    Connection conn = null;
    Statement createStatement = null;
    DatabaseMetaData dbmd = null;
    
    public DB() {
        // Megpróbáljuk életre kelteni
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("A híd létrejött");
        } catch (SQLException ex) {
            System.out.println("Valami baj van a connection (híd) létrehozásakor");
            System.out.println("" + ex);
        }
        
        // Ha életre kelt, csinálunk egy megpakolható teherautót
        if(conn != null) {
            try {
                createStatement = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Valami baj van a createStatement (teherautó) létrehozásakor.");
                System.out.println("" + ex);
            }
        }
        
        // Megnézzük, hogy üres-e az adatbázis. Megnézzük, létezik-e az adott adattábla.
        try {
            dbmd = conn.getMetaData();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a DatabaseMetaData (adatbázis leírása) létrehozásakor.");
            System.out.println("" + ex);
        }
        
        try {
            ResultSet rs = dbmd.getTables(null, "APP", "CONTACTS", null);
            if(!rs.next()) {
                createStatement.execute("create table contacts(id INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), lastname varchar(20), firstname varchar(20), email varchar(30))");
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van az adattáblák létrehozásakor.");
            System.out.println("" + ex);
        }
    }
    
    public ArrayList<Person> getAllContacts() {
        String sql = "select * from contacts";
        ArrayList<Person> contacts = null;
        try {
            ResultSet rs = createStatement.executeQuery(sql);
            contacts = new ArrayList<>();
            while(rs.next()) {
                System.out.println(" " + rs.getInt("id") + " " + rs.getString("lastname") + " " + rs.getString("firstname") + " " + rs.getString("email"));
                Person actualPerson = new Person(rs.getInt("id"), rs.getString("lastname"), rs.getString("firstname"), rs.getString("email"));
                contacts.add(actualPerson);
            }
        } catch (SQLException ex) {
            System.out.println("Valami baj van a contactok kiolvasásakor.");
            System.out.println("" + ex);  
        }    
        
        return contacts;
    }
    
    public void addContact(Person person) {
        try {
            String sql = "insert into contacts (lastname, firstname, email) values(?, ?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a contact hozzáadásakor.");
            System.out.println("" + ex);
        }
    }
    
    public void updateContact(Person person) {
        try {
            String sql = "update contacts set lastname = ?, firstname = ?, email = ? where id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, person.getLastName());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, Integer.parseInt(person.getId()));
            preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a contact hozzáadásakor.");
            System.out.println("" + ex);
        }
    }
    
    public void removeContact(Person person) {
        try {
        String sql = "delete from contacts where id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, Integer.parseInt(person.getId()));
        preparedStatement.execute();
        } catch (SQLException ex) {
            System.out.println("Valami baj van a contact törlésekor.");
            System.out.println("" + ex);
        }
    }
}
