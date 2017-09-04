package javaexercise.interview.designpattern;

public class Singleton
{
    private Singleton()
    {

    }

    /* 懒汉式单例 */
    private static Singleton instance = null;

    /*
     * public static synchronized Singleton getInstance() { if (instance == null) { instance = new Singleton(); } return instance; }
     */

    /*双重检查锁定*/
    /*public static synchronized Singleton getInstance()
    {
        if (instance == null)
        {
            synchronized (Singleton.class)
            {
                if (instance == null)
                {

                    instance = new Singleton();
                }
            }

        }
        return instance;
    }
*/
    /*静态内部类*/
    private static class LazyHolder{
        private static final Singleton INSTANCE = new Singleton();
    }
    public static final Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }
    
    /*
     * 饿汉式单例 private static Singleton instance = new Singleton(); public static Singleton getInstance() { return instance; }
     */
}
