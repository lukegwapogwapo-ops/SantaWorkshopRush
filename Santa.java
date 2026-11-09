public class Santa extends WorkshopWorker {

    public Santa(String name) {
        super(name);
    }

    @Override
    public void performTask() {
        System.out.println(getName() + " is delivering the gifts!");
    }
}