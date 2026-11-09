public class Elf extends WorkshopWorker {

    public Elf(String name) {
        super(name);
    }

    @Override
    public void performTask() {
        System.out.println(getName() + " is assembling the gifts!");
    }
}