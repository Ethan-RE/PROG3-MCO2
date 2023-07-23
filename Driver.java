public class Driver {
    public static void main(String[] args) {
        FactoryModel model = new FactoryModel();
        FactoryView view = new FactoryView();
        FactoryController controller = new FactoryController(model, view);
    }
}