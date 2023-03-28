package proxies;

public class Test {
    public static void main(String[] args) {
        GreaterInterface myGreater=new ProxyGreater(new RealGreater());
        myGreater.great();
    }
}
