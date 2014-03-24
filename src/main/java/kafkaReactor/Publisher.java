package kafkaReactor;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.Reactor;
import reactor.event.Event;
import redis.clients.jedis.Jedis;

@Service
public class Publisher {
    
   @Autowired
   Reactor reactor;
    
   Jedis jedis = new Jedis("localhost");
   Random rnd = new Random();
   
   Map<Integer,String> addresses = new HashMap<Integer,String>();
   
    
    public Publisher()
    {

       InputStream ins = null; // raw byte-stream
        Reader r = null; // cooked reader
        BufferedReader br = null; // buffered for readLine()
        try {
            String s;
            ins = Publisher.class.getResourceAsStream("/US.txt");
            r = new InputStreamReader(ins, "UTF-8"); // leave charset out for default
            br = new BufferedReader(r);
            int i=0;
            while ((s = br.readLine()) != null) {
            	String[] parts = s.split("\t");
            	String address = parts[2]+","+parts[3]+","+parts[4]+","+parts[5]+","+parts[6]+","+parts[7]+","+parts[8];
            	String longlat = parts[9]+","+parts[10];
            	//System.out.println("entered : " +"ADDRESS:"+address);
            	addresses.put(i, "ADDRESS:"+address);
            	i++;
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage()); // handle exception
        }
        finally {
            if (br != null) { try { br.close(); } catch(Throwable t) { /* ensure close happens */ } }
            if (r != null) { try { r.close(); } catch(Throwable t) { /* ensure close happens */ } }
            if (ins != null) { try { ins.close(); } catch(Throwable t) { /* ensure close happens */ } }
        }
    }
    
    
    public void sendEvents() throws InterruptedException {
    	int x = addresses.size();
    	while(true) {
    		int i = rnd.nextInt(x);
    		String address = addresses.get(i);
            reactor.notify("messages", Event.wrap(new Data(System.currentTimeMillis()+"@"+address)));
        }

    }

}
