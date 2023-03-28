package proxies;

public class RealGreater implements GreaterInterface {
    @Override
    public void great() {
        System.out.println("Hello, from me!");
    }
}
