package com.lds.syncpc.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.lds.syncpc.transfer.protocol.FileTransferProtocol;

public class SyncClient {
	  private String host = "192.168.1.100";
	  private int port = 2012;
	  
	  public SyncClient() {
	  }
	  
	  public SyncClient(String host, int port) {
		  this.host = host;
		  this.port = port;
	  }
	  
	  public void sync() throws IOException  {
		    Socket sock = new Socket(host, port);
		    System.out.println("Connecting...");

		    // receive file
		    InputStream is = sock.getInputStream();
		    FileTransferProtocol.readInputStreamToFiles(is, "/sdcard");
		    
		    sock.close();
	  }
		
	  public static void main (String [] args ) throws IOException {
		  SyncClient client = new SyncClient();
		  client.sync();
	  }

}
