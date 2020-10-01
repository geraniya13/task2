package com.company;

import ru.pflb.mq.dummy.exception.DummyException;
import ru.pflb.mq.dummy.implementation.ConnectionImpl;
import ru.pflb.mq.dummy.interfaces.Connection;
import ru.pflb.mq.dummy.interfaces.Destination;
import ru.pflb.mq.dummy.interfaces.Producer;
import ru.pflb.mq.dummy.interfaces.Session;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws DummyException, InterruptedException, IOException {
        Connection connection = new ConnectionImpl();

        Session session = connection.createSession(true);

        Destination destination = session.createDestination("Queue");

        Producer producer = session.createProducer(destination);

        while(true) {
            BufferedReader reader = new BufferedReader(new FileReader(args[0]));
            while (reader.ready()) {
                producer.send(reader.readLine());
                Thread.sleep(2000);
            }
        }
    }
}
