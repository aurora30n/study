package study.test;

public class LockExp1 {

    class Parent {
        public synchronized void doSomeThing() {
            System.out.println("Parent do");
        }
    }

    class Children extends Parent {
        public synchronized void doSomeThing() {
            System.out.println("Children do");
            super.doSomeThing();
        }
    }

    public static void main(String[] args) {
        LockExp1.Children children = new LockExp1().new Children();
        children.doSomeThing();
    }
}
