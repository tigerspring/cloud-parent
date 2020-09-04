package thread.threadlocal;

import java.util.HashMap;

class ThreadLocalUse {
    static class Context {

        private static final ThreadLocal<HashMap<String,String>> CONTEXT1 = new ThreadLocal<HashMap<String,String>>(){
            protected HashMap<String,String> initialValue(){
                return new HashMap<String,String>();
            }
        };
        private static final InheritableThreadLocal<HashMap<String,String>> CONTEXT2 = new InheritableThreadLocal<HashMap<String,String>>(){
            protected HashMap<String,String> initialValue(){
                return new HashMap<String,String>();
            }
        };
        public static HashMap<String,String> getContext1() {
            return CONTEXT1.get();
        }
        public static HashMap<String,String> getContext2() {
            return CONTEXT2.get();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Context.getContext1().put("name", "wqx");
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println("name:" + Context.getContext1().get("name"));
            }
        });
        thread.start();
    }
}
