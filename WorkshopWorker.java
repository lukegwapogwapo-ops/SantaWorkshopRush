
public class WorkshopWorker {

    private String name;

    public WorkshopWorker(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void performTask() {
        System.out.println(name + " is helping in the workshop!");
    }
}
    

