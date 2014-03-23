package kafkaReactor;


import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import reactor.event.Event;
import reactor.function.Consumer;

@Service
class Receiver implements Consumer<Event<Data>> {

    @Autowired
    CountDownLatch latch;
    
    RestTemplate restTemplate = new RestTemplate();

    public void accept(Event<Data> ev) {
        System.out.println("time is mili :" + ev.getData().getMsg());
    }

}