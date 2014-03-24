package kafkaReactor;

import java.util.Date;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.springframework.stereotype.Service;

import reactor.event.Event;
import reactor.function.Consumer;


@Service
class Receiver implements Consumer<Event<Data>> {

	
	public static final String TOPIC_NAME = "page_visits";
	Producer<String, String> producer = null;
	
	int counerOfEvents = 0;
	
	public Receiver()
	{
		    Properties props = new Properties();
	        props.put("metadata.broker.list", "127.0.0.1:9092,127.0.0.1:9092");
		    props.put("zk.connect", "127.0.0.1:2181");
		    props.put("serializer.class", "kafka.serializer.StringEncoder");
	 
	        ProducerConfig config = new ProducerConfig(props);
	 
	        producer = new Producer<String, String>(config);
	}
	
    public void accept(Event<Data> ev) {
      //  System.out.println("time is mili :" + ev.getData().getMsg());
        KeyedMessage<String, String> data = new KeyedMessage<String, String>(TOPIC_NAME, ev.getData().getMsg());
        producer.send(data);
        //increaseCounert();
       // producer.close();
    }
    
    public synchronized int get()
    {
    	return counerOfEvents++;
     }

}