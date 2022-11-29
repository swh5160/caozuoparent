/**
 * @author 施子安
 * @create
 */
public class test {

   /* //饿汉模式，类加载时就实例化
    private static test t = new test();
    //构造方法私有化
    private test(){}
    //对外只提供一个实例化
    public static test getInstance(){
        return t;
    }*/
    //懒汉模式，调用时才实例化，构造方法私有化
   /* private static test t;
    private test(){}
    public static test getInstance(){
        if (t == null){
            t = new test();
        }
        return t;
    }*///线程安全懒汉式，调用的时实例化，对外只提供一个实例化，线程安全，构造方法私有化
    private static test t;
    private test(){};
    public static  synchronized  test getInstance(){
        if (t == null){
            t = new test();
        }
        return t;
    }
}
