package kafkaReactor;


import org.springframework.stereotype.Service;

import reactor.event.Event;
import reactor.function.Consumer;

@Service
class Receiver implements Consumer<Event<Data>> {


    public void accept(Event<Data> ev) {
        System.out.println("time is mili :" + ev.getData().getMsg());
    }

}