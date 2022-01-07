package hello.core.singleton;

public class SingletonService {

    private static final SingletonService instance = new SingletonService();

    private void SingletonService(){}

    public static SingletonService getInstance() {
//        if(instance == null){
//            instance = new SingletonService();
//        }
        return instance;
    }
}
