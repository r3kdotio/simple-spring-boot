package io.r3k.k8s.springboot;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatsRestController {

    @Autowired
    ZuulInMemoryTraceRepository zuulInMemoryTraceRepository;

    @RequestMapping("/stats")
    public Collection<DateTimeTaken> getAll() {
        return zuulInMemoryTraceRepository.getStats();
    }
}
