import com.phone.Phone;
import java.io.*;


public class Client
{
    public static void main(String[] args)
    {
        try(Phone phone =new Phone("localhost",8000))


        {
            System.out.println("Connected to server");
            String request="Irkutsk";
            System.out.println("Request: "+request);
            phone.writeLine(request);
            String response=phone.readLine();
            System.out.println("Response: "+response);


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}