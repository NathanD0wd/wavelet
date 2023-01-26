import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    // The one bit of state on the server: a list that will be manipulated by
    // various requests.
    String list = "";

    public String handleRequest(URI url) {
        if (url.getPath().equals("/add-message")) {
            String[] params = url.getQuery().split("=");
            if ( params[0].equals("s") ){
                list += params[1] + "\n";
                return list;
            }
        } 
        return "404 Not Found!";
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
