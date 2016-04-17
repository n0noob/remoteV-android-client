package com.example.anoop.tcp_client;

/**
 * Created by Anoop on 4/6/2016.
 */
import android.provider.MediaStore;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;

import com.example.anoop.tcp_client.MediaFile;

class TCP_connection
{
    enum Status{
        connected, disconnected
    }

    String FromServer;
    String ToServer;
    String server_addr;
    int server_port;

    Status status = Status.disconnected;
    Socket clientSocket;
    BufferedReader inFromServer;
    PrintWriter outToServer;

/*    //This constructor is for testing purpose only
    public TCP_connection(){
        server_addr = "localhost";
        server_port = 5000;
    }
*/

    public TCP_connection(String ip_addr, int port){
        //IP ADDRESS : Case 1
        if(ip_addr.equals("localhost")){
            server_addr = ip_addr;
        }
        //IP ADDRESS : Case 2
        if(IP_checker.match_ip(ip_addr) == true){
            server_addr = ip_addr;
        }
        else {
            Log.i("TCP_connection", "Invalid server address : " + ip_addr);
        }
        //PORT
        if(port >= 1 && port <= 65535) {
            server_port = port;
        }
        else {
            Log.i("TCP_connection", "Invalid port : " + ip_addr);
        }
    }

    public void connect_now() throws Exception
    {
        clientSocket = new Socket(server_addr, server_port);

        outToServer = new PrintWriter(clientSocket.getOutputStream(),true);
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        status = Status.connected;
    }

    /*        while (true)
        {
            FromServer = inFromServer.readLine();

            System.out.println("RECIEVED:" + FromServer);
            System.out.println("SEND(Type Q or q to Quit):");

            if (ToServer.equals("Q") || ToServer.equals("q"))
            {
                outToServer.println (ToServer);
                clientSocket.close();
                break;
            }

            else
                outToServer.println(ToServer);
        }
*/
    public void write_line(String line){
        outToServer.println(line);
    }

    public LinkedList read_bulkMediaFile() throws IOException {
        if(status == Status.connected) {
            LinkedList<MediaFile> mfLL = new LinkedList<MediaFile>();
            MediaFile mf;

            FromServer = inFromServer.readLine();
            while (!FromServer.isEmpty()) {
                mf = new MediaFile();
                mf.extract_and_set(FromServer);
                mfLL.add(mf);
                FromServer = inFromServer.readLine();
            }
            return mfLL;
        }
        Log.i("TCP_connection","read_bulkMediaFile : Not connected to server");
        return null;
    }

    public MediaFile read_MediaFile() throws IOException {
        if(status == Status.connected) {
            MediaFile mf = new MediaFile(inFromServer.readLine());
            return mf;
        }
        Log.i("TCP_connection","read_MediaFile : Not connected to server");
        return null;
    }

    public String read_line() throws IOException {
        if(status == Status.connected) {
            return (inFromServer.readLine());
        }
        Log.i("TCP_connection","read_line : Not connected to server");
        return null;
    }

    public void close_connection() throws IOException {
        if(status == Status.connected){
            clientSocket.close();
        }
        Log.i("TCP_connection","close_connection : Not connected to server");
    }
}
