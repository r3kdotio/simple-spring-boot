package io.r3k.k8s.springboot;

import com.google.common.collect.EvictingQueue;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.boot.actuate.trace.InMemoryTraceRepository;
import org.springframework.boot.actuate.trace.Trace;

public class ZuulInMemoryTraceRepository extends InMemoryTraceRepository {

    private final EvictingQueue fifo = EvictingQueue.create(10000);

    public ZuulInMemoryTraceRepository(int maxSize) {
        setCapacity(maxSize);
    }

    public Collection<DateTimeTaken> getStats() {
        return fifo;
    }

    public void setReverse(boolean reverse) {
        super.setReverse(reverse);
    }

    @Override
    public void setCapacity(int capacity) {
        super.setCapacity(capacity);
    }

    @Override
    public List<Trace> findAll() {
        return super.findAll();
    }

    @Override
    public void add(Map<String, Object> map) {
        super.add(map);
        Object method = map.get("path");
        //I don't care about the time taken for health and info and even my own stats service
        if (("health".equals(method)) || ("info".equals(method)) || ("stats".equals(method))) {
            return;
        }
        Object timeTaken = map.get("timeTaken");
        if (timeTaken != null) {
            synchronized (fifo) {
                fifo.add(new DateTimeTaken(LocalDateTime.now(), timeTaken.toString()));
            }
        }
    }
}
