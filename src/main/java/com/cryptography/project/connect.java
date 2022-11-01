import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

/* *
* Main Class
*/

public class connect {
public static void main(String[] args) throws IOException {
try {
    System.out.println(" am working1");
    ProcessBuilder builder = new ProcessBuilder("python3","/Users/hamzazaher/VSprojects/javaconncetiom/addRkey.py","1234qwerasdfzxcv","this is a test i");
    /*
     * to add argument the command will be "python3","/Users/hamzazaher/VSprojects/AESclone/test.py", "arg1", "arg2"
     */
    Process process = builder.start();

    BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream())) ;
    BufferedReader readers = new BufferedReader(new InputStreamReader(process.getErrorStream())) ;
    String lines = null;
    while ((lines=reader.readLine()) != null) {
        System.out.println("lines" + lines);
    }
    while ((lines=readers.readLine()) != null) {
        System.out.println("error lines" + lines);
    }


}catch(Exception e){
    e.printStackTrace();

}

}
    
}
