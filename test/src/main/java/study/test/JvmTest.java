package study.test;

public class JvmTest {
    private String title = null;

    public JvmTest() {
    }

    public JvmTest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "JvmTest{" +
                "title='" + title + '\'' +
                '}';
    }

    public void test1() {
        JvmTest jvmTest = getTest1Jvm();
        System.out.println(jvmTest);
    }

    public JvmTest getTest1Jvm() {
        return createJvmTest("test1");
    }

    public JvmTest createJvmTest(String title) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new JvmTest(title);
    }

    public static void main(String[] args) {
//        List<JvmTest> list = new ArrayList<>();
//        long c = 0;
        while (true) {
//            list.add(new JvmTest("" + (++c)));
//            if (list.size()>1) {
//                list.remove(1);
//            }
            new JvmTest().test1();
        }
    }
}
