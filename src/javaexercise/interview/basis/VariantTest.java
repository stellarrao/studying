package javaexercise.interview.basis;

public  class VariantTest
{
    private static int staticVar = 0;

    private int instanceVar = 0;

    public VariantTest()
    {
        staticVar++;
        instanceVar++;

        System.out.println("staticVar=" + staticVar + ",instanceVar=" + instanceVar);

    }

    public static void main(String[] args)
    {
        VariantTest variant1 = new VariantTest();
        VariantTest variant2 = new VariantTest();
        VariantTest variant3 = new VariantTest();
        VariantTest variant4 = new VariantTest();
    }

}
