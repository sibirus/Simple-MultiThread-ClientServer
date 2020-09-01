package com.phone;



import java.io.*;
import java.net.*;
///////////////////////////////////////////////////////
/*Основная задача данного класса это автозакрытие, т.е данный класс
* может использоваться в качестве try with resources */
///////////////////////////////////////////////////////


public class Phone implements Closeable
{
    private final Socket socket;
    private final BufferedReader reader;
    private final BufferedWriter writer;

    public Phone(String ip,int port)

        /*Конструктор клиента*/
    {
        try
        {
            this.socket = new Socket(ip, port);
            this.reader = createReader();
            this.writer = createWriter();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }


    }
    public Phone(ServerSocket server)

        /*Конструктор сервера*/
    {
        try
        {
            this.socket = server.accept();
            this.reader = createReader();
            this.writer = createWriter();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public void writeLine(String message)
    {

        try
        {
            writer.newLine();
            writer.write(message);
            writer.flush();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    public String readLine()
    {
        try
        {
            return reader.readLine();

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }

    private BufferedReader createReader() throws IOException
    {

            return new BufferedReader(new InputStreamReader(socket.getInputStream()));

    }

    private BufferedWriter createWriter() throws IOException {

            return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

    }

    @Override
    public void close() throws IOException {
        writer.close();
        reader.close();
        socket.close();
    }
}
