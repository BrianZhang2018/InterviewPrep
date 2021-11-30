package apple.phone;

/**
 * https://www.1point3acres.com/bbs/thread-734482-1-1.html
 *
 * Double Checked Locking
 *
 * Created by brianzhang on 4/29/21.
 */
public class Singleton
{
    private static volatile Singleton obj = null;

    private Singleton() {}

    public static Singleton getInstance()
    {
        if (obj == null)
        {
            // To make thread safe
            synchronized (Singleton.class)
            {
                // check again as multiple threads can reach above step
                if (obj==null) // make sure we only initiate the obj once
                    obj = new Singleton();
            }
        }
        return obj;
    }
}
