import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        String uname="root";
        String url="jdbc:mysql://localhost:3306/AddressBook";
        String password="Ceramida";
        String query="SELECT * FROM Contacts";
                
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
    
        Connection con=DriverManager.getConnection(url,uname,password);
        Statement statement=con.createStatement();
                
        boolean checkTrue = true;
      
        System.out.println("Hello and welcome to your address book!");
        System.out.println("What would you like to do?\ncheck current contacts,add a contact,delete a contact\n1.add\n2.check\n3.delete\n4.exit");
    
        while(checkTrue==true){
        
        Scanner s = new Scanner(System.in);
        String choice=s.nextLine();

        if(choice.equals("add")){
            System.out.println("please input the necessary information: \nFirst name: ");
            String firstName=s.nextLine();
            System.out.println("Last name: ");
            String lastName=s.nextLine();
            System.out.println("phone number: ");
            String phoneNum=s.nextLine();
            System.out.println("address: ");
            String address=s.nextLine();
            System.out.println("email: ");
            String email=s.nextLine();
            Contact contact=new Contact(firstName,lastName,phoneNum, address, email);
            System.out.println("The contact info: "+contact.getFirstName()+" "+contact.getLastName()+" "+contact.getPhoneNum()+" "+contact.getAddress()+" "+contact.getEmail());
            String addContact="INSERT INTO contacts (first_name,last_name,phone_number,address,email) VALUES ('"+firstName+"','"+lastName+"','"+phoneNum+"','"+address+"','"+email+"')";       
            statement.executeUpdate(addContact);
        }

        if(choice.equals("check")){
            ResultSet result=statement.executeQuery(query);
            String contactData="";
            while(result.next()){
                for(int i = 1;i<=5;i++){
                    contactData += result.getString(i) + "   \n";
                }
            }
            System.out.println(contactData);
            
        } 
        
        if(choice.equals("delete")){
            System.out.println("insert the first name of the person which you want to delete: ");
            String delFirstName = s.nextLine();
            System.out.println("Now insert the last name of that person: ");
            String delLastName = s.nextLine();
            String delContact="Delete from contacts where first_name='"+delFirstName+"'AND last_name='"+delLastName+"'";
            statement.executeUpdate(delContact);
        }
        
        if(choice.equals("exit")){
            checkTrue=false;
            s.close();
        }
       
        System.out.println("what would you like to do now?");

    }
}
}
