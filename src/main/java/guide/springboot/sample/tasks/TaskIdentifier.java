package guide.springboot.sample.tasks;

import java.util.Objects;

public class TaskIdentifier {
    private final String id;

    public TaskIdentifier(final String id) {this.id = id;}

    public String getId(){return id;}

}
