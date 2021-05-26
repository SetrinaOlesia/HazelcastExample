import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
        IMap<Integer, String> map = client.getMap("myMap");

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");

        System.out.println(LocalDateTime.now());
        System.out.println("Map size is: " + map.size());
        String value = map.remove(3);
        System.out.println("Map size is: " + map.size());

        System.out.println(new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss").
                format(map.getEntryView(4).getCreationTime()));

        IQueue<String> queue = client.getQueue("myQueue");
        queue.add(value);
        System.out.println("IQueue size is: " + queue.size());
        value = queue.poll();
        System.out.println("IQueue size is: " + queue.size() + ", " + "value is: " + value);

    }
}
