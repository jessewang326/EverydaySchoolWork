public class MyFriends {
  public static void main(String args[]) {

    SocialNetwork contacts = new SocialNetwork();

    contacts.add("Snoopy","Dog","snoopy@uwo.ca");
    contacts.add("Felix","Cat","felix@uwo.ca");
    contacts.add("Mickey","Mouse","mickey@uwo.ca");
    
    if(contacts.remove("Snoopy", "Dog") == true);
    	System.out.println("Snoopy Dog was was removed successfully.");

    	
    if(contacts.remove("jiaxi", "wang") != true);
    System.out.println("jiaxi wang was not removed");
    
    System.out.println(contacts.toString());
    System.out.println("I have " + contacts.getNumFriends() + " friends in my list.");
  }
}
