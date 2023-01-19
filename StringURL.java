import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    // The one bit of state on the server: a list that will be manipulated by
    // various requests.
    ArrayList<String> list = new ArrayList<String>();

    public String handleRequest(URI url) {
        String output = "";
        if (url.getPath().equals("/")) {
            output += "Nathan's list:\n";
            for ( int i = 0 ; i < list.size() ; i++ ){
                output += list.get(i) + "\n";
            }
            return output;
        } else if (url.getPath().equals("/add")) {
            list.add( url.getQuery());
            return url.getQuery() + " added";
        } else if ( url.getPath().equals("/search") ){
            output += "List of words matching your search:\n";
            for ( int i = 0 ; i < list.size() ; i++ ){
                if ( list.get(i).contains( url.getQuery() ) ){
                    output += list.get(i) + ", ";
                }
            }
            return output;
        }
        return "404 Not Found!";
    }
}

class StringURL {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
