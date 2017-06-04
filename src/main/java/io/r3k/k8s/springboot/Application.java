package io.r3k.k8s.springboot;

import static java.lang.Boolean.TRUE;
import static java.lang.Thread.sleep;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Application {

  private static final int RANDOM_ID = (int) (Math.random() * 1000);

  @RequestMapping(value = "/")
  @SneakyThrows
  public String get(@RequestParam(required = false) Integer delay, @RequestParam(required = false) Boolean killMe) {
    if (delay != null) {
      sleep(delay);
    }
    if (TRUE.equals(killMe)) {
      System.exit(0);
    }
    InetAddress ip = InetAddress.getLocalHost();
    String hostname = ip.getHostName();
    return "RANDOM_ID " + RANDOM_ID + " ip " + ip.getHostAddress() + " hostname " + hostname;
  }

  @RequestMapping(value = "/env")
  public List<VaraibleValue> env() {
    Map<String, String> env = System.getenv();
    List<VaraibleValue> output = new ArrayList<>(env.size());
    env.keySet().forEach((envName) -> {
      output.add(new VaraibleValue(envName, env.get(envName)));
    });
    return output;
  }

  @RequestMapping(value = "/env/{envName}")
  public String envSearch(@PathParam(value = "envName") String envNameToSearch) {
    Map<String, String> env = System.getenv();
    return env.get(envNameToSearch);
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}

@AllArgsConstructor
@Getter
class VaraibleValue {

  String envName;
  String value;
}
