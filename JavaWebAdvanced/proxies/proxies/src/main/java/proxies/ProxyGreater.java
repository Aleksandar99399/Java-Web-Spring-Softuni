package proxies;

public class ProxyGreater implements GreaterInterface {
    private final GreaterInterface delegate;

    public ProxyGreater(GreaterInterface delegate ) {
        this.delegate = delegate;
    }

    @Override
    public void great() {
        System.out.println("Before execution ");
        delegate.great();
        System.out.println("After execution ");
    }
}
